package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.ReferentialStaffingApp;
import com.atos.maldiv.staffing.domain.Entite;
import com.atos.maldiv.staffing.repository.EntiteRepository;

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
 * Integration tests for the {@link EntiteResource} REST controller.
 */
@SpringBootTest(classes = ReferentialStaffingApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EntiteResourceIT {

    private static final String DEFAULT_CODE_ENTITY = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ENTITY = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELE_ENTITY = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE_ENTITY = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_ENTITEE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_ENTITEE = "BBBBBBBBBB";

    private static final String DEFAULT_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_MANAGER = "BBBBBBBBBB";

    @Autowired
    private EntiteRepository entiteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntiteMockMvc;

    private Entite entite;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entite createEntity(EntityManager em) {
        Entite entite = new Entite()
            .codeEntity(DEFAULT_CODE_ENTITY)
            .libeleEntity(DEFAULT_LIBELE_ENTITY)
            .typeEntitee(DEFAULT_TYPE_ENTITEE)
            .manager(DEFAULT_MANAGER);
        return entite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entite createUpdatedEntity(EntityManager em) {
        Entite entite = new Entite()
            .codeEntity(UPDATED_CODE_ENTITY)
            .libeleEntity(UPDATED_LIBELE_ENTITY)
            .typeEntitee(UPDATED_TYPE_ENTITEE)
            .manager(UPDATED_MANAGER);
        return entite;
    }

    @BeforeEach
    public void initTest() {
        entite = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntite() throws Exception {
        int databaseSizeBeforeCreate = entiteRepository.findAll().size();
        // Create the Entite
        restEntiteMockMvc.perform(post("/api/entites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entite)))
            .andExpect(status().isCreated());

        // Validate the Entite in the database
        List<Entite> entiteList = entiteRepository.findAll();
        assertThat(entiteList).hasSize(databaseSizeBeforeCreate + 1);
        Entite testEntite = entiteList.get(entiteList.size() - 1);
        assertThat(testEntite.getCodeEntity()).isEqualTo(DEFAULT_CODE_ENTITY);
        assertThat(testEntite.getLibeleEntity()).isEqualTo(DEFAULT_LIBELE_ENTITY);
        assertThat(testEntite.getTypeEntitee()).isEqualTo(DEFAULT_TYPE_ENTITEE);
        assertThat(testEntite.getManager()).isEqualTo(DEFAULT_MANAGER);
    }

    @Test
    @Transactional
    public void createEntiteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entiteRepository.findAll().size();

        // Create the Entite with an existing ID
        entite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntiteMockMvc.perform(post("/api/entites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entite)))
            .andExpect(status().isBadRequest());

        // Validate the Entite in the database
        List<Entite> entiteList = entiteRepository.findAll();
        assertThat(entiteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntites() throws Exception {
        // Initialize the database
        entiteRepository.saveAndFlush(entite);

        // Get all the entiteList
        restEntiteMockMvc.perform(get("/api/entites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entite.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeEntity").value(hasItem(DEFAULT_CODE_ENTITY)))
            .andExpect(jsonPath("$.[*].libeleEntity").value(hasItem(DEFAULT_LIBELE_ENTITY)))
            .andExpect(jsonPath("$.[*].typeEntitee").value(hasItem(DEFAULT_TYPE_ENTITEE)))
            .andExpect(jsonPath("$.[*].manager").value(hasItem(DEFAULT_MANAGER)));
    }
    
    @Test
    @Transactional
    public void getEntite() throws Exception {
        // Initialize the database
        entiteRepository.saveAndFlush(entite);

        // Get the entite
        restEntiteMockMvc.perform(get("/api/entites/{id}", entite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entite.getId().intValue()))
            .andExpect(jsonPath("$.codeEntity").value(DEFAULT_CODE_ENTITY))
            .andExpect(jsonPath("$.libeleEntity").value(DEFAULT_LIBELE_ENTITY))
            .andExpect(jsonPath("$.typeEntitee").value(DEFAULT_TYPE_ENTITEE))
            .andExpect(jsonPath("$.manager").value(DEFAULT_MANAGER));
    }
    @Test
    @Transactional
    public void getNonExistingEntite() throws Exception {
        // Get the entite
        restEntiteMockMvc.perform(get("/api/entites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntite() throws Exception {
        // Initialize the database
        entiteRepository.saveAndFlush(entite);

        int databaseSizeBeforeUpdate = entiteRepository.findAll().size();

        // Update the entite
        Entite updatedEntite = entiteRepository.findById(entite.getId()).get();
        // Disconnect from session so that the updates on updatedEntite are not directly saved in db
        em.detach(updatedEntite);
        updatedEntite
            .codeEntity(UPDATED_CODE_ENTITY)
            .libeleEntity(UPDATED_LIBELE_ENTITY)
            .typeEntitee(UPDATED_TYPE_ENTITEE)
            .manager(UPDATED_MANAGER);

        restEntiteMockMvc.perform(put("/api/entites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEntite)))
            .andExpect(status().isOk());

        // Validate the Entite in the database
        List<Entite> entiteList = entiteRepository.findAll();
        assertThat(entiteList).hasSize(databaseSizeBeforeUpdate);
        Entite testEntite = entiteList.get(entiteList.size() - 1);
        assertThat(testEntite.getCodeEntity()).isEqualTo(UPDATED_CODE_ENTITY);
        assertThat(testEntite.getLibeleEntity()).isEqualTo(UPDATED_LIBELE_ENTITY);
        assertThat(testEntite.getTypeEntitee()).isEqualTo(UPDATED_TYPE_ENTITEE);
        assertThat(testEntite.getManager()).isEqualTo(UPDATED_MANAGER);
    }

    @Test
    @Transactional
    public void updateNonExistingEntite() throws Exception {
        int databaseSizeBeforeUpdate = entiteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntiteMockMvc.perform(put("/api/entites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entite)))
            .andExpect(status().isBadRequest());

        // Validate the Entite in the database
        List<Entite> entiteList = entiteRepository.findAll();
        assertThat(entiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntite() throws Exception {
        // Initialize the database
        entiteRepository.saveAndFlush(entite);

        int databaseSizeBeforeDelete = entiteRepository.findAll().size();

        // Delete the entite
        restEntiteMockMvc.perform(delete("/api/entites/{id}", entite.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Entite> entiteList = entiteRepository.findAll();
        assertThat(entiteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
