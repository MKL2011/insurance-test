package com.tinubu.test.domain.ports;

import com.tinubu.test.PolicyRepositoryInMemory;
import com.tinubu.test.domain.model.Policy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        assertThat(allPolicies.stream().map(Policy::getId)).contains(1,2,3,4,5);

    }
}
