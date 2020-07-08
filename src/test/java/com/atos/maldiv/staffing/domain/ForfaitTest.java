package com.atos.maldiv.staffing.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.atos.maldiv.staffing.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ForfaitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Forfait.class);
        Forfait forfait1 = new Forfait();
        forfait1.setId(1L);
        Forfait forfait2 = new Forfait();
        forfait2.setId(forfait1.getId());
        assertThat(forfait1).isEqualTo(forfait2);
        forfait2.setId(2L);
        assertThat(forfait1).isNotEqualTo(forfait2);
        forfait1.setId(null);
        assertThat(forfait1).isNotEqualTo(forfait2);
    }
}
