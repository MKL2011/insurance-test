package com.tinubu.test.adapters.api;
import com.tinubu.test.domain.exception.InvalidPolicyException;
import com.tinubu.test.domain.exception.PolicyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(PolicyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handlePolicyNotFoundException(
            PolicyNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(InvalidPolicyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidPolicyException(
            InvalidPolicyException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(
            IllegalArgumentException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Policy Status should be ACTIVE or INACTIVE");
    }

}
