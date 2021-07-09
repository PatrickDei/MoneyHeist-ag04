package org.agency04.software.moneyheist.services.member;

import org.agency04.software.moneyheist.dto.MemberDTO;
import org.agency04.software.moneyheist.validation.requestEntities.MemberCommand;
import org.agency04.software.moneyheist.validation.uniqueField.FieldValueExists;

import java.util.List;

public interface MemberService extends FieldValueExists {
    List<MemberDTO> findAll();

    Integer saveMember(MemberCommand member);

    Integer updateMemberSkills(Integer id, MemberCommand updatedMember);

    Integer removeSkillFromMember(Integer memberId, String skill);
}
