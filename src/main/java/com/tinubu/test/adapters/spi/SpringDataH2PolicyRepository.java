package com.tinubu.test.adapters.spi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataH2PolicyRepository extends JpaRepository<PolicyDataBaseModel, Integer> {
    List<PolicyDataBaseModel> findAll();

}
