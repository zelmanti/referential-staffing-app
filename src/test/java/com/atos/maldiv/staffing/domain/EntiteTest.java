package com.atos.maldiv.staffing.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.atos.maldiv.staffing.web.rest.TestUtil;

public class EntiteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Entite.class);
        Entite entite1 = new Entite();
        entite1.setId(1L);
        Entite entite2 = new Entite();
        entite2.setId(entite1.getId());
        assertThat(entite1).isEqualTo(entite2);
        entite2.setId(2L);
        assertThat(entite1).isNotEqualTo(entite2);
        entite1.setId(null);
        assertThat(entite1).isNotEqualTo(entite2);
    }
}
