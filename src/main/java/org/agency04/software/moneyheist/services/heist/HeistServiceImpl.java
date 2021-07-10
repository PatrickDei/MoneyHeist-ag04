package org.agency04.software.moneyheist.services.heist;

import org.agency04.software.moneyheist.dto.HeistDTO;
import org.agency04.software.moneyheist.entities.heist.Heist;
import org.agency04.software.moneyheist.entities.heist.HeistStatus;
import org.agency04.software.moneyheist.entities.member.Member;
import org.agency04.software.moneyheist.entities.member.MemberStatus;
import org.agency04.software.moneyheist.entities.requirement.HeistRequirement;
import org.agency04.software.moneyheist.entities.skill.Skill;
import org.agency04.software.moneyheist.repositories.heist.HeistRepository;
import org.agency04.software.moneyheist.repositories.member.MemberRepository;
import org.agency04.software.moneyheist.transformation.Transformation;
import org.agency04.software.moneyheist.validation.request_entities.HeistCommand;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HeistServiceImpl implements HeistService {

    @Autowired
    private Transformation transformation;
    @Autowired
    private HeistRepository heistRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<HeistDTO> findAll() {
        return heistRepository.findAll().stream().map(Transformation::heistToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<Heist> findHeistById(Integer id){
        return this.heistRepository.findById(id);
    }

    @Override
    public Integer saveHeist(HeistCommand heist) throws ParseException {
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
    public Integer confirmHeistMembers(Integer heistId, List<String> memberNames){
        Heist heist = heistRepository.findById(heistId).orElse(null);
        if(heist == null)
            return null;

        // they have been validated
        Set<Member> members = new HashSet<>();
        for(String memberName : memberNames)
            members.add(memberRepository.findFirstByNameIgnoreCase(memberName).orElse(null));

        heist.setMembers(members);
        heist.setStatus(HeistStatus.READY);
        return heistRepository.save(heist).getId();
    }

    @Override
    public HeistStatus getHeistStatus(Integer heistId){
        Heist heist = this.heistRepository.findById(heistId).orElse(null);
        return (heist != null) ? heist.getStatus() : null;
    }

    @Override
    public boolean heistCanBeStarted(Integer heistId){
        return Objects.requireNonNull(this.heistRepository.findById(heistId).orElse(null)).getStatus() == HeistStatus.READY;
    }

    @Override
    public void startHeist(Integer id){
        Heist h =this.heistRepository.findById(id).orElse(null);
        assert h != null;
        h.setStatus(HeistStatus.IN_PROGRESS);
        this.heistRepository.save(h);
    }

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

        for(HeistRequirement r : heist.getRequirements()) {
            Skill skill = r.getSkill();
            Member member = memberRepository.findFirstByNameIgnoreCase(name).orElse(null);

            if(member == null
                || !Arrays.asList(MemberStatus.AVAILABLE, MemberStatus.RETIRED).contains(member.getStatus())
                || !memberRepository.findEligibleMembers(skill.getName(), skill.getSkillLevel()).contains(member)
                || memberRepository.isParticipatingInAnotherHeist(member.getId()))
                return false;
        }
        return true;
    }
}
