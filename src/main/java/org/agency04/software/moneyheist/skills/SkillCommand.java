package org.agency04.software.moneyheist.skills;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class SkillCommand {

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    @Pattern(message = "Level must be expressed though stars and be between 1 and 10", regexp = "^[*]{1,10}$")
    private String level;

    public SkillCommand(String name, String level) {
        this.name = name;
        this.level = level;
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
}
