package org.agency04.software.moneyheist.dto.request;

import org.agency04.software.moneyheist.group.Group;

import javax.validation.constraints.*;

public class HeistRequirementCommand {

    @NotEmpty(groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    @NotBlank(groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    private final String name;

    @NotEmpty(groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    @NotBlank(groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    @Pattern(message = "Level must be expressed though stars and be between 1 and 10", regexp = "[*]{1,10}", groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    private final String level;

    @NotNull(groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    @Positive(groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    private final Integer members;

    public HeistRequirementCommand(String name, String level, Integer members) {
        this.name = name;
        this.level = level;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public Integer getMembers() {
        return members;
    }

    public SkillCommand getSkill(){
        return new SkillCommand(this.name, this.level);
    }
}
