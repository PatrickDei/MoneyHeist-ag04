package org.agency04.software.moneyheist.transformation;


import org.agency04.software.moneyheist.entities.heist.Heist;
import org.agency04.software.moneyheist.dto.heist.HeistDTO;
import org.agency04.software.moneyheist.entities.heist.requirement.HeistRequirement;
import org.agency04.software.moneyheist.dto.heist.requirement.HeistRequirementDTO;
import org.agency04.software.moneyheist.entities.member.Member;
import org.agency04.software.moneyheist.dto.member.MemberDTO;
import org.agency04.software.moneyheist.validation.requestEntity.heist.HeistCommand;
import org.agency04.software.moneyheist.validation.requestEntity.heist.requirement.HeistRequirementCommand;
import org.agency04.software.moneyheist.validation.requestEntity.member.MemberCommand;
import org.agency04.software.moneyheist.entities.skill.Skill;
import org.agency04.software.moneyheist.validation.requestEntity.skill.SkillCommand;
import org.agency04.software.moneyheist.dto.skill.SkillDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.stream.Collectors;

@Component
public final class Transformation {

    // Members
    public static Member commandToMember(MemberCommand memberCommand){
        return new Member(
                memberCommand.getName(),
                memberCommand.getSex(),
                memberCommand.getEmail(),
                memberCommand.getSkills().stream().map(Transformation::commandToSkill).collect(Collectors.toSet()),
                normalizeString(memberCommand.getMainSkill()),
                memberCommand.getStatus()
        );
    }

    public static MemberDTO memberToDTO(Member member){
        return new MemberDTO(member.getId(), member.getName(), member.getSkills().stream().map(Transformation::skillToDTO).collect(Collectors.toSet()), member.getMainSkill(), member.getEmail());
    }


    // Skills
    public static Skill commandToSkill(SkillCommand skillCommand) {
        return new Skill(normalizeString(skillCommand.getName()), skillCommand.getLevel().length());
    }

    public static SkillDTO skillToDTO(Skill skill){
        return new SkillDTO(skill.getName(), Transformation.skillLevelToString(skill.getSkillLevel()));
    }

    public static String skillLevelToString(Integer count){
        return "*".repeat(count);
    }


    // Heists
    public static HeistDTO heistToDTO(Heist heist){
        return new HeistDTO(heist.getId(),
                heist.getName(),
                heist.getLocation(),
                heist.getStartTime(),
                heist.getEndTime(),
                heist.getRequirements().stream().map(Transformation::requirementToDTO).collect(Collectors.toList())
        );
    }

    public static HeistRequirementDTO requirementToDTO(HeistRequirement requirement){
        return new HeistRequirementDTO(
                Transformation.skillToDTO(requirement.getSkill()),
                requirement.getNumberOfMembers()
        );
    }

    public static Heist commandToHeist(HeistCommand heist) throws ParseException {
        return new Heist(
                heist.getName(),
                heist.getLocation(),
                heist.getStartTime(),
                heist.getEndTime(),
                heist.getSkills().stream().map(Transformation::commandToRequirement).collect(Collectors.toSet())
        );
    }

    public static HeistRequirement commandToRequirement(HeistRequirementCommand requirement){
        return new HeistRequirement(
                Transformation.commandToSkill(requirement.getSkill()),
                requirement.getMembers()
        );
    }


    public static String normalizeString(String text){
        // capitalise first letter only
        if(text != null)
            return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
        return null;
    }
}
