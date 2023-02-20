package com.tinubu.test.adapters.api;

import com.tinubu.test.domain.ports.PolicyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyFacade {
    private final PolicyService policyService;

    public PolicyFacade(PolicyService policyService) {
        this.policyService = policyService;
    }

    public List<PolicyResponse> getAllPolicies() {
        return policyService.getAllPolicies().stream().map(policy -> new PolicyResponse(policy.getId(), policy.getName(),
                policy.getStartCoverDate(), policy.getEndCoverDate(), policy.getCreationDate(), policy.getUpdateDate(),
                policy.getStatus().name())).collect(Collectors.toList());
    }
}
