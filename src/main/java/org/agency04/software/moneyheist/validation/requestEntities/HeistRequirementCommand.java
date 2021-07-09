package org.agency04.software.moneyheist.validation.requestEntities;

import org.agency04.software.moneyheist.groups.OnlySkillsRequired;
import org.agency04.software.moneyheist.groups.WholeObjectRequired;

import javax.validation.constraints.*;

public class HeistRequirementCommand {

    @NotEmpty(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    @NotBlank(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    private String name;

    @NotEmpty(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    @NotBlank(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    @Pattern(message = "Level must be expressed though stars and be between 1 and 10", regexp = "[*]{1,10}", groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    private String level;

    @NotNull(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    @Positive(groups = {WholeObjectRequired.class, OnlySkillsRequired.class})
    private Integer members;

    public HeistRequirementCommand(String name, String level, Integer members) {
        this.name = name;
        this.level = level;
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
