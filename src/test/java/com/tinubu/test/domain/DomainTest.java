package com.tinubu.test.domain;

import com.tinubu.test.domain.model.Policy;
import com.tinubu.test.domain.model.PolicyStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;


public class DomainTest {
    private Policy policy;

    @BeforeEach
    public void setUp() {
        policy = new Policy();
    }

    @Test
    public void policy_id_is_not_null() {
        assertThat(policy.isIdValid()).isFalse();
    }

    @Test
    public void policy_name_is_not_null() {
        assertThat(policy.isNameValid()).isFalse();
    }

    @Test
    public void policy_name_is_not_empty() {
        policy.setName("");
        assertThat(policy.isNameValid()).isFalse();
    }

    @Test
    public void policy_status_is_not_null() {
        assertThat(policy.isStatusValid()).isFalse();
    }

    @Test
    public void policy_creation_date_is_not_null() {
        assertThat(policy.isCreationDateValid()).isFalse();
    }

    @Test
    public void policy_update_date_is_not_null() {
        assertThat(policy.isUpdateDateValid()).isFalse();
    }

    @Test
    public void policy_cover_start_date_is_not_null() {
        assertThat(policy.isStartCoverDateValid()).isFalse();
    }

    @Test
    public void policy_cover_end_date_is_not_null() {
        assertThat(policy.isEndCoverDateValid()).isFalse();
    }

    @Test
    public void policy_cover_end_date_is_after_policy_cover_start_date() {
        policy.setStartCoverDate(LocalDate.now());
        policy.setEndCoverDate(policy.getStartCoverDate().minusDays(1));
        assertThat(policy.isEndCoverDateValid()).isFalse();
    }

    @Test
    public void valid_policy() {
        policy = new Policy(1, "policy1", LocalDate.of(2021, 1, 12),
                LocalDate.of(2025, 12, 15), LocalDate.of(2021, 10, 10),
                LocalDate.of(2022, 1, 11), PolicyStatus.ACTIVE);
        policy = new Policy(2, "policy2", LocalDate.of(2011, 1, 12),
                LocalDate.of(2015, 12, 15), LocalDate.of(2011, 10, 10),
                LocalDate.of(2010, 1, 11), PolicyStatus.INACTIVE);
        assertThat(policy.isValid()).isTrue();
    }

}
