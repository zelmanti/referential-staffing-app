package com.atos.maldiv.staffing.repository;

import com.atos.maldiv.staffing.domain.DFinanciere;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DFinanciere entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DFinanciereRepository extends JpaRepository<DFinanciere, Long> {
}
