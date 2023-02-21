package com.tinubu.test.adapters.api;

import java.time.LocalDate;

public record PolicyRequest(String name, LocalDate startCoverDate, LocalDate endCoverDate, String status){

    public static final class PolicyRequestBuilder {

        String name;
        LocalDate startCoverDate;
        LocalDate endCoverDate;
        String status;

        public PolicyRequestBuilder(String name, LocalDate startCoverDate, LocalDate endCoverDate, String status) {
            this.name = name;
            this.startCoverDate = startCoverDate;
            this.endCoverDate = endCoverDate;
            this.status = status;
        }


        public PolicyRequest build() {
            return new PolicyRequest(name, startCoverDate, endCoverDate, status);
        }
    }
}
