package org.agency04.software.moneyheist.heist.members;

import org.agency04.software.moneyheist.heist.members.exceptions.SameEmailException;
import org.agency04.software.moneyheist.heist.members.repository.CustomMemberRepository;
import org.agency04.software.moneyheist.heist.members.repository.MemberRepository;
import org.agency04.software.moneyheist.heist.skills.Skill;
import org.agency04.software.moneyheist.heist.skills.SkillRepository;
import org.agency04.software.moneyheist.heist.skills.exceptions.SameNameException;
import org.agency04.software.moneyheist.heist.skills.exceptions.SkillAlreadyExistsException;
import org.agency04.software.moneyheist.heist.transformations.Transformable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService, Transformable {
    private final MemberRepository memberRepository;
    private final CustomMemberRepository customMemberRepository;
    private final SkillRepository skillRepository;

    public MemberServiceImpl(MemberRepository memberRepository, CustomMemberRepository customMemberRepository, SkillRepository skillRepository) {
        this.memberRepository = memberRepository;
        this.customMemberRepository = customMemberRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public Integer saveMember(MemberCommand member){
        Member m = CommandToMember(member);
        try {
            performChecks(m);
        }
        catch(SameEmailException | SameNameException e) {
            return null;
        }
        catch(SkillAlreadyExistsException e){
            return this.customMemberRepository.customMemberInsert(m, e.getExistingSkills()).getId();
        }

        return this.memberRepository.save(m).getId();
    }

    @Override
    public List<MemberDTO> findAll(){
        return this.memberRepository.findAll().stream().map(this::MemberToDTO).collect(Collectors.toList());
    }

    @Override
    public void performChecks(Member member) {
        if(memberRepository.findAll().stream().map(Member::getEmail).anyMatch(m -> m.equals(member.getEmail())))
            throw new SameEmailException();

        Set<String> setOfSkills = member.getSkills().stream().map(Skill::getName).collect(Collectors.toSet());
        if(setOfSkills.size() < member.getSkills().size())
            throw new SameNameException();

        List<Skill> existingSkills = skillRepository.findAll()
                .stream()
                .distinct()
                .filter(member.getSkills()::contains)
                .collect(Collectors.toList());
        if(!existingSkills.isEmpty())
            throw new SkillAlreadyExistsException(existingSkills);
    }
}

interface MemberService{
    Integer saveMember(MemberCommand member);

    List<MemberDTO> findAll();

    void performChecks(Member member);
}