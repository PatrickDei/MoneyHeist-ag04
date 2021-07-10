package org.agency04.software.moneyheist.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.agency04.software.moneyheist.groups_and_views.View;

public class SkillDTO {

    @JsonView({View.MemberSkills.class, View.EligibleMembers.class, View.HeistMembersOnly.class})
    private String name;

    @JsonView({View.MemberSkills.class, View.EligibleMembers.class, View.HeistMembersOnly.class})
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
