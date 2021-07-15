package org.agency04.software.moneyheist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.agency04.software.moneyheist.dto.response.HeistDTO;
import org.agency04.software.moneyheist.entity.heist.HeistOutcome;
import org.agency04.software.moneyheist.entity.heist.HeistStatus;
import org.agency04.software.moneyheist.service.HeistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
class HeistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeistService heistService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllHeists() throws Exception {
        mockMvc.perform(
                get("/heist")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getHeistMembers() throws Exception {
        final Integer invalidId = 1;
        final Integer id = 2;
        when(heistService.findHeist(invalidId))
                .thenReturn(Optional.of(new HeistDTO("name", "location", null, null, null, null, HeistStatus.PLANNING, null)));
        when(heistService.findHeist(id))
                .thenReturn(Optional.of(new HeistDTO("name", "location", null, null, null, null, HeistStatus.READY, null)));

        mockMvc.perform(
                get("/heist/" + id + "/members")
        ).andExpect(status().isOk());

        mockMvc.perform(
                get("/heist/" + invalidId + "/members")
        ).andExpect(status().isMethodNotAllowed());
    }

    @Test
    void getHeistOutcome() throws Exception {
        final Integer invalidId = 1;
        final Integer id = 2;

        when(heistService.findHeist(invalidId))
                .thenReturn(Optional.of(new HeistDTO("name", "location", null, null, null, null, HeistStatus.PLANNING, null)));

        when(heistService.findHeist(id))
                .thenReturn(Optional.of(new HeistDTO("name", "location", null, null, null, null, HeistStatus.READY, HeistOutcome.SUCCEEDED)));

        mockMvc.perform(
                get("/heist/" + id + "/outcome")
        ).andExpect(status().isOk());

        mockMvc.perform(
                get("/heist/" + invalidId + "/outcome")
        ).andExpect(status().isMethodNotAllowed());
    }

    @Test
    void getEligibleHeistMembers() throws Exception {
        final Integer invalidId = 1;
        final Integer id = 2;

        when(heistService.getHeistStatus(invalidId))
                .thenReturn(HeistStatus.FINISHED);

        when(heistService.getHeistStatus(id))
                .thenReturn(HeistStatus.PLANNING);

        when(heistService.getEligibleHeistMembers(id))
                .thenReturn(new HeistDTO("name", "location", null, null, null, null, HeistStatus.READY, HeistOutcome.SUCCEEDED));

        mockMvc.perform(
                get("/heist/" + id + "/eligible_members")
        ).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(
                get("/heist/" + invalidId + "/eligible_members")
        ).andExpect(status().isMethodNotAllowed());
    }
}