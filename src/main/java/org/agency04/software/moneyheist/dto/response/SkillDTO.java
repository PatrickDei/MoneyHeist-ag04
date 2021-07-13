package org.agency04.software.moneyheist.dto.response;

import com.fasterxml.jackson.annotation.JsonView;
import org.agency04.software.moneyheist.group.Group;

public class SkillDTO {

    @JsonView({Group.JsonViewGroup.MemberSkills.class, Group.JsonViewGroup.EligibleMembers.class})
    private String name;

    @JsonView({Group.JsonViewGroup.MemberSkills.class, Group.JsonViewGroup.EligibleMembers.class})
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
