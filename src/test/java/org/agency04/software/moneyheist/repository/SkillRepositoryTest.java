package org.agency04.software.moneyheist.repository;

import org.agency04.software.moneyheist.entity.skill.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SkillRepositoryTest {

    @Autowired
    private SkillRepository skillRepository;

    @Test
    void findAll() {
        List<Skill> skills = skillRepository.findAll();

        Assertions.assertNotNull(skills);
        Assertions.assertEquals(skills.size(), 25);
    }
}