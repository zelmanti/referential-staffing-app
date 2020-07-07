package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.ReferentialStaffingApp;
import com.atos.maldiv.staffing.domain.EntityType;
import com.atos.maldiv.staffing.repository.EntityTypeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EntityTypeResource} REST controller.
 */
@SpringBootTest(classes = ReferentialStaffingApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EntityTypeResourceIT {

    private static final String DEFAULT_CODE_TYPE_ENTITEE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_TYPE_ENTITEE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_TYPE_ENTITEE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_TYPE_ENTITEE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private EntityTypeRepository entityTypeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityTypeMockMvc;

    private EntityType entityType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityType createEntity(EntityManager em) {
        EntityType entityType = new EntityType()
            .codeTypeEntitee(DEFAULT_CODE_TYPE_ENTITEE)
            .libelleTypeEntitee(DEFAULT_LIBELLE_TYPE_ENTITEE)
            .isActive(DEFAULT_IS_ACTIVE);
        return entityType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityType createUpdatedEntity(EntityManager em) {
        EntityType entityType = new EntityType()
            .codeTypeEntitee(UPDATED_CODE_TYPE_ENTITEE)
            .libelleTypeEntitee(UPDATED_LIBELLE_TYPE_ENTITEE)
            .isActive(UPDATED_IS_ACTIVE);
        return entityType;
    }

    @BeforeEach
    public void initTest() {
        entityType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityType() throws Exception {
        int databaseSizeBeforeCreate = entityTypeRepository.findAll().size();
        // Create the EntityType
        restEntityTypeMockMvc.perform(post("/api/entity-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityType)))
            .andExpect(status().isCreated());

        // Validate the EntityType in the database
        List<EntityType> entityTypeList = entityTypeRepository.findAll();
        assertThat(entityTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EntityType testEntityType = entityTypeList.get(entityTypeList.size() - 1);
        assertThat(testEntityType.getCodeTypeEntitee()).isEqualTo(DEFAULT_CODE_TYPE_ENTITEE);
        assertThat(testEntityType.getLibelleTypeEntitee()).isEqualTo(DEFAULT_LIBELLE_TYPE_ENTITEE);
        assertThat(testEntityType.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void createEntityTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityTypeRepository.findAll().size();

        // Create the EntityType with an existing ID
        entityType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityTypeMockMvc.perform(post("/api/entity-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityType)))
            .andExpect(status().isBadRequest());

        // Validate the EntityType in the database
        List<EntityType> entityTypeList = entityTypeRepository.findAll();
        assertThat(entityTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntityTypes() throws Exception {
        // Initialize the database
        entityTypeRepository.saveAndFlush(entityType);

        // Get all the entityTypeList
        restEntityTypeMockMvc.perform(get("/api/entity-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityType.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeTypeEntitee").value(hasItem(DEFAULT_CODE_TYPE_ENTITEE)))
            .andExpect(jsonPath("$.[*].libelleTypeEntitee").value(hasItem(DEFAULT_LIBELLE_TYPE_ENTITEE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getEntityType() throws Exception {
        // Initialize the database
        entityTypeRepository.saveAndFlush(entityType);

        // Get the entityType
        restEntityTypeMockMvc.perform(get("/api/entity-types/{id}", entityType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityType.getId().intValue()))
            .andExpect(jsonPath("$.codeTypeEntitee").value(DEFAULT_CODE_TYPE_ENTITEE))
            .andExpect(jsonPath("$.libelleTypeEntitee").value(DEFAULT_LIBELLE_TYPE_ENTITEE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingEntityType() throws Exception {
        // Get the entityType
        restEntityTypeMockMvc.perform(get("/api/entity-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityType() throws Exception {
        // Initialize the database
        entityTypeRepository.saveAndFlush(entityType);

        int databaseSizeBeforeUpdate = entityTypeRepository.findAll().size();

        // Update the entityType
        EntityType updatedEntityType = entityTypeRepository.findById(entityType.getId()).get();
        // Disconnect from session so that the updates on updatedEntityType are not directly saved in db
        em.detach(updatedEntityType);
        updatedEntityType
            .codeTypeEntitee(UPDATED_CODE_TYPE_ENTITEE)
            .libelleTypeEntitee(UPDATED_LIBELLE_TYPE_ENTITEE)
            .isActive(UPDATED_IS_ACTIVE);

        restEntityTypeMockMvc.perform(put("/api/entity-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEntityType)))
            .andExpect(status().isOk());

        // Validate the EntityType in the database
        List<EntityType> entityTypeList = entityTypeRepository.findAll();
        assertThat(entityTypeList).hasSize(databaseSizeBeforeUpdate);
        EntityType testEntityType = entityTypeList.get(entityTypeList.size() - 1);
        assertThat(testEntityType.getCodeTypeEntitee()).isEqualTo(UPDATED_CODE_TYPE_ENTITEE);
        assertThat(testEntityType.getLibelleTypeEntitee()).isEqualTo(UPDATED_LIBELLE_TYPE_ENTITEE);
        assertThat(testEntityType.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityType() throws Exception {
        int databaseSizeBeforeUpdate = entityTypeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityTypeMockMvc.perform(put("/api/entity-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entityType)))
            .andExpect(status().isBadRequest());

        // Validate the EntityType in the database
        List<EntityType> entityTypeList = entityTypeRepository.findAll();
        assertThat(entityTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityType() throws Exception {
        // Initialize the database
        entityTypeRepository.saveAndFlush(entityType);

        int databaseSizeBeforeDelete = entityTypeRepository.findAll().size();

        // Delete the entityType
        restEntityTypeMockMvc.perform(delete("/api/entity-types/{id}", entityType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntityType> entityTypeList = entityTypeRepository.findAll();
        assertThat(entityTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
