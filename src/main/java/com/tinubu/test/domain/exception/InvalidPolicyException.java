package com.tinubu.test.domain.exception;

public class InvalidPolicyException extends IllegalStateException {
    public InvalidPolicyException() {
        super("Policy is invalid");
    }
}
