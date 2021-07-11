package org.agency04.software.moneyheist.entities.skill;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column()
    private String name;

    @Column()
    private Integer level;

    Skill(){}

    public Skill(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSkillLevel() {
        return level;
    }

    public void setSkillLevel(Integer level) {
        if(level > 10)
            level = 10;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return name.equals(skill.name) && level.equals(skill.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }
}