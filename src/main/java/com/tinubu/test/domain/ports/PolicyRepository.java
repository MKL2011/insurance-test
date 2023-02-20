package com.tinubu.test.domain.ports;

import com.tinubu.test.adapters.spi.PolicyDataBaseModel;

import java.util.List;

public interface PolicyRepository {
    List<PolicyDataBaseModel> getAll();
}
