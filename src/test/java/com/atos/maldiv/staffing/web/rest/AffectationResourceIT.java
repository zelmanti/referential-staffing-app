package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.ReferentialStaffingApp;
import com.atos.maldiv.staffing.domain.Affectation;
import com.atos.maldiv.staffing.repository.AffectationRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AffectationResource} REST controller.
 */
@SpringBootTest(classes = ReferentialStaffingApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AffectationResourceIT {

    private static final String DEFAULT_RESSOURCE = "AAAAAAAAAA";
    private static final String UPDATED_RESSOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_RATTACHEMENT_RESSOURCE = "AAAAAAAAAA";
    private static final String UPDATED_RATTACHEMENT_RESSOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_PRESTATION = "AAAAAAAAAA";
    private static final String UPDATED_PRESTATION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_PROJET = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PROJET = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_RATTACHEMENT_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_RATTACHEMENT_CLIENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AffectationRepository affectationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAffectationMockMvc;

    private Affectation affectation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Affectation createEntity(EntityManager em) {
        Affectation affectation = new Affectation()
            .ressource(DEFAULT_RESSOURCE)
            .rattachementRessource(DEFAULT_RATTACHEMENT_RESSOURCE)
            .prestation(DEFAULT_PRESTATION)
            .codeProjet(DEFAULT_CODE_PROJET)
            .status(DEFAULT_STATUS)
            .client(DEFAULT_CLIENT)
            .rattachementClient(DEFAULT_RATTACHEMENT_CLIENT)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return affectation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Affectation createUpdatedEntity(EntityManager em) {
        Affectation affectation = new Affectation()
            .ressource(UPDATED_RESSOURCE)
            .rattachementRessource(UPDATED_RATTACHEMENT_RESSOURCE)
            .prestation(UPDATED_PRESTATION)
            .codeProjet(UPDATED_CODE_PROJET)
            .status(UPDATED_STATUS)
            .client(UPDATED_CLIENT)
            .rattachementClient(UPDATED_RATTACHEMENT_CLIENT)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        return affectation;
    }

    @BeforeEach
    public void initTest() {
        affectation = createEntity(em);
    }

    @Test
    @Transactional
    public void createAffectation() throws Exception {
        int databaseSizeBeforeCreate = affectationRepository.findAll().size();
        // Create the Affectation
        restAffectationMockMvc.perform(post("/api/affectations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affectation)))
            .andExpect(status().isCreated());

        // Validate the Affectation in the database
        List<Affectation> affectationList = affectationRepository.findAll();
        assertThat(affectationList).hasSize(databaseSizeBeforeCreate + 1);
        Affectation testAffectation = affectationList.get(affectationList.size() - 1);
        assertThat(testAffectation.getRessource()).isEqualTo(DEFAULT_RESSOURCE);
        assertThat(testAffectation.getRattachementRessource()).isEqualTo(DEFAULT_RATTACHEMENT_RESSOURCE);
        assertThat(testAffectation.getPrestation()).isEqualTo(DEFAULT_PRESTATION);
        assertThat(testAffectation.getCodeProjet()).isEqualTo(DEFAULT_CODE_PROJET);
        assertThat(testAffectation.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAffectation.getClient()).isEqualTo(DEFAULT_CLIENT);
        assertThat(testAffectation.getRattachementClient()).isEqualTo(DEFAULT_RATTACHEMENT_CLIENT);
        assertThat(testAffectation.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testAffectation.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createAffectationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = affectationRepository.findAll().size();

        // Create the Affectation with an existing ID
        affectation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAffectationMockMvc.perform(post("/api/affectations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affectation)))
            .andExpect(status().isBadRequest());

        // Validate the Affectation in the database
        List<Affectation> affectationList = affectationRepository.findAll();
        assertThat(affectationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAffectations() throws Exception {
        // Initialize the database
        affectationRepository.saveAndFlush(affectation);

        // Get all the affectationList
        restAffectationMockMvc.perform(get("/api/affectations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(affectation.getId().intValue())))
            .andExpect(jsonPath("$.[*].ressource").value(hasItem(DEFAULT_RESSOURCE)))
            .andExpect(jsonPath("$.[*].rattachementRessource").value(hasItem(DEFAULT_RATTACHEMENT_RESSOURCE)))
            .andExpect(jsonPath("$.[*].prestation").value(hasItem(DEFAULT_PRESTATION)))
            .andExpect(jsonPath("$.[*].codeProjet").value(hasItem(DEFAULT_CODE_PROJET)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT)))
            .andExpect(jsonPath("$.[*].rattachementClient").value(hasItem(DEFAULT_RATTACHEMENT_CLIENT)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getAffectation() throws Exception {
        // Initialize the database
        affectationRepository.saveAndFlush(affectation);

        // Get the affectation
        restAffectationMockMvc.perform(get("/api/affectations/{id}", affectation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(affectation.getId().intValue()))
            .andExpect(jsonPath("$.ressource").value(DEFAULT_RESSOURCE))
            .andExpect(jsonPath("$.rattachementRessource").value(DEFAULT_RATTACHEMENT_RESSOURCE))
            .andExpect(jsonPath("$.prestation").value(DEFAULT_PRESTATION))
            .andExpect(jsonPath("$.codeProjet").value(DEFAULT_CODE_PROJET))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.client").value(DEFAULT_CLIENT))
            .andExpect(jsonPath("$.rattachementClient").value(DEFAULT_RATTACHEMENT_CLIENT))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAffectation() throws Exception {
        // Get the affectation
        restAffectationMockMvc.perform(get("/api/affectations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAffectation() throws Exception {
        // Initialize the database
        affectationRepository.saveAndFlush(affectation);

        int databaseSizeBeforeUpdate = affectationRepository.findAll().size();

        // Update the affectation
        Affectation updatedAffectation = affectationRepository.findById(affectation.getId()).get();
        // Disconnect from session so that the updates on updatedAffectation are not directly saved in db
        em.detach(updatedAffectation);
        updatedAffectation
            .ressource(UPDATED_RESSOURCE)
            .rattachementRessource(UPDATED_RATTACHEMENT_RESSOURCE)
            .prestation(UPDATED_PRESTATION)
            .codeProjet(UPDATED_CODE_PROJET)
            .status(UPDATED_STATUS)
            .client(UPDATED_CLIENT)
            .rattachementClient(UPDATED_RATTACHEMENT_CLIENT)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);

        restAffectationMockMvc.perform(put("/api/affectations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAffectation)))
            .andExpect(status().isOk());

        // Validate the Affectation in the database
        List<Affectation> affectationList = affectationRepository.findAll();
        assertThat(affectationList).hasSize(databaseSizeBeforeUpdate);
        Affectation testAffectation = affectationList.get(affectationList.size() - 1);
        assertThat(testAffectation.getRessource()).isEqualTo(UPDATED_RESSOURCE);
        assertThat(testAffectation.getRattachementRessource()).isEqualTo(UPDATED_RATTACHEMENT_RESSOURCE);
        assertThat(testAffectation.getPrestation()).isEqualTo(UPDATED_PRESTATION);
        assertThat(testAffectation.getCodeProjet()).isEqualTo(UPDATED_CODE_PROJET);
        assertThat(testAffectation.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAffectation.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testAffectation.getRattachementClient()).isEqualTo(UPDATED_RATTACHEMENT_CLIENT);
        assertThat(testAffectation.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAffectation.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingAffectation() throws Exception {
        int databaseSizeBeforeUpdate = affectationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAffectationMockMvc.perform(put("/api/affectations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affectation)))
            .andExpect(status().isBadRequest());

        // Validate the Affectation in the database
        List<Affectation> affectationList = affectationRepository.findAll();
        assertThat(affectationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAffectation() throws Exception {
        // Initialize the database
        affectationRepository.saveAndFlush(affectation);

        int databaseSizeBeforeDelete = affectationRepository.findAll().size();

        // Delete the affectation
        restAffectationMockMvc.perform(delete("/api/affectations/{id}", affectation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Affectation> affectationList = affectationRepository.findAll();
        assertThat(affectationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
