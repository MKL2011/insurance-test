package com.tinubu.test.adapters.spi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class H2RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SpringDataH2PolicyRepository springDataH2PolicyRepository;

    @Test
    public void should_find_no_policies_if_repository_is_empty() {
        List<PolicyDataBaseModel> policies = springDataH2PolicyRepository.findAll();
        assertThat(policies).isEmpty();
    }

    @Test
    public void should_find_all_policies() {
        PolicyDataBaseModel policyDataBaseModel1 = new PolicyDataBaseModel( "policy1",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE");
        entityManager.persist(policyDataBaseModel1);

        PolicyDataBaseModel policyDataBaseModel2 = new PolicyDataBaseModel( "policy2",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE");

        entityManager.persist(policyDataBaseModel2);


        PolicyDataBaseModel policyDataBaseModel3 = new PolicyDataBaseModel( "policy3",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE");

        entityManager.persist(policyDataBaseModel3);

        Iterable<PolicyDataBaseModel> policies = springDataH2PolicyRepository.findAll();

        assertThat(policies).hasSize(3).contains(policyDataBaseModel1, policyDataBaseModel2, policyDataBaseModel3);
    }

    @Test
    public void test_add_policy() {
        PolicyDataBaseModel policyDataBaseModel = springDataH2PolicyRepository.save(new PolicyDataBaseModel( "policy1",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE"));

        assertThat(policyDataBaseModel).hasFieldOrPropertyWithValue("name", "policy1");
        assertThat(policyDataBaseModel).hasFieldOrPropertyWithValue("status", "ACTIVE");
    }

    @Test
    public void test_update_policy() {
        PolicyDataBaseModel policyDataBaseModel1 = new PolicyDataBaseModel( "policy1",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE");
        entityManager.persist(policyDataBaseModel1);

        PolicyDataBaseModel policyDataBaseModel2 = new PolicyDataBaseModel( "policy2",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE");

        entityManager.persist(policyDataBaseModel2);

        PolicyDataBaseModel policyDataBaseModel = new PolicyDataBaseModel( "policy3",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "INACTIVE");

        PolicyDataBaseModel newPolicyDataBaseModel = springDataH2PolicyRepository.findById(policyDataBaseModel2.getId()).get();
        newPolicyDataBaseModel.setName(policyDataBaseModel.getName());
        newPolicyDataBaseModel.setStatus(policyDataBaseModel.getStatus());
        springDataH2PolicyRepository.save(newPolicyDataBaseModel);

        PolicyDataBaseModel checkPolicyDataBaseModel = springDataH2PolicyRepository.findById(policyDataBaseModel2.getId()).get();

        assertThat(checkPolicyDataBaseModel).hasFieldOrPropertyWithValue("name", "policy3");
        assertThat(checkPolicyDataBaseModel).hasFieldOrPropertyWithValue("status", "INACTIVE");
    }

    @Test
    public void test_find_by_id() {
        PolicyDataBaseModel policyDataBaseModel1 = new PolicyDataBaseModel( "policy1",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE");
        entityManager.persist(policyDataBaseModel1);

        PolicyDataBaseModel policyDataBaseModel2 = new PolicyDataBaseModel( "policy2",
                LocalDate.of(2021, 1, 12), LocalDate.of(2025, 12, 15),
                LocalDate.of(2021, 10, 10), LocalDate.of(2022, 1, 11), "ACTIVE");

        entityManager.persist(policyDataBaseModel2);

        PolicyDataBaseModel checkPolicyDataBaseModel = springDataH2PolicyRepository.findById(policyDataBaseModel2.getId()).get();

        assertThat(checkPolicyDataBaseModel).isEqualTo(policyDataBaseModel2);
    }

}
