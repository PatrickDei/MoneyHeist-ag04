package org.agency04.software.moneyheist.interfaces;


import org.agency04.software.moneyheist.members.Member;
import org.agency04.software.moneyheist.members.MemberDTO;
import org.agency04.software.moneyheist.members.validation.MemberCommand;
import org.agency04.software.moneyheist.skills.Skill;
import org.agency04.software.moneyheist.skills.SkillCommand;
import org.agency04.software.moneyheist.skills.SkillDTO;

import java.util.stream.Collectors;

public interface ITransform {

    // Members
    default Member CommandToMember(MemberCommand memberCommand){
        return new Member(
                memberCommand.getName(),
                memberCommand.getSex(),
                memberCommand.getEmail(),
                memberCommand.getSkills().stream().map(this::CommandToSkill).collect(Collectors.toSet()),
                normalizeString(memberCommand.getMainSkill()),
                memberCommand.getStatus()
        );
    }

    default MemberDTO MemberToDTO(Member member){
        return new MemberDTO(member.getId(), member.getName(), member.getSkills().stream().map(this::SkillToDTO).collect(Collectors.toSet()), member.getMainSkill(), member.getEmail());
    }


    // Skills
    default Skill CommandToSkill(SkillCommand skillCommand) {
        return new Skill(normalizeString(skillCommand.getName()), skillCommand.getLevel().length());
    }

    default SkillDTO SkillToDTO(Skill skill){
        return new SkillDTO(skill.getName(), this.SkillLevelToString(skill.getSkillLevel()));
    }

    default String SkillLevelToString(Integer count){
        return "*".repeat(count);
    }

    default String normalizeString(String text){
        // capitalise first letter only
        if(text != null)
            return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
        return null;
    }
}
