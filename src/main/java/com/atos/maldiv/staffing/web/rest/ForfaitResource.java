package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.domain.Forfait;
import com.atos.maldiv.staffing.repository.ForfaitRepository;
import com.atos.maldiv.staffing.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.atos.maldiv.staffing.domain.Forfait}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ForfaitResource {
    private final Logger log = LoggerFactory.getLogger(ForfaitResource.class);

    private static final String ENTITY_NAME = "forfait";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ForfaitRepository forfaitRepository;

    public ForfaitResource(ForfaitRepository forfaitRepository) {
        this.forfaitRepository = forfaitRepository;
    }

    /**
     * {@code POST  /forfaits} : Create a new forfait.
     *
     * @param forfait the forfait to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new forfait, or with status {@code 400 (Bad Request)} if the forfait has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/forfaits")
    public ResponseEntity<Forfait> createForfait(@RequestBody Forfait forfait) throws URISyntaxException {
        log.debug("REST request to save Forfait : {}", forfait);
        if (forfait.getId() != null) {
            throw new BadRequestAlertException("A new forfait cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Forfait result = forfaitRepository.save(forfait);
        return ResponseEntity
            .created(new URI("/api/forfaits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /forfaits} : Updates an existing forfait.
     *
     * @param forfait the forfait to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated forfait,
     * or with status {@code 400 (Bad Request)} if the forfait is not valid,
     * or with status {@code 500 (Internal Server Error)} if the forfait couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/forfaits")
    public ResponseEntity<Forfait> updateForfait(@RequestBody Forfait forfait) throws URISyntaxException {
        log.debug("REST request to update Forfait : {}", forfait);
        if (forfait.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Forfait result = forfaitRepository.save(forfait);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, forfait.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /forfaits} : get all the forfaits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of forfaits in body.
     */
    @GetMapping("/forfaits")
    public List<Forfait> getAllForfaits() {
        log.debug("REST request to get all Forfaits");
        return forfaitRepository.findAll();
    }

    /**
     * {@code GET  /forfaits/:id} : get the "id" forfait.
     *
     * @param id the id of the forfait to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the forfait, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/forfaits/{id}")
    public ResponseEntity<Forfait> getForfait(@PathVariable Long id) {
        log.debug("REST request to get Forfait : {}", id);
        Optional<Forfait> forfait = forfaitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(forfait);
    }

    /**
     * {@code DELETE  /forfaits/:id} : delete the "id" forfait.
     *
     * @param id the id of the forfait to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/forfaits/{id}")
    public ResponseEntity<Void> deleteForfait(@PathVariable Long id) {
        log.debug("REST request to delete Forfait : {}", id);
        forfaitRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
