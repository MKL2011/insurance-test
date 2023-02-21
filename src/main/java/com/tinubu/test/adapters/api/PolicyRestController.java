package com.tinubu.test.adapters.api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PolicyRestController {
    private final PolicyFacade policies;

    public PolicyRestController(PolicyFacade policies) {
        this.policies = policies;
    }

    @CrossOrigin
    @RequestMapping(value = "/policies", method = RequestMethod.GET)
    public List<PolicyResponse> getPolicies() {
        return policies.getAllPolicies();
    }
    @CrossOrigin
    @RequestMapping(value="/policies", method = RequestMethod.POST)
    public Integer createPolicy(@RequestBody PolicyRequest policyRequest){
        return policies.createPolicy(policyRequest);
    }

    @CrossOrigin
    @RequestMapping(value="/policies/{id}", method = RequestMethod.PUT)
    public Integer updatePolicy(@RequestBody PolicyRequest policyRequest, @PathVariable Integer id){
        return policies.update(policyRequest, id);
    }
    @CrossOrigin
    @RequestMapping(value="/policies/{id}", method = RequestMethod.GET)
    public PolicyResponse getPolicy(@PathVariable Integer id) {
        return policies.findById(id);
    }
}
