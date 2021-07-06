package org.agency04.software.moneyheist.transformation;


import org.agency04.software.moneyheist.entities.heist.Heist;
import org.agency04.software.moneyheist.dto.heist.HeistDTO;
import org.agency04.software.moneyheist.entities.heist.requirement.HeistRequirement;
import org.agency04.software.moneyheist.dto.heist.requirement.HeistRequirementDTO;
import org.agency04.software.moneyheist.entities.member.Member;
import org.agency04.software.moneyheist.dto.member.MemberDTO;
import org.agency04.software.moneyheist.validation.member.MemberCommand;
import org.agency04.software.moneyheist.entities.skill.Skill;
import org.agency04.software.moneyheist.validation.skill.SkillCommand;
import org.agency04.software.moneyheist.dto.skill.SkillDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public final class Transformation {

    // Members
    public static Member CommandToMember(MemberCommand memberCommand){
        return new Member(
                memberCommand.getName(),
                memberCommand.getSex(),
                memberCommand.getEmail(),
                memberCommand.getSkills().stream().map(Transformation::CommandToSkill).collect(Collectors.toSet()),
                normalizeString(memberCommand.getMainSkill()),
                memberCommand.getStatus()
        );
    }

    public static MemberDTO MemberToDTO(Member member){
        return new MemberDTO(member.getId(), member.getName(), member.getSkills().stream().map(Transformation::SkillToDTO).collect(Collectors.toSet()), member.getMainSkill(), member.getEmail());
    }


    // Skills
    public static Skill CommandToSkill(SkillCommand skillCommand) {
        return new Skill(normalizeString(skillCommand.getName()), skillCommand.getLevel().length());
    }

    public static SkillDTO SkillToDTO(Skill skill){
        return new SkillDTO(skill.getName(), Transformation.SkillLevelToString(skill.getSkillLevel()));
    }

    public static String SkillLevelToString(Integer count){
        return "*".repeat(count);
    }


    // heists
    public static HeistDTO HeistToDTO(Heist heist){
        return new HeistDTO(heist.getId(),
                heist.getName(),
                heist.getLocation(),
                heist.getStartTime(),
                heist.getEndTime(),
                heist.getRequirements().stream().map(Transformation::RequirementToDTO).collect(Collectors.toList())
        );
    }

    public static HeistRequirementDTO RequirementToDTO(HeistRequirement requirement){
        return new HeistRequirementDTO(
                Transformation.SkillToDTO(requirement.getSkill()),
                requirement.getNumberOfMembers()
        );
    }


    public static String normalizeString(String text){
        // capitalise first letter only
        if(text != null)
            return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
        return null;
    }
}
