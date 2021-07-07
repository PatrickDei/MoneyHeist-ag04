package org.agency04.software.moneyheist.services.member;

import org.agency04.software.moneyheist.dto.member.MemberDTO;
import org.agency04.software.moneyheist.entities.member.Member;
import org.agency04.software.moneyheist.exceptions.member.InvalidMainSkill;
import org.agency04.software.moneyheist.validation.requestEntity.member.MemberCommand;
import org.agency04.software.moneyheist.validation.uniqueField.FieldValueExists;

import java.util.List;

public interface MemberService extends FieldValueExists {
    List<MemberDTO> findAll();

    Integer saveMember(MemberCommand member);

    Integer updateMemberSkills(Integer id, MemberCommand updatedMember) throws InvalidMainSkill;

    Integer removeSkillFromMember(Integer memberId, String skill);

    Integer saveMemberAndReturnId(Member member);
}
