package com.tinubu.test.domain.model;


import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Policy {
    private Integer id;
    private String name;
    private LocalDate startCoverDate;
    private LocalDate endCoverDate;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private PolicyStatus status;

    public Policy() {
    }

    public Policy(Integer id, String name, LocalDate startCoverDate, LocalDate endCoverDate, LocalDate creationDate, LocalDate updateDate, PolicyStatus status) {
        this.id = id;
        this.name = name;
        this.startCoverDate = startCoverDate;
        this.endCoverDate = endCoverDate;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.status = status;
    }

    public Policy(String name, LocalDate startCoverDate, LocalDate endCoverDate, PolicyStatus status) {
        this.id = new AtomicInteger().incrementAndGet();
        this.name = name;
        this.startCoverDate = startCoverDate;
        this.endCoverDate = endCoverDate;
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartCoverDate() {
        return startCoverDate;
    }

    public void setStartCoverDate(LocalDate startCoverDate) {
        this.startCoverDate = startCoverDate;
    }

    public LocalDate getEndCoverDate() {
        return endCoverDate;
    }

    public void setEndCoverDate(LocalDate endCoverDate) {
        this.endCoverDate = endCoverDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public void setStatus(PolicyStatus status) {
        this.status = status;
    }

    public boolean isValid(){
        return isIdValid() && isNameValid() && isStatusValid() && isCreationDateValid() &&
                isUpdateDateValid() && isStartCoverDateValid() && isEndCoverDateValid();
    }

    public boolean isIdValid() {
        return id != null;
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