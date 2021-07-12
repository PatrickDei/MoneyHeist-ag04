package org.agency04.software.moneyheist.entity.member;

import org.agency04.software.moneyheist.entity.skill.Skill;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "email", unique = true)
    private String email;

    @JoinColumn(name = "main_Skill")
    private String mainSkill;

    @ManyToMany( cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Member_Skill",
            joinColumns = @JoinColumn(name = "member_Id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Column(name = "password")
    private String password;

    @ManyToOne( cascade = {CascadeType.ALL})
    @JoinColumn(name = "role_id")
    private Role role;

    public Member(){}

    public Member(String name, String sex, String email, Set<Skill> skills, String mainSkill, MemberStatus memberStatus, String password, Role role) {
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.mainSkill = mainSkill;
        this.skills = skills;
        this.memberStatus = memberStatus;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
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

    public String getMainSkill() {
        return mainSkill;
    }

    public void setMainSkill(String mainSkill) {
            this.mainSkill = mainSkill;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public MemberStatus getStatus() {
        return memberStatus;
    }

    public void setStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return email.equals(member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public void updateSkills(List<Skill> newSkills){
        for(Skill s : newSkills){
            if(skills.stream()
                    .map(Skill::getName).collect(Collectors.toList())
                    .contains(s.getName()))
            {
                skills.remove(
                        skills.stream()
                                .filter(skill -> skill.getName().equals(s.getName()))
                                .findFirst()
                                .orElse(null));
            }

            skills.add(s);
        }
    }
}