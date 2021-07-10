package org.agency04.software.moneyheist.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.agency04.software.moneyheist.groups_and_views.View;

public class HeistRequirementDTO {

    @JsonView({View.BasicHeistInfo.class, View.EligibleMembers.class})
    private String name;

    @JsonView({View.BasicHeistInfo.class, View.EligibleMembers.class})
    private String level;

    @JsonView({View.BasicHeistInfo.class, View.EligibleMembers.class})
    private Integer members;

    public HeistRequirementDTO(SkillDTO skills, Integer members) {
        this.name = skills.getName();
        this.level = skills.getLevel();
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
}
