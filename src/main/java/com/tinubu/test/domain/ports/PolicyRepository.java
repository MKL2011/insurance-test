package com.tinubu.test.domain.ports;

import com.tinubu.test.domain.model.Policy;

import java.util.List;

public interface PolicyRepository {
    List<Policy> getAll();

    Policy findById(Integer id);

    Integer save(Policy policy);

    boolean existsById(Integer id);
}
