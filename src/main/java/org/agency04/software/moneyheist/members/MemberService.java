package org.agency04.software.moneyheist.members;

import org.agency04.software.moneyheist.members.validation.MemberCommand;

import java.util.List;

public interface MemberService{
    List<MemberDTO> findAll();

    Integer saveMember(MemberCommand member);

    Integer updateMemberSkills(Integer id, MemberCommand updatedMember);

    Integer removeSkillFromMember(Integer memberId, String skill);

    void performChecks(Member member, boolean newMember);

    Integer validateAndReturnMemberId(Member member, boolean newMember);
}
