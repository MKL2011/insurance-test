package com.tinubu.test.domain.ports;

import com.tinubu.test.adapters.spi.PolicyDataBaseModel;
import com.tinubu.test.domain.model.Policy;

import java.util.List;

public interface PolicyRepository {
    List<PolicyDataBaseModel> getAll();

    Integer save(Policy policy);
}
