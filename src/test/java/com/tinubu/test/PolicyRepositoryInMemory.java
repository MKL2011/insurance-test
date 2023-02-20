package com.tinubu.test;

import com.tinubu.test.domain.model.Policy;
import com.tinubu.test.domain.ports.PolicyRepository;

import java.time.LocalDate;
import java.util.List;

import static com.tinubu.test.domain.model.PolicyStatus.ACTIVE;
import static com.tinubu.test.domain.model.PolicyStatus.INACTIVE;

public class PolicyRepositoryInMemory implements PolicyRepository {
    @Override
    public List<Policy> getAll() {
        return List.of(
                new Policy(1, "policy1", LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15), LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), ACTIVE),
                new Policy(2, "policy2", LocalDate.of(2021, 1, 1), LocalDate.of(2028, 10, 18), LocalDate.of(2021, 12, 3), LocalDate.of(2022, 2, 9), ACTIVE),
                new Policy(3, "policy3", LocalDate.of(2021, 12, 10), LocalDate.of(2029, 11, 29), LocalDate.of(2021, 2, 6), LocalDate.of(2022, 10, 2), INACTIVE),
                new Policy(4, "policy4", LocalDate.of(2021, 2, 15), LocalDate.of(2030, 1, 3), LocalDate.of(2021, 5, 19), LocalDate.of(2022, 12, 25), ACTIVE),
                new Policy(5, "policy5", LocalDate.of(2021, 10, 22), LocalDate.of(2032, 9, 9), LocalDate.of(2021, 7, 27), LocalDate.of(2022, 7, 13), INACTIVE)
        );
    }

    @Override
    public Policy findById(Integer id) {
        return getAll().stream().filter(policy -> policy.getId() == id).findFirst().get();
    }

    @Override
    public Integer save(Policy policy) {
        return 0;
    }
}
