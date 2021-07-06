package org.agency04.software.moneyheist.entities.skill;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Skill")
@IdClass(SkillId.class)
public class Skill {

    @Id
    @Column(unique = true)
    private String name;

    @Id
    @Column(unique = true)
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

// needed for composite primary key
class SkillId implements Serializable {

    private String name;

    private Integer level;

    SkillId(){}

    public SkillId(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillId skillId = (SkillId) o;
        return name.equals(skillId.name) && level.equals(skillId.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }
}