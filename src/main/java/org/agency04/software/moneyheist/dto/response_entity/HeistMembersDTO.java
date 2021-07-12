package org.agency04.software.moneyheist.dto.response_entity;

import java.util.Set;

public class HeistMembersDTO {

    private String name;

    private Set<SkillDTO> skills;

    public HeistMembersDTO(String name, Set<SkillDTO> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillDTO> skills) {
        this.skills = skills;
    }
}
