package com.tinubu.test.adapters.api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PolicyRestController {
    private final PolicyFacade policies;

    public PolicyRestController(PolicyFacade policies) {
        this.policies = policies;
    }

    @RequestMapping(value = "/policies", method = RequestMethod.GET)
    public List<PolicyResponse> getPolicies() {
        return policies.getAllPolicies();
    }

    @RequestMapping(value="/create-policy", method = RequestMethod.POST)
    public Integer createPolicy(@RequestBody PolicyRequest policyRequest){
        return policies.createPolicy(policyRequest);
    }

    @RequestMapping(value="/update-policy", method = RequestMethod.PUT)
    public Integer updatePolicy(@RequestBody PolicyRequest policyRequest){
        return policies.update(policyRequest);
    }
}
