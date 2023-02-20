package com.tinubu.test.adapters.api;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PolicyFacade policies;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test_get_all_policies() throws Exception {
        List<PolicyResponse> policyResponseList = List.of(new PolicyResponse(1, "policy1",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE"));
        when(policies.getAllPolicies()).thenReturn(policyResponseList);
        this.mockMvc.perform(get("/policies")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")));

    }

    @Test
    public void test_create_policy() throws Exception {
        PolicyRequest policyRequest = new PolicyRequest.PolicyRequestBuilder("policy1", LocalDate.of(2021, 1, 12),
                LocalDate.of(2025, 12, 15), "ACTIVE").build();
        this.mockMvc.perform(post("/create-policy").content(objectMapper.writeValueAsString(policyRequest))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void test_update_policy() throws Exception {
        PolicyRequest policyRequest = new PolicyRequest.PolicyRequestBuilder("policy1", LocalDate.of(2021, 1, 12),
                LocalDate.of(2025, 12, 15), "ACTIVE").id(1).build();
        this.mockMvc.perform(put("/update-policy").content(objectMapper.writeValueAsString(policyRequest))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void test_get_policy() throws Exception {
        PolicyResponse policyResponse=new PolicyResponse(1, "policy1",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE");
        when(policies.findById(1)).thenReturn(policyResponse);
        this.mockMvc.perform(get("/policies/1")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")));
    }
}
