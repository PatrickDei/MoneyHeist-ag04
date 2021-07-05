package org.agency04.software.moneyheist.members;


import org.agency04.software.moneyheist.interfaces.ITransform;
import org.agency04.software.moneyheist.members.exceptions.SameEmailException;
import org.agency04.software.moneyheist.members.repository.CustomMemberRepository;
import org.agency04.software.moneyheist.members.repository.MemberRepository;
import org.agency04.software.moneyheist.members.validation.MemberCommand;
import org.agency04.software.moneyheist.skills.Skill;
import org.agency04.software.moneyheist.skills.SkillRepository;
import org.agency04.software.moneyheist.skills.exceptions.SkillAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService, ITransform {
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
        return validateAndReturnMemberId(this.CommandToMember(member));
    }

    @Override
    public Integer updateMemberSkills(Integer id, MemberCommand updatedMember){
        Member member = memberRepository.findById(id).orElse(null);
        if(member == null)
            return null;

        // update the objects skills and validation will take care of the rest
        if(updatedMember.getSkills() != null)
            member.updateSkills(updatedMember.getSkills().stream().map(this::CommandToSkill).collect(Collectors.toList()));

        String suggestedMainSkill = this.normalizeString(updatedMember.getMainSkill());
        if(suggestedMainSkill != null){
            if(!member.getSkills().stream().map(Skill::getName).collect(Collectors.toList()).contains(suggestedMainSkill))
                return 0;
            member.setMainSkill(suggestedMainSkill);
        }
        //

        return validateAndReturnMemberId(member);
    }

    @Override
    public Integer removeSkillFromMember(Integer memberId, String skill){
        return memberRepository.removeSkillFromMember(memberId, this.normalizeString(skill));
    }


    @Override
    public Integer validateAndReturnMemberId(Member member){
        try {
            member.validateEmail(memberRepository.findAll());
            member.validateSkills(skillRepository.findAll());
        }
        catch(SameEmailException e) {
            return null;
        }
        catch(SkillAlreadyExistsException e){
            return this.customMemberRepository.saveMember(member, e.getExistingSkills()).getId();
        }

        return this.memberRepository.save(member).getId();
    }
}