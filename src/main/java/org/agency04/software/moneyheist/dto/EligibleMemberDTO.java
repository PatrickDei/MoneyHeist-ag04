package org.agency04.software.moneyheist.dto;

import java.util.Set;

public class EligibleMemberDTO {
    private String name;

    private Set<SkillDTO> skills;

    public EligibleMemberDTO(String name, Set<SkillDTO> skills) {
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
