package org.agency04.software.moneyheist.heists.requirements.validation;

import org.agency04.software.moneyheist.skills.validation.SkillCommand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class HeistRequirementCommand {

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String level;

    @NotEmpty
    @NotBlank
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
