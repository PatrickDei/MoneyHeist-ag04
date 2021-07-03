package org.agency04.software.moneyheist.heist.skills;

import java.io.Serializable;
import java.util.Objects;

public class SkillId implements Serializable {

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
