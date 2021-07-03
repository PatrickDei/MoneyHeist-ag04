package org.agency04.software.moneyheist.heist.skills;

import org.agency04.software.moneyheist.heist.members.Member;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Skill")
@IdClass(SkillId.class)
public class Skill {

    @Id
    private String name;

    @Id
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
