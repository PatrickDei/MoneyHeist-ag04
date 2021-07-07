package org.agency04.software.moneyheist.entities.heist.requirement;


import org.agency04.software.moneyheist.entities.skill.Skill;

import javax.persistence.*;

@Entity
@Table(name = "Heist_requirement")
public class HeistRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "level")
    @JoinColumn(name = "name")
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
}