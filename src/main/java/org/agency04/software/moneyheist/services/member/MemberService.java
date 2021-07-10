package org.agency04.software.moneyheist.services.member;

import org.agency04.software.moneyheist.dto.MemberDTO;
import org.agency04.software.moneyheist.validation.requestEntities.MemberCommand;
import org.agency04.software.moneyheist.validation.uniqueField.FieldValueExists;

import java.util.List;
import java.util.Optional;

public interface MemberService extends FieldValueExists {
    List<MemberDTO> findAll();

    Optional<MemberDTO> getMemberById(Integer id);

    Integer saveMember(MemberCommand member);

    Integer updateMemberSkills(Integer id, MemberCommand updatedMember);

    Integer removeSkillFromMember(Integer memberId, String skill);
}
