package org.agency04.software.moneyheist.entities.requirement;


import org.agency04.software.moneyheist.entities.skill.Skill;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Heist_requirement")
public class HeistRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(name = "number_of_members")
    private Integer numberOfMembers;

    public HeistRequirement(){}

    public HeistRequirement(Skill skill, Integer numberOfMembers) {
        this.skill = skill;
        this.numberOfMembers = numberOfMembers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(Integer numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeistRequirement that = (HeistRequirement) o;
        return skill.equals(that.skill) && numberOfMembers.equals(that.numberOfMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill, numberOfMembers);
    }
}