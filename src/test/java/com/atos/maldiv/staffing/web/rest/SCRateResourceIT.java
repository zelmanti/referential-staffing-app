package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.ReferentialStaffingApp;
import com.atos.maldiv.staffing.domain.SCRate;
import com.atos.maldiv.staffing.repository.SCRateRepository;

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
 * Integration tests for the {@link SCRateResource} REST controller.
 */
@SpringBootTest(classes = ReferentialStaffingApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SCRateResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NIVEAU = "AAAAAAAAAA";
    private static final String UPDATED_NIVEAU = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALISATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCALISATION = "BBBBBBBBBB";

    private static final String DEFAULT_MONTANT = "AAAAAAAAAA";
    private static final String UPDATED_MONTANT = "BBBBBBBBBB";

    @Autowired
    private SCRateRepository sCRateRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSCRateMockMvc;

    private SCRate sCRate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SCRate createEntity(EntityManager em) {
        SCRate sCRate = new SCRate()
            .code(DEFAULT_CODE)
            .niveau(DEFAULT_NIVEAU)
            .localisation(DEFAULT_LOCALISATION)
            .montant(DEFAULT_MONTANT);
        return sCRate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SCRate createUpdatedEntity(EntityManager em) {
        SCRate sCRate = new SCRate()
            .code(UPDATED_CODE)
            .niveau(UPDATED_NIVEAU)
            .localisation(UPDATED_LOCALISATION)
            .montant(UPDATED_MONTANT);
        return sCRate;
    }

    @BeforeEach
    public void initTest() {
        sCRate = createEntity(em);
    }

    @Test
    @Transactional
    public void createSCRate() throws Exception {
        int databaseSizeBeforeCreate = sCRateRepository.findAll().size();
        // Create the SCRate
        restSCRateMockMvc.perform(post("/api/sc-rates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sCRate)))
            .andExpect(status().isCreated());

        // Validate the SCRate in the database
        List<SCRate> sCRateList = sCRateRepository.findAll();
        assertThat(sCRateList).hasSize(databaseSizeBeforeCreate + 1);
        SCRate testSCRate = sCRateList.get(sCRateList.size() - 1);
        assertThat(testSCRate.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSCRate.getNiveau()).isEqualTo(DEFAULT_NIVEAU);
        assertThat(testSCRate.getLocalisation()).isEqualTo(DEFAULT_LOCALISATION);
        assertThat(testSCRate.getMontant()).isEqualTo(DEFAULT_MONTANT);
    }

    @Test
    @Transactional
    public void createSCRateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sCRateRepository.findAll().size();

        // Create the SCRate with an existing ID
        sCRate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSCRateMockMvc.perform(post("/api/sc-rates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sCRate)))
            .andExpect(status().isBadRequest());

        // Validate the SCRate in the database
        List<SCRate> sCRateList = sCRateRepository.findAll();
        assertThat(sCRateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSCRates() throws Exception {
        // Initialize the database
        sCRateRepository.saveAndFlush(sCRate);

        // Get all the sCRateList
        restSCRateMockMvc.perform(get("/api/sc-rates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sCRate.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].niveau").value(hasItem(DEFAULT_NIVEAU)))
            .andExpect(jsonPath("$.[*].localisation").value(hasItem(DEFAULT_LOCALISATION)))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT)));
    }
    
    @Test
    @Transactional
    public void getSCRate() throws Exception {
        // Initialize the database
        sCRateRepository.saveAndFlush(sCRate);

        // Get the sCRate
        restSCRateMockMvc.perform(get("/api/sc-rates/{id}", sCRate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sCRate.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.niveau").value(DEFAULT_NIVEAU))
            .andExpect(jsonPath("$.localisation").value(DEFAULT_LOCALISATION))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT));
    }
    @Test
    @Transactional
    public void getNonExistingSCRate() throws Exception {
        // Get the sCRate
        restSCRateMockMvc.perform(get("/api/sc-rates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSCRate() throws Exception {
        // Initialize the database
        sCRateRepository.saveAndFlush(sCRate);

        int databaseSizeBeforeUpdate = sCRateRepository.findAll().size();

        // Update the sCRate
        SCRate updatedSCRate = sCRateRepository.findById(sCRate.getId()).get();
        // Disconnect from session so that the updates on updatedSCRate are not directly saved in db
        em.detach(updatedSCRate);
        updatedSCRate
            .code(UPDATED_CODE)
            .niveau(UPDATED_NIVEAU)
            .localisation(UPDATED_LOCALISATION)
            .montant(UPDATED_MONTANT);

        restSCRateMockMvc.perform(put("/api/sc-rates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSCRate)))
            .andExpect(status().isOk());

        // Validate the SCRate in the database
        List<SCRate> sCRateList = sCRateRepository.findAll();
        assertThat(sCRateList).hasSize(databaseSizeBeforeUpdate);
        SCRate testSCRate = sCRateList.get(sCRateList.size() - 1);
        assertThat(testSCRate.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSCRate.getNiveau()).isEqualTo(UPDATED_NIVEAU);
        assertThat(testSCRate.getLocalisation()).isEqualTo(UPDATED_LOCALISATION);
        assertThat(testSCRate.getMontant()).isEqualTo(UPDATED_MONTANT);
    }

    @Test
    @Transactional
    public void updateNonExistingSCRate() throws Exception {
        int databaseSizeBeforeUpdate = sCRateRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSCRateMockMvc.perform(put("/api/sc-rates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sCRate)))
            .andExpect(status().isBadRequest());

        // Validate the SCRate in the database
        List<SCRate> sCRateList = sCRateRepository.findAll();
        assertThat(sCRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSCRate() throws Exception {
        // Initialize the database
        sCRateRepository.saveAndFlush(sCRate);

        int databaseSizeBeforeDelete = sCRateRepository.findAll().size();

        // Delete the sCRate
        restSCRateMockMvc.perform(delete("/api/sc-rates/{id}", sCRate.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SCRate> sCRateList = sCRateRepository.findAll();
        assertThat(sCRateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
