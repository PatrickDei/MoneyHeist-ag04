package org.agency04.software.moneyheist.service;

import org.agency04.software.moneyheist.dto.response.MemberDTO;
import org.agency04.software.moneyheist.dto.request.MemberCommand;
import org.agency04.software.moneyheist.entity.member.Member;
import org.agency04.software.moneyheist.validation.interfaces.FieldValueExists;

import java.util.List;
import java.util.Optional;

public interface MemberService extends FieldValueExists {
    List<MemberDTO> findAll();

    Optional<MemberDTO> getMemberDTOById(Integer id);

    Optional<Member> findMemberByEmail(String email);

    Optional<Member> findMemberById(Integer id);

    Integer saveMember(MemberCommand member);

    Integer updateMemberSkills(Integer id, MemberCommand updatedMember);

    Integer removeSkillFromMember(Integer memberId, String skill);
}
