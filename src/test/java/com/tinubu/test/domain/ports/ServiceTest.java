package com.tinubu.test.domain.ports;

import com.tinubu.test.PolicyRepositoryInMemory;
import com.tinubu.test.domain.model.Policy;
import com.tinubu.test.domain.model.PolicyStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceTest {


    @Autowired
    private PolicyService policyService;
    @MockBean
    private PolicyRepository policyRepository;

    @Test
    public void test_get_all_service() {
        when(policyRepository.getAll()).thenReturn(new PolicyRepositoryInMemory().getAll());
        List<Policy> allPolicies = policyService.getAllPolicies();
        assertThat(allPolicies.size()).isEqualTo(5);
        assertThat(allPolicies.stream().map(Policy::getId)).contains(1, 2, 3, 4, 5);

    }

    @Test
    public void test_create_service() {
        Policy policy = new Policy();
        when(policyRepository.save(policy)).thenReturn(new PolicyRepositoryInMemory().save(policy));
        Integer policyId = policyService.createPolicy("policy1", LocalDate.of(2022, 6, 11), LocalDate.of(2022, 7, 19), "ACTIVE");
        assertThat(policyId).isEqualTo(0);

    }

    @Test
    public void test_update_service() {
        Policy policy = new Policy(1, "policy1", LocalDate.of(2021, 1, 12),
                LocalDate.of(2025, 12, 15), LocalDate.of(2021, 10, 10),
                LocalDate.of(2022, 1, 11), PolicyStatus.ACTIVE);
        when(policyRepository.findById(policy.getId())).thenReturn(new PolicyRepositoryInMemory().findById(policy.getId()));
        Integer policyId = policyService.updatePolicy(1, "policy1", LocalDate.of(2022, 6, 11), LocalDate.of(2022, 7, 19), "ACTIVE");
        assertThat(policyId).isEqualTo(0);

    }
}
