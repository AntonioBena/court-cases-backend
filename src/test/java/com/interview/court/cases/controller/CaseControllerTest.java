package com.interview.court.cases.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.CourtDto;
import com.interview.court.cases.model.dto.requests.CaseAndCourtRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CaseControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();


    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "steve.jobs@apple.com", roles = "USER")
    void createCourtCase() throws Exception {

        var request = CaseAndCourtRequest.builder()
                        .courtCaseDto(
                                CourtCaseDto.builder()
                                        .caseLabel("case label")
                                        .description("case description")
                                        .build())
                        .courtDto(
                                CourtDto.builder()
                                        .courtAddress("address")
                                        .courtName("court name")
                                        .build()
                        ).build();

                var rest = mockMvc.perform(post("/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .content(mapper.writeValueAsString(request))
                                .header("Authorization", "Bearer " + "token12345648797897453fghtferad45g4r56hz4ads5f4g5ae46drger"))
                        .andExpect(
                                status()
                                .isCreated()
                        )
                        .andReturn()
                        .getResponse();
    }

    @Test
    void updateCourtCase() {
    }

    @org.springframework.context.annotation.Configuration
    public static class ContextConfiguration {
    }
}