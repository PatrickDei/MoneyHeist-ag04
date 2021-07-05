package org.agency04.software.moneyheist.skills;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {

    List<Skill> findAll();

    Skill findFirstByName(String name);
}
