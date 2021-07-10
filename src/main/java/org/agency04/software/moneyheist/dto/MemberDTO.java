package org.agency04.software.moneyheist.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.agency04.software.moneyheist.entities.member.MemberStatus;
import org.agency04.software.moneyheist.groups_and_views.View;

import java.util.Set;

public class MemberDTO {

    @JsonView({View.EligibleMembers.class, View.HeistMembersOnly.class})
    private String name;

    private String sex;

    private String email;

    @JsonView({View.MemberSkills.class, View.EligibleMembers.class, View.HeistMembersOnly.class})
    private Set<SkillDTO> skills;

    @JsonView(View.MemberSkills.class)
    private String mainSkill;

    private MemberStatus status;

    public MemberDTO(String name, String sex, String email, Set<SkillDTO> skills, String mainSkill, MemberStatus status) {
        this.name = name;
        this.sex = sex;
        this.skills = skills;
        this.mainSkill = mainSkill;
        this.email = email;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }
}
