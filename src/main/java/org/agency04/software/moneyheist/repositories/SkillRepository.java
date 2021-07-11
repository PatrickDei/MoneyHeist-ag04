package org.agency04.software.moneyheist.repositories;

import org.agency04.software.moneyheist.entities.skill.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {

    List<Skill> findAll();
}
