package org.agency04.software.moneyheist.dto.request;

import org.agency04.software.moneyheist.group.Group;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class SkillCommand {

    @NotEmpty(groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    @NotBlank(groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    private final String name;

    @NotEmpty(groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    @NotBlank(groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    @Pattern(message = "Level must be expressed though stars and be between 1 and 10", regexp = "[*]{1,10}", groups = {Group.ValidationGroup.WholeObjectRequired.class, Group.ValidationGroup.OnlySkillsRequired.class})
    private final String level;

    public SkillCommand(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillCommand that = (SkillCommand) o;
        return name.equals(that.name) && level.equals(that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }
}
