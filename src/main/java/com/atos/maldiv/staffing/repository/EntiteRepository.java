package com.atos.maldiv.staffing.repository;

import com.atos.maldiv.staffing.domain.Entite;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Entite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntiteRepository extends JpaRepository<Entite, Long> {
}
