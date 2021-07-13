package org.agency04.software.moneyheist.heist.members;

import org.agency04.software.moneyheist.dto.response.MemberDTO;
import org.agency04.software.moneyheist.entity.member.MemberStatus;
import org.agency04.software.moneyheist.repository.MemberRepository;
import org.agency04.software.moneyheist.service.MemberService;
import org.agency04.software.moneyheist.dto.request.MemberCommand;
import org.agency04.software.moneyheist.dto.request.SkillCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @Test
    void findAll() {
        List<MemberDTO> members = memberService.findAll();

        Assertions.assertNotNull(members);
        Assertions.assertEquals(members.size(), 4);
    }

    @Test
    void saveMember() {
        MemberCommand member = new MemberCommand("Helsinki", "F", "newemail@ag04", MemberStatus.EXPIRED, Arrays.asList(new SkillCommand("counting", "****"), new SkillCommand("hacking", "**")), "counting");

        Integer id = memberService.saveMember(member);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(id, 5);
    }

    @Test
    void updateMemberSkills(){
        MemberCommand member = new MemberCommand(null, null, null, null, Arrays.asList(new SkillCommand("counting", "****"), new SkillCommand("hacking", "**")), "counting");

        final Integer assignedId = 1;
        Integer id = memberService.updateMemberSkills(assignedId, member);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(id, assignedId);
    }

    @Test
    void validateAndReturnMemberId() {
    }

    @Test
    void performChecks() {
    }
}