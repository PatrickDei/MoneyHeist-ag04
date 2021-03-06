package org.agency04.software.moneyheist.transformation;


import org.agency04.software.moneyheist.dto.request.HeistCommand;
import org.agency04.software.moneyheist.dto.request.HeistRequirementCommand;
import org.agency04.software.moneyheist.dto.request.MemberCommand;
import org.agency04.software.moneyheist.dto.request.SkillCommand;
import org.agency04.software.moneyheist.dto.response.*;
import org.agency04.software.moneyheist.entity.heist.Heist;
import org.agency04.software.moneyheist.entity.heist.HeistStatus;
import org.agency04.software.moneyheist.entity.member.Member;
import org.agency04.software.moneyheist.entity.requirement.HeistRequirement;
import org.agency04.software.moneyheist.entity.skill.Skill;

import java.util.Set;
import java.util.stream.Collectors;

public final class Transformation {

    // Members
    public static Member commandToMember(MemberCommand memberCommand){
        return new Member(
                memberCommand.getName(),
                memberCommand.getSex(),
                memberCommand.getEmail().toLowerCase(),
                memberCommand.getSkills().stream().map(Transformation::commandToSkill).collect(Collectors.toSet()),
                normalizeString(memberCommand.getMainSkill()),
                memberCommand.getStatus(),
                "pass",
                null);
    }

    public static MemberDTO memberToDTO(Member member){
        return new MemberDTO(
                member.getName(),
                member.getSex(),
                member.getEmail(),
                member.getSkills().stream()
                        .map(Transformation::skillToDTO)
                        .collect(Collectors.toSet()),
                member.getMainSkill(),
                member.getStatus());
    }

    public static Set<HeistMembersDTO> membersToHeistMembersDTO(Set<Member> members){
        return members.stream()
                .map( m -> {
                    String name = m.getName();
                    Set<SkillDTO> skills = m.getSkills().stream()
                            .map(Transformation::skillToDTO)
                            .collect(Collectors.toSet());
                    return new HeistMembersDTO(name, skills);
                })
                .collect(Collectors.toSet());
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
        return new HeistDTO(
                heist.getName(),
                heist.getLocation(),
                heist.getStartTime(),
                heist.getEndTime(),
                heist.getRequirements().stream().map(Transformation::requirementToDTO).collect(Collectors.toList()),
                heist.getMembers().stream().map(Transformation::memberToDTO).collect(Collectors.toSet()),
                heist.getStatus(),
                heist.getOutcome());
    }

    public static Heist commandToHeist(HeistCommand heist){
        return new Heist(
                heist.getName(),
                heist.getLocation(),
                heist.getStartTime(),
                heist.getEndTime(),
                heist.getSkills().stream().map(Transformation::commandToRequirement).collect(Collectors.toSet()),
                HeistStatus.PLANNING,
                null);
    }


    // requirements
    public static HeistRequirementDTO requirementToDTO(HeistRequirement requirement){
        return new HeistRequirementDTO(
                Transformation.skillToDTO(requirement.getSkill()),
                requirement.getNumberOfMembers()
        );
    }

    public static Set<HeistRequirementDTO> requirementsToDTO(Set<HeistRequirement> requirements){
        return requirements.stream().map(Transformation::requirementToDTO).collect(Collectors.toSet());
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
