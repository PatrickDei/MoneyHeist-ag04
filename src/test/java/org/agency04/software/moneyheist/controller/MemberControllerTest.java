package org.agency04.software.moneyheist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.agency04.software.moneyheist.dto.request.MemberCommand;
import org.agency04.software.moneyheist.dto.request.SkillCommand;
import org.agency04.software.moneyheist.entity.member.MemberStatus;
import org.agency04.software.moneyheist.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllMembers() throws Exception {
        mockMvc.perform(
                get("/member")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void saveNewMember() throws Exception {
        MemberCommand member = new MemberCommand("NewName", "F", "new@ag04.com", MemberStatus.AVAILABLE, Arrays.asList(new SkillCommand("deception", "*****"), new SkillCommand("hacking", "*")), "deception");

        mockMvc.perform(
                post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void updateMembersSkills() throws Exception {
        // no content request
        MemberCommand member = new MemberCommand(null, null, null, null, Arrays.asList(new SkillCommand("deception", "*****"), new SkillCommand("hacking", "*")), null);
        Integer id = 1;

        mockMvc.perform(
                put("/member/" + id.toString() + "/skills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent())
                .andExpect(header().exists("Location"));

        /*
        Integer invalidId = 123123;

        when(
                memberService.updateMemberSkills(invalidId, member)
        ).thenReturn(null);

        // not found check
        mockMvc.perform(
                put("/member/" + invalidId.toString() + "/skills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());*/
    }

    @Test
    void removeSkillFromMember() throws Exception {
        final int id = 1;
        final String name = "bob";
        final String invalidName = "invalid";

        mockMvc.perform(
                delete("/member/" + invalidName + "/skills" + Integer.toString(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());

        when(memberService.removeSkillFromMember(id, name))
                .thenReturn(1);

        mockMvc.perform(
                delete("/member/" + name + "/skills" + Integer.toString(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
}