package org.agency04.software.moneyheist.heist.members;

import org.agency04.software.moneyheist.heist.skills.SkillDTO;

import java.util.Set;

public class MemberDTO {

    private String name;

    private Set<SkillDTO> skills;

    private String mainSkill;

    private String email;

    public MemberDTO(String name, Set<SkillDTO> skills, String mainSkill, String email) {
        this.name = name;
        this.skills = skills;
        this.mainSkill = mainSkill;
        this.email = email;
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

    public String getMainSkill() {
        return mainSkill;
    }

    public void setMainSkill(String mainSkill) {
        this.mainSkill = mainSkill;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
