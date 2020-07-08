package com.atos.maldiv.staffing.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.atos.maldiv.staffing.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class SCRateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SCRate.class);
        SCRate sCRate1 = new SCRate();
        sCRate1.setId(1L);
        SCRate sCRate2 = new SCRate();
        sCRate2.setId(sCRate1.getId());
        assertThat(sCRate1).isEqualTo(sCRate2);
        sCRate2.setId(2L);
        assertThat(sCRate1).isNotEqualTo(sCRate2);
        sCRate1.setId(null);
        assertThat(sCRate1).isNotEqualTo(sCRate2);
    }
}
