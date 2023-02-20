package com.tinubu.test.domain;

public class InvalidPolicyException extends IllegalStateException {
    public InvalidPolicyException() {
        super("Policy is invalid");

    }
}
