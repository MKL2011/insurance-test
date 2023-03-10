package com.tinubu.test.domain.ports;

import com.tinubu.test.domain.exception.InvalidPolicyException;
import com.tinubu.test.domain.model.Policy;
import com.tinubu.test.domain.model.PolicyStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PolicyService {
    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.getAll();
    }

    public Integer createPolicy(String name, LocalDate startCoverDate, LocalDate endCoverDate, String status) {
        Policy policy = new Policy.PolicyBuilder(name, startCoverDate, endCoverDate, PolicyStatus.valueOf(status),
                LocalDate.now(), LocalDate.now())
                .build();
        if (policy.isValid())
            return policyRepository.save(policy);
        else {
            throw new InvalidPolicyException();
        }
    }

    public Integer updatePolicy(Integer id, String name, LocalDate startCoverDate, LocalDate endCoverDate, String status) {
        Policy existingPolicy = policyRepository.findById(id);
        Policy policy = new Policy(id, name, startCoverDate, endCoverDate, existingPolicy.creationDate(), LocalDate.now(), PolicyStatus.valueOf(status));
        if (policy.isValid())
            return policyRepository.update(policy);
        else {
            throw new InvalidPolicyException();
        }
    }

    public Policy findPolicyById(Integer id) {
        return policyRepository.findById(id);
    }
}
