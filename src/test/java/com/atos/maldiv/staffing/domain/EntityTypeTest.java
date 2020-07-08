package com.atos.maldiv.staffing.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.atos.maldiv.staffing.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class EntityTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityType.class);
        EntityType entityType1 = new EntityType();
        entityType1.setId(1L);
        EntityType entityType2 = new EntityType();
        entityType2.setId(entityType1.getId());
        assertThat(entityType1).isEqualTo(entityType2);
        entityType2.setId(2L);
        assertThat(entityType1).isNotEqualTo(entityType2);
        entityType1.setId(null);
        assertThat(entityType1).isNotEqualTo(entityType2);
    }
}
