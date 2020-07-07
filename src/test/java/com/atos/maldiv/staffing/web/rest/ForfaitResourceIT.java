package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.ReferentialStaffingApp;
import com.atos.maldiv.staffing.domain.Forfait;
import com.atos.maldiv.staffing.repository.ForfaitRepository;

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
 * Integration tests for the {@link ForfaitResource} REST controller.
 */
@SpringBootTest(classes = ReferentialStaffingApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ForfaitResourceIT {

    private static final String DEFAULT_PROJET = "AAAAAAAAAA";
    private static final String UPDATED_PROJET = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESSOURCE = "AAAAAAAAAA";
    private static final String UPDATED_RESSOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_LIEU = "AAAAAAAAAA";
    private static final String UPDATED_LIEU = "BBBBBBBBBB";

    @Autowired
    private ForfaitRepository forfaitRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restForfaitMockMvc;

    private Forfait forfait;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Forfait createEntity(EntityManager em) {
        Forfait forfait = new Forfait()
            .projet(DEFAULT_PROJET)
            .responsable(DEFAULT_RESPONSABLE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .ressource(DEFAULT_RESSOURCE)
            .status(DEFAULT_STATUS)
            .lieu(DEFAULT_LIEU);
        return forfait;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Forfait createUpdatedEntity(EntityManager em) {
        Forfait forfait = new Forfait()
            .projet(UPDATED_PROJET)
            .responsable(UPDATED_RESPONSABLE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .ressource(UPDATED_RESSOURCE)
            .status(UPDATED_STATUS)
            .lieu(UPDATED_LIEU);
        return forfait;
    }

    @BeforeEach
    public void initTest() {
        forfait = createEntity(em);
    }

    @Test
    @Transactional
    public void createForfait() throws Exception {
        int databaseSizeBeforeCreate = forfaitRepository.findAll().size();
        // Create the Forfait
        restForfaitMockMvc.perform(post("/api/forfaits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(forfait)))
            .andExpect(status().isCreated());

        // Validate the Forfait in the database
        List<Forfait> forfaitList = forfaitRepository.findAll();
        assertThat(forfaitList).hasSize(databaseSizeBeforeCreate + 1);
        Forfait testForfait = forfaitList.get(forfaitList.size() - 1);
        assertThat(testForfait.getProjet()).isEqualTo(DEFAULT_PROJET);
        assertThat(testForfait.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testForfait.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testForfait.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testForfait.getRessource()).isEqualTo(DEFAULT_RESSOURCE);
        assertThat(testForfait.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testForfait.getLieu()).isEqualTo(DEFAULT_LIEU);
    }

    @Test
    @Transactional
    public void createForfaitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = forfaitRepository.findAll().size();

        // Create the Forfait with an existing ID
        forfait.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restForfaitMockMvc.perform(post("/api/forfaits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(forfait)))
            .andExpect(status().isBadRequest());

        // Validate the Forfait in the database
        List<Forfait> forfaitList = forfaitRepository.findAll();
        assertThat(forfaitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllForfaits() throws Exception {
        // Initialize the database
        forfaitRepository.saveAndFlush(forfait);

        // Get all the forfaitList
        restForfaitMockMvc.perform(get("/api/forfaits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(forfait.getId().intValue())))
            .andExpect(jsonPath("$.[*].projet").value(hasItem(DEFAULT_PROJET)))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].ressource").value(hasItem(DEFAULT_RESSOURCE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].lieu").value(hasItem(DEFAULT_LIEU)));
    }
    
    @Test
    @Transactional
    public void getForfait() throws Exception {
        // Initialize the database
        forfaitRepository.saveAndFlush(forfait);

        // Get the forfait
        restForfaitMockMvc.perform(get("/api/forfaits/{id}", forfait.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(forfait.getId().intValue()))
            .andExpect(jsonPath("$.projet").value(DEFAULT_PROJET))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.ressource").value(DEFAULT_RESSOURCE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.lieu").value(DEFAULT_LIEU));
    }
    @Test
    @Transactional
    public void getNonExistingForfait() throws Exception {
        // Get the forfait
        restForfaitMockMvc.perform(get("/api/forfaits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateForfait() throws Exception {
        // Initialize the database
        forfaitRepository.saveAndFlush(forfait);

        int databaseSizeBeforeUpdate = forfaitRepository.findAll().size();

        // Update the forfait
        Forfait updatedForfait = forfaitRepository.findById(forfait.getId()).get();
        // Disconnect from session so that the updates on updatedForfait are not directly saved in db
        em.detach(updatedForfait);
        updatedForfait
            .projet(UPDATED_PROJET)
            .responsable(UPDATED_RESPONSABLE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .ressource(UPDATED_RESSOURCE)
            .status(UPDATED_STATUS)
            .lieu(UPDATED_LIEU);

        restForfaitMockMvc.perform(put("/api/forfaits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedForfait)))
            .andExpect(status().isOk());

        // Validate the Forfait in the database
        List<Forfait> forfaitList = forfaitRepository.findAll();
        assertThat(forfaitList).hasSize(databaseSizeBeforeUpdate);
        Forfait testForfait = forfaitList.get(forfaitList.size() - 1);
        assertThat(testForfait.getProjet()).isEqualTo(UPDATED_PROJET);
        assertThat(testForfait.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testForfait.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testForfait.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testForfait.getRessource()).isEqualTo(UPDATED_RESSOURCE);
        assertThat(testForfait.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testForfait.getLieu()).isEqualTo(UPDATED_LIEU);
    }

    @Test
    @Transactional
    public void updateNonExistingForfait() throws Exception {
        int databaseSizeBeforeUpdate = forfaitRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restForfaitMockMvc.perform(put("/api/forfaits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(forfait)))
            .andExpect(status().isBadRequest());

        // Validate the Forfait in the database
        List<Forfait> forfaitList = forfaitRepository.findAll();
        assertThat(forfaitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteForfait() throws Exception {
        // Initialize the database
        forfaitRepository.saveAndFlush(forfait);

        int databaseSizeBeforeDelete = forfaitRepository.findAll().size();

        // Delete the forfait
        restForfaitMockMvc.perform(delete("/api/forfaits/{id}", forfait.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Forfait> forfaitList = forfaitRepository.findAll();
        assertThat(forfaitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
