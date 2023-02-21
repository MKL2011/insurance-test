package com.tinubu.test.adapters.spi;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PolicyDataBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "start_cover_date")
    private LocalDate startCoverDate;
    @Column(name = "end_cover_date")
    private LocalDate endCoverDate;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "update_date")
    private LocalDate updateDate;
    @Column(name = "status")
    private String status;

    public PolicyDataBaseModel() {
    }

    public PolicyDataBaseModel(String name, LocalDate startCoverDate, LocalDate endCoverDate, LocalDate creationDate, LocalDate updateDate, String status) {
        this.name = name;
        this.startCoverDate = startCoverDate;
        this.endCoverDate = endCoverDate;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartCoverDate() {
        return startCoverDate;
    }

    public LocalDate getEndCoverDate() {
        return endCoverDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public String getStatus() {
        return status;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartCoverDate(LocalDate startCoverDate) {
        this.startCoverDate = startCoverDate;
    }

    public void setEndCoverDate(LocalDate endCoverDate) {
        this.endCoverDate = endCoverDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
