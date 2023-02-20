package com.tinubu.test.adapters.api;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @Test
    public void test_get_all_policies() throws Exception {
        when(policies.getAllPolicies()).thenReturn(List.of(new PolicyResponse(1, "policy1",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE")));
        this.mockMvc.perform(get("/policies")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")));

    }
}
