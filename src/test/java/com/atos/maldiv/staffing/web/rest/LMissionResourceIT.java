package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.ReferentialStaffingApp;
import com.atos.maldiv.staffing.domain.LMission;
import com.atos.maldiv.staffing.repository.LMissionRepository;

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
 * Integration tests for the {@link LMissionResource} REST controller.
 */
@SpringBootTest(classes = ReferentialStaffingApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LMissionResourceIT {

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION_MISSION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_MISSION = "BBBBBBBBBB";

    private static final String DEFAULT_INDICATEUR_RENOUVELEMENT = "AAAAAAAAAA";
    private static final String UPDATED_INDICATEUR_RENOUVELEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_IS_ACTIVE = "AAAAAAAAAA";
    private static final String UPDATED_IS_ACTIVE = "BBBBBBBBBB";

    @Autowired
    private LMissionRepository lMissionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLMissionMockMvc;

    private LMission lMission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LMission createEntity(EntityManager em) {
        LMission lMission = new LMission()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .descriptionMission(DEFAULT_DESCRIPTION_MISSION)
            .indicateurRenouvelement(DEFAULT_INDICATEUR_RENOUVELEMENT)
            .isActive(DEFAULT_IS_ACTIVE);
        return lMission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LMission createUpdatedEntity(EntityManager em) {
        LMission lMission = new LMission()
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .descriptionMission(UPDATED_DESCRIPTION_MISSION)
            .indicateurRenouvelement(UPDATED_INDICATEUR_RENOUVELEMENT)
            .isActive(UPDATED_IS_ACTIVE);
        return lMission;
    }

    @BeforeEach
    public void initTest() {
        lMission = createEntity(em);
    }

    @Test
    @Transactional
    public void createLMission() throws Exception {
        int databaseSizeBeforeCreate = lMissionRepository.findAll().size();
        // Create the LMission
        restLMissionMockMvc.perform(post("/api/l-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lMission)))
            .andExpect(status().isCreated());

        // Validate the LMission in the database
        List<LMission> lMissionList = lMissionRepository.findAll();
        assertThat(lMissionList).hasSize(databaseSizeBeforeCreate + 1);
        LMission testLMission = lMissionList.get(lMissionList.size() - 1);
        assertThat(testLMission.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testLMission.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testLMission.getDescriptionMission()).isEqualTo(DEFAULT_DESCRIPTION_MISSION);
        assertThat(testLMission.getIndicateurRenouvelement()).isEqualTo(DEFAULT_INDICATEUR_RENOUVELEMENT);
        assertThat(testLMission.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void createLMissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lMissionRepository.findAll().size();

        // Create the LMission with an existing ID
        lMission.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLMissionMockMvc.perform(post("/api/l-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lMission)))
            .andExpect(status().isBadRequest());

        // Validate the LMission in the database
        List<LMission> lMissionList = lMissionRepository.findAll();
        assertThat(lMissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLMissions() throws Exception {
        // Initialize the database
        lMissionRepository.saveAndFlush(lMission);

        // Get all the lMissionList
        restLMissionMockMvc.perform(get("/api/l-missions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lMission.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].descriptionMission").value(hasItem(DEFAULT_DESCRIPTION_MISSION)))
            .andExpect(jsonPath("$.[*].indicateurRenouvelement").value(hasItem(DEFAULT_INDICATEUR_RENOUVELEMENT)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)));
    }
    
    @Test
    @Transactional
    public void getLMission() throws Exception {
        // Initialize the database
        lMissionRepository.saveAndFlush(lMission);

        // Get the lMission
        restLMissionMockMvc.perform(get("/api/l-missions/{id}", lMission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lMission.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.descriptionMission").value(DEFAULT_DESCRIPTION_MISSION))
            .andExpect(jsonPath("$.indicateurRenouvelement").value(DEFAULT_INDICATEUR_RENOUVELEMENT))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE));
    }
    @Test
    @Transactional
    public void getNonExistingLMission() throws Exception {
        // Get the lMission
        restLMissionMockMvc.perform(get("/api/l-missions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLMission() throws Exception {
        // Initialize the database
        lMissionRepository.saveAndFlush(lMission);

        int databaseSizeBeforeUpdate = lMissionRepository.findAll().size();

        // Update the lMission
        LMission updatedLMission = lMissionRepository.findById(lMission.getId()).get();
        // Disconnect from session so that the updates on updatedLMission are not directly saved in db
        em.detach(updatedLMission);
        updatedLMission
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .descriptionMission(UPDATED_DESCRIPTION_MISSION)
            .indicateurRenouvelement(UPDATED_INDICATEUR_RENOUVELEMENT)
            .isActive(UPDATED_IS_ACTIVE);

        restLMissionMockMvc.perform(put("/api/l-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLMission)))
            .andExpect(status().isOk());

        // Validate the LMission in the database
        List<LMission> lMissionList = lMissionRepository.findAll();
        assertThat(lMissionList).hasSize(databaseSizeBeforeUpdate);
        LMission testLMission = lMissionList.get(lMissionList.size() - 1);
        assertThat(testLMission.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testLMission.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testLMission.getDescriptionMission()).isEqualTo(UPDATED_DESCRIPTION_MISSION);
        assertThat(testLMission.getIndicateurRenouvelement()).isEqualTo(UPDATED_INDICATEUR_RENOUVELEMENT);
        assertThat(testLMission.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingLMission() throws Exception {
        int databaseSizeBeforeUpdate = lMissionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLMissionMockMvc.perform(put("/api/l-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lMission)))
            .andExpect(status().isBadRequest());

        // Validate the LMission in the database
        List<LMission> lMissionList = lMissionRepository.findAll();
        assertThat(lMissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLMission() throws Exception {
        // Initialize the database
        lMissionRepository.saveAndFlush(lMission);

        int databaseSizeBeforeDelete = lMissionRepository.findAll().size();

        // Delete the lMission
        restLMissionMockMvc.perform(delete("/api/l-missions/{id}", lMission.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LMission> lMissionList = lMissionRepository.findAll();
        assertThat(lMissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
