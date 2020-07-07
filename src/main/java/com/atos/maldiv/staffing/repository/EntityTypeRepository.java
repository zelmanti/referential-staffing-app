package com.atos.maldiv.staffing.repository;

import com.atos.maldiv.staffing.domain.EntityType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EntityType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityTypeRepository extends JpaRepository<EntityType, Long> {
}
