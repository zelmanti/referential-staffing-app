package com.atos.maldiv.staffing.repository;

import com.atos.maldiv.staffing.domain.LMission;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LMission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LMissionRepository extends JpaRepository<LMission, Long> {
}
