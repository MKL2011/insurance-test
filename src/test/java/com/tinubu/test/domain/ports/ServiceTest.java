package com.tinubu.test.domain.ports;

import com.tinubu.test.PolicyRepositoryInMemory;
import com.tinubu.test.domain.exception.InvalidPolicyException;
import com.tinubu.test.domain.exception.PolicyNotFoundException;
import com.tinubu.test.domain.model.Policy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@SpringBootTest
public class ServiceTest {

    private final PolicyService policyService = new PolicyService(new PolicyRepositoryInMemory());

    @Test
    public void test_get_all_service() {
        List<Policy> allPolicies = policyService.getAllPolicies();
        assertThat(allPolicies.size()).isEqualTo(5);
        assertThat(allPolicies.stream().map(Policy::id)).contains(1, 2, 3, 4, 5);

    }

    @Test
    public void test_create_service() {
        Integer policyId = policyService.createPolicy("policy1", LocalDate.of(2022, 6, 11), LocalDate.of(2022, 7, 19), "ACTIVE");
        assertThat(policyId).isEqualTo(0);

    }

    @Test
    public void test_update_service() {
        Integer policyId = policyService.updatePolicy(1, "policy1", LocalDate.of(2022, 6, 11), LocalDate.of(2022, 7, 19), "ACTIVE");
        assertThat(policyId).isEqualTo(0);

    }

    @Test
    public void test_find_by_id_service() {
        Policy foundPolicy = policyService.findPolicyById(1);
        assertThat(foundPolicy.id()).isEqualTo(1);

    }

    @Test
    public void test_invalid_status() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Integer policyId = policyService.createPolicy("policy1", LocalDate.of(2022, 6, 11), LocalDate.of(2022, 7, 19), "ACTIVEE");
        });

        String expectedMessage = "PolicyStatus";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_invalid_policy_date() {
        Exception exception = assertThrows(InvalidPolicyException.class, () -> {
            Integer policyId = policyService.createPolicy("policy1", LocalDate.of(2025, 6, 11), LocalDate.of(2022, 7, 19), "ACTIVE");
        });

        String expectedMessage = "Policy is invalid";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void test_policy_not_found() {
        Exception exception = assertThrows(PolicyNotFoundException.class, () -> {
            Policy policy=policyService.findPolicyById(10);
        });

        String expectedMessage = "Policy with this id is not found";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(actualMessage, expectedMessage);
    }

}
