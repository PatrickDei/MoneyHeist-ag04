package org.agency04.software.moneyheist.heist.skills;

public class SkillDTO {

    private String name;

    private String level;

    public SkillDTO(String name, String level) {
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
