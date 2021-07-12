package org.agency04.software.moneyheist.service.member;


import org.agency04.software.moneyheist.dto.request_entity.MemberCommand;
import org.agency04.software.moneyheist.dto.response_entity.MemberDTO;
import org.agency04.software.moneyheist.entity.member.Member;
import org.agency04.software.moneyheist.repository.MemberRepository;
import org.agency04.software.moneyheist.repository.RoleRepository;
import org.agency04.software.moneyheist.service.email.EmailService;
import org.agency04.software.moneyheist.transformation.Transformation;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<MemberDTO> findAll(){
        return this.memberRepository.findAll().stream().map(Transformation::memberToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<MemberDTO> getMemberById(Integer id){
        return this.memberRepository.findById(id).map(Transformation::memberToDTO);
    }

    @Override
    public Integer saveMember(MemberCommand member){
        Member memberToAdd = Transformation.commandToMember(member);
        // place member role
        memberToAdd.setRole(roleRepository.findById(2).orElse(null));

        emailService.sendSimpleMessage(memberToAdd.getEmail(), "Money heist confirmation", "You have successfully created a member");

        return memberRepository.save(memberToAdd).getId();
    }

    @Override
    public Integer updateMemberSkills(Integer id, MemberCommand updatedMember){
        Member member = memberRepository.findById(id).orElse(null);
        if(member == null)
            return null;

        // update the objects skills
        if(updatedMember.getSkills() != null)
            member.updateSkills(
                    updatedMember.getSkills().stream()
                            .map(Transformation::commandToSkill)
                            .collect(Collectors.toList()));

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

    // validation
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