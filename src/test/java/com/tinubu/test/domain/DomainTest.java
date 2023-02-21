package com.tinubu.test.domain;

import com.tinubu.test.domain.model.Policy;
import com.tinubu.test.domain.model.PolicyStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;


public class DomainTest {
    private final Policy policy = new Policy.PolicyBuilder("", LocalDate.now(), LocalDate.now().minusDays(1), PolicyStatus.INACTIVE, LocalDate.now(), LocalDate.now()).build();
    private final Policy policyWithoutRequiredFields = new Policy.PolicyBuilder("policyWithoutDates", PolicyStatus.INACTIVE).build();
    private final Policy policyWithNullFields = new Policy.PolicyBuilder(null,null,null,null,null,null).build();


    @Test
    public void policy_name_is_not_null() {
        assertThat(policyWithNullFields.isNameValid()).isFalse();
    }

    @Test
    public void policy_name_is_not_empty() {
        assertThat(policy.isNameValid()).isFalse();
    }

    @Test
    public void policy_status_is_not_null() {
        assertThat(policyWithNullFields.isStatusValid()).isFalse();
    }

    @Test
    public void policy_creation_date_is_required_and_not_null() {
        assertThat(policyWithoutRequiredFields.isCreationDateValid()).isFalse();
        assertThat(policyWithNullFields.isCreationDateValid()).isFalse();
    }

    @Test
    public void policy_update_date_is_required_and_not_null() {
        assertThat(policyWithoutRequiredFields.isUpdateDateValid()).isFalse();
        assertThat(policyWithNullFields.isUpdateDateValid()).isFalse();
    }

    @Test
    public void policy_cover_start_date_is_required_and_not_null() {
        assertThat(policyWithoutRequiredFields.isStartCoverDateValid()).isFalse();
        assertThat(policyWithNullFields.isStartCoverDateValid()).isFalse();
    }

    @Test
    public void policy_cover_end_date_is_required_and_not_null() {
        assertThat(policyWithoutRequiredFields.isEndCoverDateValid()).isFalse();
        assertThat(policyWithNullFields.isEndCoverDateValid()).isFalse();
    }

    @Test
    public void policy_cover_end_date_is_after_policy_cover_start_date() {
        assertThat(policy.isEndCoverDateValid()).isFalse();
    }

    @Test
    public void valid_policy() {
        Policy policy1 = new Policy(1, "policy1", LocalDate.of(2021, 1, 12),
                LocalDate.of(2025, 12, 15), LocalDate.of(2021, 10, 10),
                LocalDate.of(2022, 1, 11), PolicyStatus.ACTIVE);
        Policy policy2 = new Policy(2, "policy2", LocalDate.of(2011, 1, 12),
                LocalDate.of(2015, 12, 15), LocalDate.of(2011, 10, 10),
                LocalDate.of(2010, 1, 11), PolicyStatus.INACTIVE);
        assertThat(policy1.isValid()).isTrue();
        assertThat(policy2.isValid()).isTrue();

    }

}
