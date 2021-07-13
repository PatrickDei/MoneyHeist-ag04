package org.agency04.software.moneyheist.service.implementation;

import org.agency04.software.moneyheist.dto.request.HeistCommand;
import org.agency04.software.moneyheist.dto.response.HeistDTO;
import org.agency04.software.moneyheist.dto.response.HeistMembersDTO;
import org.agency04.software.moneyheist.dto.response.HeistRequirementDTO;
import org.agency04.software.moneyheist.entity.heist.Heist;
import org.agency04.software.moneyheist.entity.heist.HeistOutcome;
import org.agency04.software.moneyheist.entity.heist.HeistStatus;
import org.agency04.software.moneyheist.entity.member.Member;
import org.agency04.software.moneyheist.entity.member.MemberStatus;
import org.agency04.software.moneyheist.entity.requirement.HeistRequirement;
import org.agency04.software.moneyheist.entity.skill.Skill;
import org.agency04.software.moneyheist.repository.HeistRepository;
import org.agency04.software.moneyheist.repository.MemberRepository;
import org.agency04.software.moneyheist.repository.RequirementRepository;
import org.agency04.software.moneyheist.service.EmailService;
import org.agency04.software.moneyheist.service.HeistService;
import org.agency04.software.moneyheist.service.MemberService;
import org.agency04.software.moneyheist.transformation.Transformation;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HeistServiceImpl implements HeistService {

    private final HeistRepository heistRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final RequirementRepository requirementRepository;
    private final EmailService emailService;

    @Value("${levelUpTime}")
    private String levelUpTime;

    public HeistServiceImpl(HeistRepository heistRepository, MemberRepository memberRepository, MemberService memberService, RequirementRepository requirementRepository, EmailService emailService) {
        this.heistRepository = heistRepository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.requirementRepository = requirementRepository;
        this.emailService = emailService;
    }

    @Override
    public List<HeistDTO> findAll() {
        return heistRepository.findAll().stream().map(Transformation::heistToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HeistDTO> findHeist(Integer id){
        return heistRepository.findById(id).map(Transformation::heistToDTO);
    }

    @Override
    public List<Heist> findHeistsByOutcomeIsNull(){
        return heistRepository.findByOutcomeIsNull();
    }

    @Override
    public Set<HeistMembersDTO> getHeistMembers(Integer heistId){
        return Transformation.membersToHeistMembersDTO(Set.copyOf(memberRepository.findMembersForHeist(heistId)));
    }

    @Override
    public Set<HeistRequirementDTO> getHeistRequirements(Integer id){
        return Transformation.requirementsToDTO(Set.copyOf(requirementRepository.findRequirementsForHeist(id)));
    }

    @Override
    public HeistDTO getEligibleHeistMembers(Integer heistId){
        Heist heist = this.heistRepository.findById(heistId).orElse(null);

        if(heist == null)
            return null;

        Set<Member> member = new HashSet<>();
        for(HeistRequirement r : heist.getRequirements()) {
            Skill skill = r.getSkill();
            member.addAll(memberRepository.findEligibleMembers(skill.getName(), skill.getSkillLevel()));
        }

        heist.setMembers(member);

        return Transformation.heistToDTO(heist);
    }

    @Override
    public HeistStatus getHeistStatus(Integer heistId){
        Heist heist = this.heistRepository.findById(heistId).orElse(null);
        return (heist != null) ? heist.getStatus() : null;
    }

    @Override
    public Integer saveHeist(HeistCommand heist) {
        return heistRepository.save(Transformation.commandToHeist(heist)).getId();
    }

    @Override
    public Integer updateHeistSkills(HeistCommand heist, Integer heistId){
        Heist heistToUpdate = this.heistRepository.findById(heistId).orElse(null);

        if(heistToUpdate == null)
            return null;

        heistToUpdate.updateRequirements(Transformation.commandToHeist(heist).getRequirements());

        return heistRepository.save(heistToUpdate).getId();
    }

    @Override
    public Integer confirmHeistMembers(Integer heistId, List<String> memberNames){
        Heist heist = heistRepository.findById(heistId).orElse(null);
        if(heist == null)
            return null;

        // they have been validated
        Set<Member> members = new HashSet<>();
        for(String memberName : memberNames)
            members.add(memberRepository.findFirstByNameIgnoreCase(memberName).orElse(null));

        for(Member m : members)
            emailService.sendSimpleMessage(m.getEmail(), "Money heist update", "You have been added to a heist: " + heist.getName());

        heist.setMembers(members);
        heist.setStatus(HeistStatus.READY);
        return heistRepository.save(heist).getId();
    }

    @Override
    public boolean heistCanBeStarted(Integer heistId){
        Heist heist = this.heistRepository.findById(heistId).orElse(null);

        if(heist == null)
            return false;

        return heist.getStatus() == HeistStatus.READY;
    }

    @Override
    @Transactional
    public void startHeist(Integer id) {
        Heist h = this.heistRepository.findById(id).orElse(null);
        assert h != null;

        h.setStatus(HeistStatus.IN_PROGRESS);

        // for premature heist starts
        Date now = new Date();
        if( h.getStartTime().after(now) )
            h.setStartTime(now);

        this.heistRepository.save(h);

        for(Member m : h.getMembers())
            emailService.sendSimpleMessage(m.getEmail(), "Money heist update", "The heist" + h.getName() + " has started");
    }

    @Override
    @Transactional
    public void finishHeist(Integer id){
        Heist heist = heistRepository.findById(id).orElse(null);
        assert heist != null;
        final Random random = new Random();

        for(Member m : heist.getMembers()){
            m.setStatus( (random.nextBoolean()) ? MemberStatus.EXPIRED : MemberStatus.INCARCERATED);
            memberRepository.save(m);
        }

        int numberOfMembersRequired = heist.getRequirements().stream()
                .map(HeistRequirement::getNumberOfMembers)
                .collect(Collectors.toList()).stream()
                .mapToInt(Integer::intValue)
                .sum();

        int numberOfMembersExpired = 0;
        int numberOfMembersIncarcerated = 0;

        for(Member m : heist.getMembers()) {
            if (m.getStatus().equals(MemberStatus.EXPIRED))
                numberOfMembersExpired++;

            if (m.getStatus().equals(MemberStatus.INCARCERATED))
                numberOfMembersIncarcerated++;
        }

        boolean heistSucceeded = false;

        if(heist.getMembers().size() >= numberOfMembersRequired / 2
            && (numberOfMembersExpired + numberOfMembersIncarcerated) / numberOfMembersRequired >= 1 / 3)
            heistSucceeded = true;

        if(heist.getMembers().size() >= numberOfMembersRequired * 3 / 4
                && numberOfMembersIncarcerated / numberOfMembersRequired >= 1 / 3)
            heistSucceeded = true;

        if(heist.getMembers().size() == numberOfMembersRequired)
            heistSucceeded = true;

        heist.setStatus(HeistStatus.FINISHED);
        heist.setOutcome( heistSucceeded ? HeistOutcome.SUCCEEDED : HeistOutcome.FAILED);

        upgradeMemberSkills(heist);

        heistRepository.save(heist);

        for(Member m : heist.getMembers())
            emailService.sendSimpleMessage(m.getEmail(), "Money heist update", "The heist " + heist.getName() + " has finished");
    }

    @Override
    public void upgradeMemberSkills(Heist heist){
        for(Member m : heist.getMembers()) {
            for (Skill s : m.getSkills())
                if (heist.getRequirements().stream().map(HeistRequirement::getSkill).collect(Collectors.toSet()).contains(s)) {
                    s.setSkillLevel(
                            (int) (s.getSkillLevel() +
                                    (heist.getEndTime().getTime() - heist.getStartTime().getTime()) / (1000 * Integer.parseInt(levelUpTime)))
                    );
                }
            memberRepository.save(m);
        }
    }

    // validation
    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        Assert.assertNotNull(fieldName);

        if(!fieldName.equals("name"))
            throw new UnsupportedOperationException("Field name not supported");

        if(value == null)
            return false;

        return this.heistRepository.existsByName(value.toString());
    }

    @Override
    public boolean memberIsValid(String name, Integer heistId) {
        Heist heist = heistRepository.findById(heistId).orElse(null);
        // we do this so confirmHeistMember can return null and return Bad Request
        if(heist == null)
            return true;

        boolean memberCanJoin = false;
        for(HeistRequirement r : heist.getRequirements()) {
            Skill skill = r.getSkill();
            Member member = memberRepository.findFirstByNameIgnoreCase(name).orElse(null);

            if(member == null
                || Arrays.asList(MemberStatus.AVAILABLE, MemberStatus.RETIRED).contains(member.getStatus())
                || memberRepository.findEligibleMembers(skill.getName(), skill.getSkillLevel()).contains(member)
                || !memberRepository.isParticipatingInAnotherHeist(member.getId()))
                memberCanJoin = true;
        }
        return memberCanJoin;
    }
}
