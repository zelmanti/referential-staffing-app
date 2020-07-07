package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.domain.Entite;
import com.atos.maldiv.staffing.repository.EntiteRepository;
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
 * REST controller for managing {@link com.atos.maldiv.staffing.domain.Entite}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EntiteResource {

    private final Logger log = LoggerFactory.getLogger(EntiteResource.class);

    private static final String ENTITY_NAME = "entite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntiteRepository entiteRepository;

    public EntiteResource(EntiteRepository entiteRepository) {
        this.entiteRepository = entiteRepository;
    }

    /**
     * {@code POST  /entites} : Create a new entite.
     *
     * @param entite the entite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entite, or with status {@code 400 (Bad Request)} if the entite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entites")
    public ResponseEntity<Entite> createEntite(@RequestBody Entite entite) throws URISyntaxException {
        log.debug("REST request to save Entite : {}", entite);
        if (entite.getId() != null) {
            throw new BadRequestAlertException("A new entite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Entite result = entiteRepository.save(entite);
        return ResponseEntity.created(new URI("/api/entites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entites} : Updates an existing entite.
     *
     * @param entite the entite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entite,
     * or with status {@code 400 (Bad Request)} if the entite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entites")
    public ResponseEntity<Entite> updateEntite(@RequestBody Entite entite) throws URISyntaxException {
        log.debug("REST request to update Entite : {}", entite);
        if (entite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Entite result = entiteRepository.save(entite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entite.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entites} : get all the entites.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entites in body.
     */
    @GetMapping("/entites")
    public List<Entite> getAllEntites() {
        log.debug("REST request to get all Entites");
        return entiteRepository.findAll();
    }

    /**
     * {@code GET  /entites/:id} : get the "id" entite.
     *
     * @param id the id of the entite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entites/{id}")
    public ResponseEntity<Entite> getEntite(@PathVariable Long id) {
        log.debug("REST request to get Entite : {}", id);
        Optional<Entite> entite = entiteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(entite);
    }

    /**
     * {@code DELETE  /entites/:id} : delete the "id" entite.
     *
     * @param id the id of the entite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entites/{id}")
    public ResponseEntity<Void> deleteEntite(@PathVariable Long id) {
        log.debug("REST request to delete Entite : {}", id);
        entiteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
