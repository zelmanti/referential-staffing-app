package com.atos.maldiv.staffing.repository;

import com.atos.maldiv.staffing.domain.SCRate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SCRate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SCRateRepository extends JpaRepository<SCRate, Long> {}
