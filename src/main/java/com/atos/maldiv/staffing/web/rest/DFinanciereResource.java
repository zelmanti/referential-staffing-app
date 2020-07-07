package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.domain.DFinanciere;
import com.atos.maldiv.staffing.repository.DFinanciereRepository;
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
 * REST controller for managing {@link com.atos.maldiv.staffing.domain.DFinanciere}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DFinanciereResource {

    private final Logger log = LoggerFactory.getLogger(DFinanciereResource.class);

    private static final String ENTITY_NAME = "dFinanciere";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DFinanciereRepository dFinanciereRepository;

    public DFinanciereResource(DFinanciereRepository dFinanciereRepository) {
        this.dFinanciereRepository = dFinanciereRepository;
    }

    /**
     * {@code POST  /d-financieres} : Create a new dFinanciere.
     *
     * @param dFinanciere the dFinanciere to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dFinanciere, or with status {@code 400 (Bad Request)} if the dFinanciere has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/d-financieres")
    public ResponseEntity<DFinanciere> createDFinanciere(@RequestBody DFinanciere dFinanciere) throws URISyntaxException {
        log.debug("REST request to save DFinanciere : {}", dFinanciere);
        if (dFinanciere.getId() != null) {
            throw new BadRequestAlertException("A new dFinanciere cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DFinanciere result = dFinanciereRepository.save(dFinanciere);
        return ResponseEntity.created(new URI("/api/d-financieres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /d-financieres} : Updates an existing dFinanciere.
     *
     * @param dFinanciere the dFinanciere to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dFinanciere,
     * or with status {@code 400 (Bad Request)} if the dFinanciere is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dFinanciere couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/d-financieres")
    public ResponseEntity<DFinanciere> updateDFinanciere(@RequestBody DFinanciere dFinanciere) throws URISyntaxException {
        log.debug("REST request to update DFinanciere : {}", dFinanciere);
        if (dFinanciere.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DFinanciere result = dFinanciereRepository.save(dFinanciere);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dFinanciere.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /d-financieres} : get all the dFinancieres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dFinancieres in body.
     */
    @GetMapping("/d-financieres")
    public List<DFinanciere> getAllDFinancieres() {
        log.debug("REST request to get all DFinancieres");
        return dFinanciereRepository.findAll();
    }

    /**
     * {@code GET  /d-financieres/:id} : get the "id" dFinanciere.
     *
     * @param id the id of the dFinanciere to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dFinanciere, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/d-financieres/{id}")
    public ResponseEntity<DFinanciere> getDFinanciere(@PathVariable Long id) {
        log.debug("REST request to get DFinanciere : {}", id);
        Optional<DFinanciere> dFinanciere = dFinanciereRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dFinanciere);
    }

    /**
     * {@code DELETE  /d-financieres/:id} : delete the "id" dFinanciere.
     *
     * @param id the id of the dFinanciere to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/d-financieres/{id}")
    public ResponseEntity<Void> deleteDFinanciere(@PathVariable Long id) {
        log.debug("REST request to delete DFinanciere : {}", id);
        dFinanciereRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
