package com.tinubu.test.adapters.spi;

import com.tinubu.test.domain.PolicyNotFoundException;
import com.tinubu.test.domain.model.Policy;
import com.tinubu.test.domain.model.PolicyStatus;
import com.tinubu.test.domain.ports.PolicyRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class H2PolicyRepository implements PolicyRepository {
    private final SpringDataH2PolicyRepository springDataH2PolicyRepository;

    public H2PolicyRepository(SpringDataH2PolicyRepository springDataH2PolicyRepository) {
        this.springDataH2PolicyRepository = springDataH2PolicyRepository;
    }

    @Override
    public List<Policy> getAll() {
        return springDataH2PolicyRepository.findAll().stream().map(policy -> new Policy(policy.getId(), policy.getName(),
                policy.getStartCoverDate(), policy.getEndCoverDate(), policy.getCreationDate(), policy.getUpdateDate(),
                PolicyStatus.valueOf(policy.getStatus()))).collect(Collectors.toList());
    }

    @Override
    public Policy findById(Integer id) {
         Optional<PolicyDataBaseModel> optionalPolicyDataBaseModel=springDataH2PolicyRepository.findById(id);
         if(optionalPolicyDataBaseModel.isPresent()){
             PolicyDataBaseModel policyDataBaseModel = optionalPolicyDataBaseModel.get();
             return new Policy.PolicyBuilder(policyDataBaseModel.getName(),policyDataBaseModel.getStartCoverDate(),
                     policyDataBaseModel.getEndCoverDate(),PolicyStatus.valueOf(policyDataBaseModel.getStatus()))
                     .id(policyDataBaseModel.getId())
                     .creationDate(policyDataBaseModel.getCreationDate())
                     .updateDate(policyDataBaseModel.getUpdateDate()).build();
         }
         else {
             throw new PolicyNotFoundException();
         }
    }

    @Override
    public Integer save(Policy policy) {
        PolicyDataBaseModel policyDataBaseModelInput = new PolicyDataBaseModel(policy.getId(), policy.getName(), policy.getStartCoverDate(), policy.getEndCoverDate(), policy.getCreationDate(), policy.getUpdateDate(), policy.getStatus().name());
        springDataH2PolicyRepository.save(policyDataBaseModelInput);
         return policy.getId();
    }

    @Override
    public boolean existsById(Integer id) {
        return springDataH2PolicyRepository.existsById(id);
    }
}
