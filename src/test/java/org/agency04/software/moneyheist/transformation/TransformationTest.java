package org.agency04.software.moneyheist.transformation;

import org.agency04.software.moneyheist.dto.request.HeistRequirementCommand;
import org.agency04.software.moneyheist.dto.request.MemberCommand;
import org.agency04.software.moneyheist.dto.request.SkillCommand;
import org.agency04.software.moneyheist.dto.response.HeistMembersDTO;
import org.agency04.software.moneyheist.dto.response.HeistRequirementDTO;
import org.agency04.software.moneyheist.dto.response.MemberDTO;
import org.agency04.software.moneyheist.dto.response.SkillDTO;
import org.agency04.software.moneyheist.entity.member.Member;
import org.agency04.software.moneyheist.entity.member.MemberStatus;
import org.agency04.software.moneyheist.entity.requirement.HeistRequirement;
import org.agency04.software.moneyheist.entity.skill.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Set;

@SpringBootTest
class TransformationTest {

    @Test
    void commandToMember() {
        MemberCommand command = new MemberCommand("bob", "M", "bobby@ag04.com", MemberStatus.AVAILABLE, Arrays.asList(new SkillCommand("hack", "**")), null);

        Member member = Transformation.commandToMember(command);

        Assertions.assertNotNull(member);
        Assertions.assertNotNull(member.getSkills());
    }

    @Test
    void memberToDTO() {
        Member member = new Member("bob", "M", "bobby@ag04.com", Set.of(new Skill("Hacking", 3)), null, MemberStatus.AVAILABLE, null, null);

        MemberDTO dto = Transformation.memberToDTO(member);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getSkills());
    }

    @Test
    void membersToHeistMembersDTO() {
        Set<Member> members = Set.of(new Member("bob", "M", "bobby@ag04.com", Set.of(new Skill("Hacking", 3)), null, MemberStatus.AVAILABLE, null, null));

        Set<HeistMembersDTO> dtos = Transformation.membersToHeistMembersDTO(members);

        Assertions.assertNotNull(dtos);
        Assertions.assertEquals(dtos.size(), members.size());
    }

    @Test
    void commandToSkill() {
        SkillCommand command = new SkillCommand("name", "**");

        Skill skill = Transformation.commandToSkill(command);

        Assertions.assertNotNull(skill);
        Assertions.assertEquals(skill.getSkillLevel(), command.getLevel().length());
    }

    @Test
    void skillToDTO() {
        Skill skill = new Skill("name", 1);

        SkillDTO dto = Transformation.skillToDTO(skill);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getLevel().length(), skill.getSkillLevel());
    }

    @Test
    void skillLevelToString() {
        Integer amount = 5;

        String amountInStars = Transformation.skillLevelToString(5);

        Assertions.assertNotNull(amountInStars);
        Assertions.assertEquals(amountInStars.length(), amount);
    }

    @Test
    void requirementToDTO() {
        HeistRequirement requirement = new HeistRequirement(new Skill("name", 1), 1);

        HeistRequirementDTO dto = Transformation.requirementToDTO(requirement);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getName(), requirement.getSkill().getName());
    }

    @Test
    void commandToRequirement() {
        HeistRequirementCommand command = new HeistRequirementCommand("name", "***", 2);

        HeistRequirement requirement = Transformation.commandToRequirement(command);

        Assertions.assertNotNull(requirement);
        Assertions.assertEquals(requirement.getSkill().getName(), Transformation.normalizeString(command.getName()));
    }

    @Test
    void normalizeString() {
        String upper = "TEXT";
        String lower = "text";
        String normalised = "Text";

        Assertions.assertEquals(Transformation.normalizeString(upper), normalised);
        Assertions.assertEquals(Transformation.normalizeString(lower), normalised);
    }
}