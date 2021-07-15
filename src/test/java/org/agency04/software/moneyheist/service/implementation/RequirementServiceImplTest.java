package org.agency04.software.moneyheist.service.implementation;

import org.agency04.software.moneyheist.entity.requirement.HeistRequirement;
import org.agency04.software.moneyheist.entity.skill.Skill;
import org.agency04.software.moneyheist.repository.RequirementRepository;
import org.agency04.software.moneyheist.service.RequirementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class RequirementServiceImplTest {

    @Autowired
    private RequirementService requirementService;

    @MockBean
    private RequirementRepository requirementRepository;

    @Test
    void findAll() {
        List<HeistRequirement> mockedRequirements = Arrays.asList(
                new HeistRequirement(new Skill("Hacking", 1), 1),
                new HeistRequirement(new Skill("Driving", 10), 10));

        when(requirementRepository.findAll())
                .thenReturn(mockedRequirements);

        List<HeistRequirement> requirements = requirementService.findAll();

        Assertions.assertNotNull(requirements);
        Assertions.assertEquals(requirements.size(), 2);
    }
}