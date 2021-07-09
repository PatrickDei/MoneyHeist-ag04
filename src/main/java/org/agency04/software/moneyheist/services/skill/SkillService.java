package org.agency04.software.moneyheist.services.skill;

import org.agency04.software.moneyheist.entities.skill.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> findAll();
}
