package org.agency04.software.moneyheist.dto;

import org.agency04.software.moneyheist.entities.member.MemberStatus;

import java.util.Set;

public class MemberDTO {

    private Integer id;

    private String name;

    private Set<SkillDTO> skills;

    private String mainSkill;

    private String email;

    private MemberStatus status;

    public MemberDTO(Integer id, String name, Set<SkillDTO> skills, String mainSkill, String email, MemberStatus status) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.mainSkill = mainSkill;
        this.email = email;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }
}
