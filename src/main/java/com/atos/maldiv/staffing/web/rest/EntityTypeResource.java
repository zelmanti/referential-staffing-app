package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.domain.EntityType;
import com.atos.maldiv.staffing.repository.EntityTypeRepository;
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
 * REST controller for managing {@link com.atos.maldiv.staffing.domain.EntityType}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EntityTypeResource {
    private final Logger log = LoggerFactory.getLogger(EntityTypeResource.class);

    private static final String ENTITY_NAME = "entityType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntityTypeRepository entityTypeRepository;

    public EntityTypeResource(EntityTypeRepository entityTypeRepository) {
        this.entityTypeRepository = entityTypeRepository;
    }

    /**
     * {@code POST  /entity-types} : Create a new entityType.
     *
     * @param entityType the entityType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityType, or with status {@code 400 (Bad Request)} if the entityType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entity-types")
    public ResponseEntity<EntityType> createEntityType(@RequestBody EntityType entityType) throws URISyntaxException {
        log.debug("REST request to save EntityType : {}", entityType);
        if (entityType.getId() != null) {
            throw new BadRequestAlertException("A new entityType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityType result = entityTypeRepository.save(entityType);
        return ResponseEntity
            .created(new URI("/api/entity-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entity-types} : Updates an existing entityType.
     *
     * @param entityType the entityType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityType,
     * or with status {@code 400 (Bad Request)} if the entityType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entity-types")
    public ResponseEntity<EntityType> updateEntityType(@RequestBody EntityType entityType) throws URISyntaxException {
        log.debug("REST request to update EntityType : {}", entityType);
        if (entityType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityType result = entityTypeRepository.save(entityType);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entity-types} : get all the entityTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityTypes in body.
     */
    @GetMapping("/entity-types")
    public List<EntityType> getAllEntityTypes() {
        log.debug("REST request to get all EntityTypes");
        return entityTypeRepository.findAll();
    }

    /**
     * {@code GET  /entity-types/:id} : get the "id" entityType.
     *
     * @param id the id of the entityType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entity-types/{id}")
    public ResponseEntity<EntityType> getEntityType(@PathVariable Long id) {
        log.debug("REST request to get EntityType : {}", id);
        Optional<EntityType> entityType = entityTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(entityType);
    }

    /**
     * {@code DELETE  /entity-types/:id} : delete the "id" entityType.
     *
     * @param id the id of the entityType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entity-types/{id}")
    public ResponseEntity<Void> deleteEntityType(@PathVariable Long id) {
        log.debug("REST request to delete EntityType : {}", id);
        entityTypeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
