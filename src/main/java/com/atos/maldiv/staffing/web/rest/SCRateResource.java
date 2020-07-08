package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.domain.SCRate;
import com.atos.maldiv.staffing.repository.SCRateRepository;
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
 * REST controller for managing {@link com.atos.maldiv.staffing.domain.SCRate}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SCRateResource {
    private final Logger log = LoggerFactory.getLogger(SCRateResource.class);

    private static final String ENTITY_NAME = "sCRate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SCRateRepository sCRateRepository;

    public SCRateResource(SCRateRepository sCRateRepository) {
        this.sCRateRepository = sCRateRepository;
    }

    /**
     * {@code POST  /sc-rates} : Create a new sCRate.
     *
     * @param sCRate the sCRate to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sCRate, or with status {@code 400 (Bad Request)} if the sCRate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sc-rates")
    public ResponseEntity<SCRate> createSCRate(@RequestBody SCRate sCRate) throws URISyntaxException {
        log.debug("REST request to save SCRate : {}", sCRate);
        if (sCRate.getId() != null) {
            throw new BadRequestAlertException("A new sCRate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SCRate result = sCRateRepository.save(sCRate);
        return ResponseEntity
            .created(new URI("/api/sc-rates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sc-rates} : Updates an existing sCRate.
     *
     * @param sCRate the sCRate to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sCRate,
     * or with status {@code 400 (Bad Request)} if the sCRate is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sCRate couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sc-rates")
    public ResponseEntity<SCRate> updateSCRate(@RequestBody SCRate sCRate) throws URISyntaxException {
        log.debug("REST request to update SCRate : {}", sCRate);
        if (sCRate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SCRate result = sCRateRepository.save(sCRate);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sCRate.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sc-rates} : get all the sCRates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sCRates in body.
     */
    @GetMapping("/sc-rates")
    public List<SCRate> getAllSCRates() {
        log.debug("REST request to get all SCRates");
        return sCRateRepository.findAll();
    }

    /**
     * {@code GET  /sc-rates/:id} : get the "id" sCRate.
     *
     * @param id the id of the sCRate to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sCRate, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sc-rates/{id}")
    public ResponseEntity<SCRate> getSCRate(@PathVariable Long id) {
        log.debug("REST request to get SCRate : {}", id);
        Optional<SCRate> sCRate = sCRateRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sCRate);
    }

    /**
     * {@code DELETE  /sc-rates/:id} : delete the "id" sCRate.
     *
     * @param id the id of the sCRate to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sc-rates/{id}")
    public ResponseEntity<Void> deleteSCRate(@PathVariable Long id) {
        log.debug("REST request to delete SCRate : {}", id);
        sCRateRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
