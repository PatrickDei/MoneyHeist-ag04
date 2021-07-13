package org.agency04.software.moneyheist.service;

import org.agency04.software.moneyheist.entity.skill.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> findAll();
}
