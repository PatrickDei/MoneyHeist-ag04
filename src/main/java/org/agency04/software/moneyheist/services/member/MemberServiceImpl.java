package org.agency04.software.moneyheist.services.member;


import org.agency04.software.moneyheist.dto.member.MemberDTO;
import org.agency04.software.moneyheist.entities.member.Member;
import org.agency04.software.moneyheist.entities.skill.Skill;
import org.agency04.software.moneyheist.exceptions.member.InvalidMainSkill;
import org.agency04.software.moneyheist.repositories.member.CustomMemberRepository;
import org.agency04.software.moneyheist.repositories.member.MemberRepository;
import org.agency04.software.moneyheist.repositories.skill.SkillRepository;
import org.agency04.software.moneyheist.transformation.Transformation;
import org.agency04.software.moneyheist.validation.requestEntity.member.MemberCommand;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private Transformation transformation;
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
        return this.memberRepository.findAll().stream().map(Transformation::memberToDTO).collect(Collectors.toList());
    }

    @Override
    public Integer saveMember(MemberCommand member){
        Member memberToAdd = Transformation.commandToMember(member);

        return saveMemberAndReturnId(memberToAdd);
    }

    @Override
    public Integer updateMemberSkills(Integer id, MemberCommand updatedMember) throws InvalidMainSkill {
        Member member = memberRepository.findById(id).orElse(null);
        if(member == null)
            return null;

        // update the objects skills
        if(updatedMember.getSkills() != null)
            member.updateSkills(updatedMember.getSkills().stream().map(Transformation::commandToSkill).collect(Collectors.toList()));

        String suggestedMainSkill = Transformation.normalizeString(updatedMember.getMainSkill());
        if(suggestedMainSkill != null)
            member.setMainSkill(suggestedMainSkill);

        return saveMemberAndReturnId(member);
    }

    @Override
    public Integer removeSkillFromMember(Integer memberId, String skill){
        return memberRepository.removeSkillFromMember(memberId, Transformation.normalizeString(skill));
    }

    @Override
    public Integer saveMemberAndReturnId(Member member){
        List<Skill> alreadyExistingSkills = this.skillRepository.findAll().stream()
                .distinct()
                .filter(member.getSkills()::contains)
                .collect(Collectors.toList());

        if(alreadyExistingSkills.isEmpty())
            return this.memberRepository.save(member).getId();

        return this.customMemberRepository.saveMember(member, alreadyExistingSkills).getId();
    }

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        Assert.assertNotNull(fieldName);

        if(!fieldName.equals("email"))
            throw new UnsupportedOperationException("Field name not supported");

        if(value == null)
            return false;

        return this.memberRepository.existsByEmail(value.toString());
    }
}