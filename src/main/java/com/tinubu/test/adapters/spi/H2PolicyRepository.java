package com.tinubu.test.adapters.spi;

import com.tinubu.test.domain.ports.PolicyRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class H2PolicyRepository implements PolicyRepository {
    private final SpringDataH2PolicyRepository springDataH2PolicyRepository;

    public H2PolicyRepository(SpringDataH2PolicyRepository springDataH2PolicyRepository) {
        this.springDataH2PolicyRepository = springDataH2PolicyRepository;
    }

    @Override
    public List<PolicyDataBaseModel> getAll() {
        return springDataH2PolicyRepository.findAll();
    }
}
