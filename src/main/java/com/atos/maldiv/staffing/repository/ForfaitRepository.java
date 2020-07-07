package com.atos.maldiv.staffing.repository;

import com.atos.maldiv.staffing.domain.Forfait;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Forfait entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ForfaitRepository extends JpaRepository<Forfait, Long> {
}
