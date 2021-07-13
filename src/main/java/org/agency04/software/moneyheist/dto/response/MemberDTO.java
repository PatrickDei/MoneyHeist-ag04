package org.agency04.software.moneyheist.dto.response;

import com.fasterxml.jackson.annotation.JsonView;
import org.agency04.software.moneyheist.entity.member.MemberStatus;
import org.agency04.software.moneyheist.group.Group;

import java.util.Set;

public class MemberDTO {

    @JsonView(Group.JsonViewGroup.EligibleMembers.class)
    private String name;

    private String sex;

    private String email;

    @JsonView({Group.JsonViewGroup.MemberSkills.class, Group.JsonViewGroup.EligibleMembers.class})
    private Set<SkillDTO> skills;

    @JsonView(Group.JsonViewGroup.MemberSkills.class)
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
