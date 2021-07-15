package org.agency04.software.moneyheist.repository;

import org.agency04.software.moneyheist.entity.requirement.HeistRequirement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RequirementRepositoryTest {

    @Autowired
    private RequirementRepository requirementRepository;

    @Test
    void findAll() {
        List<HeistRequirement> requirements = requirementRepository.findAll();

        Assertions.assertNotNull(requirements);
        Assertions.assertEquals(requirements.size(), 20);
    }

    @Test
    void findRequirementsForHeist() {
        List<HeistRequirement> requirements = requirementRepository.findRequirementsForHeist(1);

        Assertions.assertNotNull(requirements);
        Assertions.assertEquals(requirements.size(), 1);
        Assertions.assertTrue(requirements.stream().anyMatch(r -> r.getId().equals(1)));
    }
}