package org.agency04.software.moneyheist.heist.members;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.agency04.software.moneyheist.entity.member.MemberStatus;
import org.agency04.software.moneyheist.repository.MemberRepository;
import org.agency04.software.moneyheist.service.MemberService;
import org.agency04.software.moneyheist.dto.request.MemberCommand;
import org.agency04.software.moneyheist.dto.request.SkillCommand;
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



@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @MockBean
    MemberRepository memberRepository;

    @Autowired
    ObjectMapper objectMapper;

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

        //Member updatedMember = memberRepository.findById(id).orElse(null);
        //Assertions.assertNotNull(updatedMember);
        //Assertions.assertEquals(updatedMember.getSkills(), Set.of(new Skill("deception", 5), new Skill("hacking", 1)));

        String invalidId = "123123";

        // not found check
        mockMvc.perform(
                post("/member/" + invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
}