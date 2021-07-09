package org.agency04.software.moneyheist.services.member;


import org.agency04.software.moneyheist.dto.member.MemberDTO;
import org.agency04.software.moneyheist.entities.member.Member;
import org.agency04.software.moneyheist.repositories.member.MemberRepository;
import org.agency04.software.moneyheist.services.skill.SkillService;
import org.agency04.software.moneyheist.transformation.Transformation;
import org.agency04.software.moneyheist.validation.requestEntities.member.MemberCommand;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private Transformation transformation;
    @Autowired
    private SkillService skillService;
    @Autowired
    private MemberRepository memberRepository;


    @Override
    public List<MemberDTO> findAll(){
        return this.memberRepository.findAll().stream().map(Transformation::memberToDTO).collect(Collectors.toList());
    }

    @Override
    public Integer saveMember(MemberCommand member){
        Member memberToAdd = Transformation.commandToMember(member);

        return memberRepository.save(memberToAdd).getId();
    }

    @Override
    public Integer updateMemberSkills(Integer id, MemberCommand updatedMember){
        Member member = memberRepository.findById(id).orElse(null);
        if(member == null)
            return null;

        // update the objects skills
        if(updatedMember.getSkills() != null)
            member.updateSkills(updatedMember.getSkills().stream().map(Transformation::commandToSkill).collect(Collectors.toList()));

        String suggestedMainSkill = Transformation.normalizeString(updatedMember.getMainSkill());
        if(suggestedMainSkill != null)
            member.setMainSkill(suggestedMainSkill);
        //

        return memberRepository.save(member).getId();
    }

    @Override
    public Integer removeSkillFromMember(Integer memberId, String skill){
        return memberRepository.removeSkillFromMember(memberId, Transformation.normalizeString(skill));
    }

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        Assert.assertNotNull(fieldName);

        if(!fieldName.equals("email"))
            throw new UnsupportedOperationException("Field name not supported");

        if(value == null)
            return false;

        return this.memberRepository.existsByEmail(value.toString().toLowerCase());
    }
}