package org.agency04.software.moneyheist.service.implementation;

import org.agency04.software.moneyheist.entity.skill.Skill;
import org.agency04.software.moneyheist.repository.SkillRepository;
import org.agency04.software.moneyheist.service.SkillService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class SkillServiceImplTest {

    @Autowired
    private SkillService skillService;

    @MockBean
    private SkillRepository skillRepository;

    @Test
    void findAll() {
        List<Skill> mockedSkills = Arrays.asList(
                new Skill("Hacking", 1),
                new Skill("Driving", 10));

        when(skillRepository.findAll())
                .thenReturn(mockedSkills);

        List<Skill> skills = skillService.findAll();

        Assertions.assertNotNull(skills);
        Assertions.assertEquals(skills.size(), 2);
    }
}