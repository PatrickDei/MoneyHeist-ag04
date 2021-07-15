package org.agency04.software.moneyheist.repository;

import org.agency04.software.moneyheist.entity.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findAll() {
        List<Member> members = memberRepository.findAll();

        Assertions.assertNotNull(members);
        Assertions.assertEquals(members.size(), 12);
    }

    @Test
    void findOneByEmail() {
        final String email = "bobby@ag04.com";
        Optional<Member> member = memberRepository.findOneByEmail(email);

        Assertions.assertNotNull(member);
        Assertions.assertEquals(member.get().getEmail(), email);
    }

    @Test
    void findFirstByNameIgnoreCase() {
        final String nameUpper = "BOB";
        final String nameLower = "bob";

        Optional<Member> member;
        member = memberRepository.findFirstByNameIgnoreCase(nameUpper);

        Assertions.assertNotNull(member);
        Assertions.assertFalse(member.isEmpty());
        Assertions.assertEquals(member.get().getName().toLowerCase(), nameUpper.toLowerCase());

        member = memberRepository.findFirstByNameIgnoreCase(nameLower);

        Assertions.assertNotNull(member);
        Assertions.assertFalse(member.isEmpty());
        Assertions.assertEquals(member.get().getName().toLowerCase(), nameLower.toLowerCase());
    }

    @Test
    void findMembersForHeist() {
        List<Member> members = memberRepository.findMembersForHeist(1);

        Assertions.assertTrue(members.isEmpty());
    }

    @Test
    @Transactional
    void findEligibleMembers() {
        final String skillName = "Hacking";
        final Integer skillLevel = 1;

        List<Member> members = memberRepository.findEligibleMembers(skillName, skillLevel);

        Assertions.assertNotNull(members);
        for(Member m : members){
            Assertions.assertTrue(m.getSkills().stream()
                    .anyMatch(s ->
                            s.getName().equals(skillName)
                    ));
            Assertions.assertTrue(m.getSkills().stream()
                    .filter(s ->
                            s.getName().equals(skillName)).anyMatch(s -> s.getSkillLevel() >= skillLevel));
        }
    }

    @Test
    void existsByEmail() {
        boolean memberExists = memberRepository.existsByEmail("bobby@ag04.com");

        Assertions.assertTrue(memberExists);

        memberExists = memberRepository.existsByEmail("unexistingEmail");

        Assertions.assertFalse(memberExists);
    }

    @Test
    void isParticipatingInAnotherHeist() {
        boolean isMemberBusy = memberRepository.isParticipatingInAnotherHeist(1);

        Assertions.assertFalse(isMemberBusy);
    }

    @Test
    @Modifying
    @Transactional
    @DirtiesContext
    void removeSkillFromMember() {
        final Integer id = 1;
        final String skillName = "Hacking";

        Integer changedRows = memberRepository.removeSkillFromMember(id, skillName);

        Assertions.assertNotNull(changedRows);
        Assertions.assertEquals(changedRows, 1);

        Optional<Member> modifiedMember = memberRepository.findById(id);

        Assertions.assertFalse(modifiedMember.isEmpty());
        Assertions.assertTrue(modifiedMember.get()
                .getSkills().stream()
                .noneMatch( s ->
                        s.getName().equals(skillName)
                ));
    }
}