package com.tinubu.test.domain;

public class PolicyNotFoundException extends IllegalStateException {
    public PolicyNotFoundException() {
        super("Policy with this id is not found");
    }
}
