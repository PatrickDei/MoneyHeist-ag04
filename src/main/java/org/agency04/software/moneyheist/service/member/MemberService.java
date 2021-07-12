package org.agency04.software.moneyheist.service.member;

import org.agency04.software.moneyheist.dto.response_entity.MemberDTO;
import org.agency04.software.moneyheist.dto.request_entity.MemberCommand;
import org.agency04.software.moneyheist.validation.unique_field.FieldValueExists;

import java.util.List;
import java.util.Optional;

public interface MemberService extends FieldValueExists {
    List<MemberDTO> findAll();

    Optional<MemberDTO> getMemberById(Integer id);

    Integer saveMember(MemberCommand member);

    Integer updateMemberSkills(Integer id, MemberCommand updatedMember);

    Integer removeSkillFromMember(Integer memberId, String skill);
}
