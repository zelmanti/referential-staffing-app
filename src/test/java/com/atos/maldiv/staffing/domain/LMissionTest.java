package com.atos.maldiv.staffing.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.atos.maldiv.staffing.web.rest.TestUtil;

public class LMissionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LMission.class);
        LMission lMission1 = new LMission();
        lMission1.setId(1L);
        LMission lMission2 = new LMission();
        lMission2.setId(lMission1.getId());
        assertThat(lMission1).isEqualTo(lMission2);
        lMission2.setId(2L);
        assertThat(lMission1).isNotEqualTo(lMission2);
        lMission1.setId(null);
        assertThat(lMission1).isNotEqualTo(lMission2);
    }
}
