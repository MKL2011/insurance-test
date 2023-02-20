package com.tinubu.test.adapters.spi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataH2PolicyRepository extends JpaRepository<PolicyDataBaseModel, Integer> {
}
