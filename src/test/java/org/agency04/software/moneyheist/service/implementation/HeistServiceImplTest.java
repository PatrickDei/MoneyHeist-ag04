package org.agency04.software.moneyheist.service.implementation;

import org.agency04.software.moneyheist.entity.heist.Heist;
import org.agency04.software.moneyheist.entity.heist.HeistStatus;
import org.agency04.software.moneyheist.entity.member.Member;
import org.agency04.software.moneyheist.entity.member.MemberStatus;
import org.agency04.software.moneyheist.entity.requirement.HeistRequirement;
import org.agency04.software.moneyheist.entity.skill.Skill;
import org.agency04.software.moneyheist.repository.HeistRepository;
import org.agency04.software.moneyheist.repository.MemberRepository;
import org.agency04.software.moneyheist.service.HeistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class HeistServiceImplTest {

    @Autowired
    private HeistService heistService;

    @MockBean
    private HeistRepository heistRepository;

    @MockBean
    private MemberRepository memberRepository;

    @Test
    void getEligibleHeistMembers() {
        final Integer id = 1;

        heistService.getEligibleHeistMembers(id);

        Mockito.verify(heistRepository).findById(id);
    }

    @Test
    void getHeistStatus() {
        final Integer id = 1;

        when(heistRepository.findById(id))
                .thenReturn(
                        Optional.of(new Heist("heist", "Zagreb", new Date(), new Date(), null, HeistStatus.PLANNING, null))
                );

        HeistStatus status = heistService.getHeistStatus(id);

        Assertions.assertNotNull(status);
        Assertions.assertEquals(HeistStatus.PLANNING, status);
    }

    @Test
    void heistCanBeStarted() {
        final Integer validId = 1;
        final Integer invalidId = 2;

        when(heistRepository.findById(validId))
                .thenReturn(Optional.of(new Heist(null, null, null, null, null, HeistStatus.READY, null)));

        when(heistRepository.findById(invalidId))
                .thenReturn(Optional.of(new Heist(null, null, null, null, null, HeistStatus.PLANNING, null)));

        Assertions.assertTrue(heistService.heistCanBeStarted(validId));
        Assertions.assertFalse(heistService.heistCanBeStarted(invalidId));
    }

    @Test
    void fieldValueExists() {
        final String fieldName = "name";
        Object value = "first heist";

        heistService.fieldValueExists(value, fieldName);

        verify(heistRepository).existsByName(value.toString());
    }

    @Test
    void memberIsValid() {
        final Integer id = 1;
        final String name = "bob";

        when(heistRepository.findById(id))
                .thenReturn(
                        Optional.of(new Heist("heist", "Zagreb", new Date(), new Date(),
                                Set.of(new HeistRequirement(new Skill("Hacking", 1), 1)), HeistStatus.PLANNING, null))
                );

        when(memberRepository.findFirstByNameIgnoreCase(name))
                .thenReturn(
                        Optional.of(new Member("bob", "M", "bobby@ag04.com", Set.of(new Skill("Hacking", 3)), null, MemberStatus.AVAILABLE, null, null))
                );

        Assertions.assertTrue(heistService.memberIsValid(name, id));
    }
}