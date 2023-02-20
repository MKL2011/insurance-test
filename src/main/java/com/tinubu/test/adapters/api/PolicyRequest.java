package com.tinubu.test.adapters.api;

import java.time.LocalDate;

public record PolicyRequest(Integer id, String name, LocalDate startCoverDate, LocalDate endCoverDate, String status){

    public static final class PolicyRequestBuilder {

        Integer id;
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

        public PolicyRequestBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public PolicyRequest build() {
            return new PolicyRequest(id, name, startCoverDate, endCoverDate, status);
        }
    }
}
