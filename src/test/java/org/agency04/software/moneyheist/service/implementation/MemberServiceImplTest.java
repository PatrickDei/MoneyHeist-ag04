package org.agency04.software.moneyheist.service.implementation;

import org.agency04.software.moneyheist.dto.request.MemberCommand;
import org.agency04.software.moneyheist.dto.request.SkillCommand;
import org.agency04.software.moneyheist.dto.response.MemberDTO;
import org.agency04.software.moneyheist.entity.member.Member;
import org.agency04.software.moneyheist.entity.member.MemberStatus;
import org.agency04.software.moneyheist.entity.skill.Skill;
import org.agency04.software.moneyheist.repository.MemberRepository;
import org.agency04.software.moneyheist.service.MemberService;
import org.agency04.software.moneyheist.transformation.Transformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @Test
    void findAll() {
        final List<Member> mockedMembers = Arrays.asList(
                new Member("Bob", "M", "bobby@ag04.com", Set.of(
                        new Skill("Hacking", 1),
                        new Skill("Driving", 2)
                ), null, MemberStatus.AVAILABLE, "pass", null),
                new Member("Michael", "M", "michael@ag04.com", Set.of(
                        new Skill("Hacking", 1),
                        new Skill("Driving", 2)
                ), null, MemberStatus.AVAILABLE, "pass", null)
        );

        when(memberRepository.findAll())
                .thenReturn(mockedMembers);

        List<MemberDTO> members = memberService.findAll();

        Assertions.assertNotNull(members);
        Assertions.assertEquals(members.size(), 2);
    }

    @Test
    void getMemberDTOById() {
        final Integer id = 1;

        final Member mockedMember = new Member("Bob", "M", "bobby@ag04.com", Set.of(
                new Skill("Hacking", 1),
                new Skill("Driving", 2)
        ), null, MemberStatus.AVAILABLE, "pass", null);

        when(memberRepository.findById(id))
                .thenReturn(Optional.of(mockedMember));

        Optional<MemberDTO> member = memberService.getMemberDTOById(id);

        Assertions.assertNotNull(member);
        Assertions.assertFalse(member.isEmpty());
        Assertions.assertEquals(member.get().getName(), "Bob");
    }

    @Test
    @Modifying
    @DirtiesContext
    void saveMember() {
        final MemberCommand mockedMember = new MemberCommand("Bob", "M", "bobby@ag04.com", MemberStatus.AVAILABLE, Arrays.asList(
                new SkillCommand("Hacking", "*"),
                new SkillCommand("Driving", "**")
        ), null);

        final Member transformed = Transformation.commandToMember(mockedMember);

        when(memberRepository.save(transformed))
                .thenReturn(transformed);

        Integer id = memberService.saveMember(mockedMember);

        verify(memberRepository).save(transformed);
    }

    @Test
    void fieldValueExists() {
        final String fieldName = "email";
        Object value = "bobby@ag04.com";

        memberService.fieldValueExists(value, fieldName);

        verify(memberRepository).existsByEmail(value.toString());
    }
}