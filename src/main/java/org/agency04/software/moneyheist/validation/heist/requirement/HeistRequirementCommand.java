package org.agency04.software.moneyheist.validation.heist.requirement;

import org.agency04.software.moneyheist.groups.OnlySkillsRequired;
import org.agency04.software.moneyheist.groups.WholeObjectRequired;
import org.agency04.software.moneyheist.validation.skill.SkillCommand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class HeistRequirementCommand {

    @NotEmpty(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    @NotBlank(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    private String name;

    @NotEmpty(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    @NotBlank(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    @Pattern(message = "Level must be expressed though stars and be between 1 and 10", regexp = "[*]{1,10}", groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    private String level;

    @NotEmpty(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    @NotBlank(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    private Integer members;

    public HeistRequirementCommand(SkillCommand skill, Integer members) {
        this.name = skill.getName();
        this.level = skill.getLevel();
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public SkillCommand getSkill(){
        return new SkillCommand(this.name, this.level);
    }

    public void setSkill(SkillCommand skill){
        this.name = skill.getName();
        this.level = skill.getLevel();
    }
}
