package com.tinubu.test.domain.ports;

import com.tinubu.test.domain.InvalidPolicyException;
import com.tinubu.test.domain.model.Policy;
import com.tinubu.test.domain.model.PolicyStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyService {
    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<Policy> getAllPolicies(){
        return policyRepository.getAll().stream().map(policy -> new Policy(policy.getId(), policy.getName(),
                policy.getStartCoverDate(), policy.getEndCoverDate(), policy.getCreationDate(), policy.getUpdateDate(),
                PolicyStatus.valueOf(policy.getStatus()))).collect(Collectors.toList());
    }

    public Integer createPolicy(String name, LocalDate startCoverDate, LocalDate endCoverDate, String status){
        Policy policy = new Policy(name, startCoverDate, endCoverDate, PolicyStatus.valueOf(status));
       if(policy.isValid())
        return policyRepository.save(policy);
       else {
           throw new InvalidPolicyException();
       }
    }
}
