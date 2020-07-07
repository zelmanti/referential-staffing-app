package com.atos.maldiv.staffing.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.atos.maldiv.staffing.web.rest.TestUtil;

public class DFinanciereTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DFinanciere.class);
        DFinanciere dFinanciere1 = new DFinanciere();
        dFinanciere1.setId(1L);
        DFinanciere dFinanciere2 = new DFinanciere();
        dFinanciere2.setId(dFinanciere1.getId());
        assertThat(dFinanciere1).isEqualTo(dFinanciere2);
        dFinanciere2.setId(2L);
        assertThat(dFinanciere1).isNotEqualTo(dFinanciere2);
        dFinanciere1.setId(null);
        assertThat(dFinanciere1).isNotEqualTo(dFinanciere2);
    }
}
