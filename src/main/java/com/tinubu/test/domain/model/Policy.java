package com.tinubu.test.domain.model;


import org.springframework.lang.NonNull;

import java.time.LocalDate;

public record Policy(@NonNull Integer id, @NonNull String name, @NonNull LocalDate startCoverDate,
                     @NonNull LocalDate endCoverDate,
                     @NonNull LocalDate creationDate, @NonNull LocalDate updateDate, @NonNull PolicyStatus status) {

    public static final class PolicyBuilder {

        Integer id;
        String name;
        LocalDate startCoverDate;
        LocalDate endCoverDate;
        LocalDate creationDate;
        LocalDate updateDate;
        PolicyStatus status;

        public PolicyBuilder(String name, PolicyStatus status) {
            this.name = name;
            this.status = status;
        }

        public PolicyBuilder(String name, LocalDate startCoverDate, LocalDate endCoverDate, PolicyStatus status, LocalDate creationDate, LocalDate updateDate) {
            this.name = name;
            this.startCoverDate = startCoverDate;
            this.endCoverDate = endCoverDate;
            this.status = status;
            this.creationDate = creationDate;
            this.updateDate = updateDate;
        }

        public Policy.PolicyBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public Policy build() {
            return new Policy(id, name, startCoverDate, endCoverDate, creationDate, updateDate, status);
        }
    }

    public boolean isValid(){
        return isNameValid() && isStatusValid() && isCreationDateValid() &&
                isUpdateDateValid() && isStartCoverDateValid() && isEndCoverDateValid();
    }

    public boolean isNameValid() {
        return name != null && !name.isBlank();
    }

    public boolean isStatusValid() {
        return status != null;
    }

    public boolean isCreationDateValid() {
        return creationDate != null;
    }

    public boolean isUpdateDateValid() {
        return updateDate != null;
    }

    public boolean isStartCoverDateValid() {
        return startCoverDate != null;
    }

    public boolean isEndCoverDateValid() {
        return endCoverDate != null && endCoverDate.isAfter(startCoverDate);
    }
}