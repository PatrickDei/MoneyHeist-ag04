package org.agency04.software.moneyheist.repository;

import org.agency04.software.moneyheist.entity.heist.Heist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class HeistRepositoryTest {

    @Autowired
    private HeistRepository heistRepository;

    @Test
    void findAll() {
        List<Heist> heists = heistRepository.findAll();

        Assertions.assertNotNull(heists);
        Assertions.assertEquals(heists.size(), 10);
    }

    @Test
    void findByOutcomeIsNull() {
        List<Heist> heists = heistRepository.findByOutcomeIsNull();

        Assertions.assertNotNull(heists);
        Assertions.assertEquals(heists.size(), 10);
        for(Heist h : heists)
            Assertions.assertNull(h.getOutcome());
    }

    @Test
    void existsByName() {
        boolean heistExists = heistRepository.existsByName("First heist");

        Assertions.assertTrue(heistExists);
    }

    @Test
    @Transactional
    @DirtiesContext
    void insertMemberIntoHeists() {
        heistRepository.insertMemberIntoHeists(1, 1);

        Optional<Heist> heist = heistRepository.findById(1);
        Assertions.assertTrue(heist.get().getMembers().stream().anyMatch(m -> m.getId().equals(1)));
    }
}