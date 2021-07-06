package org.agency04.software.moneyheist.heists.requirements;


import org.agency04.software.moneyheist.skills.Skill;

import javax.persistence.*;

@Entity
@Table(name = "Heist_requirement")
public class HeistRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "Heist_requirement_Skill",
            joinColumns = @JoinColumn(name = "requirement_id"),
            inverseJoinColumns = {@JoinColumn(name = "level"), @JoinColumn(name = "name")}
    )
    private Skill skill;

    @Column(name = "number_of_members")
    private Integer numberOfMembers;

    public HeistRequirement(Integer id, Skill skill, Integer numberOfMembers) {
        this.id = id;
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
}