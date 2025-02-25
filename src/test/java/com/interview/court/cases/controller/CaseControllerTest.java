package com.interview.court.cases.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.CourtDto;
import com.interview.court.cases.model.dto.requests.CaseAndCourtRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CaseControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private String token;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(post("/v1/auth/login")
                        .with(httpBasic("test@test", "123456789")));
        MvcResult mvcResult = resultActions.andDo(print()).andReturn();
        String stringContent = mvcResult.getResponse().getContentAsString();
        JSONObject json = new JSONObject(stringContent);
        token = "Bearer " + json.getJSONObject("data").getString("token");
    }

    @Test
    @WithMockUser(username = "test@test", roles = "USER")
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

                var rest = mockMvc.perform(post("/case/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                //.with(csrf())
                                .content(mapper.writeValueAsString(request))
                                .header("Authorization", token))
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