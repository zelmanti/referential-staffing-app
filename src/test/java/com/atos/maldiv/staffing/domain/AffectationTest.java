package com.atos.maldiv.staffing.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.atos.maldiv.staffing.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class AffectationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Affectation.class);
        Affectation affectation1 = new Affectation();
        affectation1.setId(1L);
        Affectation affectation2 = new Affectation();
        affectation2.setId(affectation1.getId());
        assertThat(affectation1).isEqualTo(affectation2);
        affectation2.setId(2L);
        assertThat(affectation1).isNotEqualTo(affectation2);
        affectation1.setId(null);
        assertThat(affectation1).isNotEqualTo(affectation2);
    }
}
