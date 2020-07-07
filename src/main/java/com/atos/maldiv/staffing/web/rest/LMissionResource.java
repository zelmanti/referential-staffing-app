package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.domain.LMission;
import com.atos.maldiv.staffing.repository.LMissionRepository;
import com.atos.maldiv.staffing.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.atos.maldiv.staffing.domain.LMission}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LMissionResource {

    private final Logger log = LoggerFactory.getLogger(LMissionResource.class);

    private static final String ENTITY_NAME = "lMission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LMissionRepository lMissionRepository;

    public LMissionResource(LMissionRepository lMissionRepository) {
        this.lMissionRepository = lMissionRepository;
    }

    /**
     * {@code POST  /l-missions} : Create a new lMission.
     *
     * @param lMission the lMission to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lMission, or with status {@code 400 (Bad Request)} if the lMission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/l-missions")
    public ResponseEntity<LMission> createLMission(@RequestBody LMission lMission) throws URISyntaxException {
        log.debug("REST request to save LMission : {}", lMission);
        if (lMission.getId() != null) {
            throw new BadRequestAlertException("A new lMission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LMission result = lMissionRepository.save(lMission);
        return ResponseEntity.created(new URI("/api/l-missions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /l-missions} : Updates an existing lMission.
     *
     * @param lMission the lMission to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lMission,
     * or with status {@code 400 (Bad Request)} if the lMission is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lMission couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/l-missions")
    public ResponseEntity<LMission> updateLMission(@RequestBody LMission lMission) throws URISyntaxException {
        log.debug("REST request to update LMission : {}", lMission);
        if (lMission.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LMission result = lMissionRepository.save(lMission);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lMission.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /l-missions} : get all the lMissions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lMissions in body.
     */
    @GetMapping("/l-missions")
    public List<LMission> getAllLMissions() {
        log.debug("REST request to get all LMissions");
        return lMissionRepository.findAll();
    }

    /**
     * {@code GET  /l-missions/:id} : get the "id" lMission.
     *
     * @param id the id of the lMission to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lMission, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/l-missions/{id}")
    public ResponseEntity<LMission> getLMission(@PathVariable Long id) {
        log.debug("REST request to get LMission : {}", id);
        Optional<LMission> lMission = lMissionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(lMission);
    }

    /**
     * {@code DELETE  /l-missions/:id} : delete the "id" lMission.
     *
     * @param id the id of the lMission to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/l-missions/{id}")
    public ResponseEntity<Void> deleteLMission(@PathVariable Long id) {
        log.debug("REST request to delete LMission : {}", id);
        lMissionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
