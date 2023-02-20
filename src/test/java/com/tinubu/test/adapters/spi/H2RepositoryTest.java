package com.tinubu.test.adapters.spi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class H2RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SpringDataH2PolicyRepository springDataH2PolicyRepository;

    @Test
    public void should_find_no_policies_if_repository_is_empty() {
        Iterable<PolicyDataBaseModel> policies = springDataH2PolicyRepository.findAll();
        assertThat(policies).isEmpty();
    }

    @Test
    public void should_find_all_policies() {
        PolicyDataBaseModel policyDataBaseModel1 = new PolicyDataBaseModel();
        entityManager.persist(policyDataBaseModel1);

        PolicyDataBaseModel policyDataBaseModel2 = new PolicyDataBaseModel();
        entityManager.persist(policyDataBaseModel2);

        PolicyDataBaseModel policyDataBaseModel3 = new PolicyDataBaseModel();
        entityManager.persist(policyDataBaseModel3);

        Iterable<PolicyDataBaseModel> policies = springDataH2PolicyRepository.findAll();

        assertThat(policies).hasSize(3).contains(policyDataBaseModel1, policyDataBaseModel2, policyDataBaseModel3);
    }

}
