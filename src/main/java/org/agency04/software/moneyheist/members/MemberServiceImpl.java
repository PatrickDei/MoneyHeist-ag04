package org.agency04.software.moneyheist.members;


import org.agency04.software.moneyheist.members.exceptions.SameEmailException;
import org.agency04.software.moneyheist.members.repository.CustomMemberRepository;
import org.agency04.software.moneyheist.members.repository.MemberRepository;
import org.agency04.software.moneyheist.members.validation.MemberCommand;
import org.agency04.software.moneyheist.skills.Skill;
import org.agency04.software.moneyheist.skills.SkillRepository;
import org.agency04.software.moneyheist.skills.exceptions.SkillAlreadyExistsException;
import org.agency04.software.moneyheist.transformations.Transformable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<MemberDTO> findAll(){
        return this.memberRepository.findAll().stream().map(this::MemberToDTO).collect(Collectors.toList());
    }

    @Override
    public Integer saveMember(MemberCommand member){
        return validateAndReturnMemberId(this.CommandToMember(member), true);
    }

    @Override
    public Integer updateMemberSkills(Integer id, MemberCommand updatedMember){
        Member member = memberRepository.findById(id).orElse(null);
        if(member == null)
            return null;

        // update the objects skills and email and validation will take care of the rest
        if(updatedMember.getSkills() != null)
            member.updateSkills(updatedMember.getSkills().stream().map(this::CommandToSkill).collect(Collectors.toList()));

        String suggestedMainSkill = this.normalizeString(updatedMember.getMainSkill());
        if(updatedMember.getMainSkill() != null){
            if(!member.getSkills().stream().map(Skill::getName).collect(Collectors.toList()).contains(suggestedMainSkill))
                return 0;
            member.setMainSkill(suggestedMainSkill);
        }
        //

        return validateAndReturnMemberId(member, false);
    }

    @Override
    public Integer removeSkillFromMember(Integer memberId, String skill){
        return memberRepository.removeSkillFromMember(memberId, this.normalizeString(skill));
    }


    // helper methods
    @Override
    public Integer validateAndReturnMemberId(Member member, boolean newMember){
        try {
            performChecks(member, newMember);
        }
        catch(SameEmailException e) {
            return null;
        }
        catch(SkillAlreadyExistsException e){
            return this.customMemberRepository.saveMember(member, e.getExistingSkills()).getId();
        }

        return this.memberRepository.save(member).getId();
    }

    @Override
    public void performChecks(Member member, boolean newMember) {
        if(newMember && memberRepository.findAll().stream().map(Member::getEmail).anyMatch(m -> m.equals(member.getEmail())))
            throw new SameEmailException();

        List<Skill> existingSkills = skillRepository.findAll()
                .stream()
                .distinct()
                .filter(member.getSkills()::contains)
                .collect(Collectors.toList());
        if(!existingSkills.isEmpty())
            throw new SkillAlreadyExistsException(existingSkills);
    }
}