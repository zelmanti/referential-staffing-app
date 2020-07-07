package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.ReferentialStaffingApp;
import com.atos.maldiv.staffing.domain.DFinanciere;
import com.atos.maldiv.staffing.repository.DFinanciereRepository;

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
 * Integration tests for the {@link DFinanciereResource} REST controller.
 */
@SpringBootTest(classes = ReferentialStaffingApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DFinanciereResourceIT {

    private static final String DEFAULT_START_DATE = "AAAAAAAAAA";
    private static final String UPDATED_START_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_END_DATE = "AAAAAAAAAA";
    private static final String UPDATED_END_DATE = "BBBBBBBBBB";

    private static final Double DEFAULT_TJM = 1D;
    private static final Double UPDATED_TJM = 2D;

    private static final Double DEFAULT_RFA = 1D;
    private static final Double UPDATED_RFA = 2D;

    private static final Double DEFAULT_FRAIS_MENTUELS = 1D;
    private static final Double UPDATED_FRAIS_MENTUELS = 2D;

    private static final Double DEFAULT_FRAIS_JOURNALIERS = 1D;
    private static final Double UPDATED_FRAIS_JOURNALIERS = 2D;

    private static final Double DEFAULT_MARGE_CALCULEE = 1D;
    private static final Double UPDATED_MARGE_CALCULEE = 2D;

    private static final String DEFAULT_INDICATEUR_T_REVENUE = "AAAAAAAAAA";
    private static final String UPDATED_INDICATEUR_T_REVENUE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_JOURS_TRAVAILEE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_JOURS_TRAVAILEE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_MOIS_TRAVAILEE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MOIS_TRAVAILEE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ANNEES_TRAVAILEE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANNEES_TRAVAILEE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SCR = "AAAAAAAAAA";
    private static final String UPDATED_SCR = "BBBBBBBBBB";

    private static final Double DEFAULT_CHIFFRE_AFFAIRE_CALCULEE = 1D;
    private static final Double UPDATED_CHIFFRE_AFFAIRE_CALCULEE = 2D;

    private static final Double DEFAULT_COUTS_CALCULEE = 1D;
    private static final Double UPDATED_COUTS_CALCULEE = 2D;

    @Autowired
    private DFinanciereRepository dFinanciereRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDFinanciereMockMvc;

    private DFinanciere dFinanciere;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DFinanciere createEntity(EntityManager em) {
        DFinanciere dFinanciere = new DFinanciere()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .tjm(DEFAULT_TJM)
            .rfa(DEFAULT_RFA)
            .fraisMentuels(DEFAULT_FRAIS_MENTUELS)
            .fraisJournaliers(DEFAULT_FRAIS_JOURNALIERS)
            .margeCalculee(DEFAULT_MARGE_CALCULEE)
            .indicateurTRevenue(DEFAULT_INDICATEUR_T_REVENUE)
            .joursTravailee(DEFAULT_JOURS_TRAVAILEE)
            .moisTravailee(DEFAULT_MOIS_TRAVAILEE)
            .anneesTravailee(DEFAULT_ANNEES_TRAVAILEE)
            .scr(DEFAULT_SCR)
            .chiffreAffaireCalculee(DEFAULT_CHIFFRE_AFFAIRE_CALCULEE)
            .coutsCalculee(DEFAULT_COUTS_CALCULEE);
        return dFinanciere;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DFinanciere createUpdatedEntity(EntityManager em) {
        DFinanciere dFinanciere = new DFinanciere()
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .tjm(UPDATED_TJM)
            .rfa(UPDATED_RFA)
            .fraisMentuels(UPDATED_FRAIS_MENTUELS)
            .fraisJournaliers(UPDATED_FRAIS_JOURNALIERS)
            .margeCalculee(UPDATED_MARGE_CALCULEE)
            .indicateurTRevenue(UPDATED_INDICATEUR_T_REVENUE)
            .joursTravailee(UPDATED_JOURS_TRAVAILEE)
            .moisTravailee(UPDATED_MOIS_TRAVAILEE)
            .anneesTravailee(UPDATED_ANNEES_TRAVAILEE)
            .scr(UPDATED_SCR)
            .chiffreAffaireCalculee(UPDATED_CHIFFRE_AFFAIRE_CALCULEE)
            .coutsCalculee(UPDATED_COUTS_CALCULEE);
        return dFinanciere;
    }

    @BeforeEach
    public void initTest() {
        dFinanciere = createEntity(em);
    }

    @Test
    @Transactional
    public void createDFinanciere() throws Exception {
        int databaseSizeBeforeCreate = dFinanciereRepository.findAll().size();
        // Create the DFinanciere
        restDFinanciereMockMvc.perform(post("/api/d-financieres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dFinanciere)))
            .andExpect(status().isCreated());

        // Validate the DFinanciere in the database
        List<DFinanciere> dFinanciereList = dFinanciereRepository.findAll();
        assertThat(dFinanciereList).hasSize(databaseSizeBeforeCreate + 1);
        DFinanciere testDFinanciere = dFinanciereList.get(dFinanciereList.size() - 1);
        assertThat(testDFinanciere.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testDFinanciere.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testDFinanciere.getTjm()).isEqualTo(DEFAULT_TJM);
        assertThat(testDFinanciere.getRfa()).isEqualTo(DEFAULT_RFA);
        assertThat(testDFinanciere.getFraisMentuels()).isEqualTo(DEFAULT_FRAIS_MENTUELS);
        assertThat(testDFinanciere.getFraisJournaliers()).isEqualTo(DEFAULT_FRAIS_JOURNALIERS);
        assertThat(testDFinanciere.getMargeCalculee()).isEqualTo(DEFAULT_MARGE_CALCULEE);
        assertThat(testDFinanciere.getIndicateurTRevenue()).isEqualTo(DEFAULT_INDICATEUR_T_REVENUE);
        assertThat(testDFinanciere.getJoursTravailee()).isEqualTo(DEFAULT_JOURS_TRAVAILEE);
        assertThat(testDFinanciere.getMoisTravailee()).isEqualTo(DEFAULT_MOIS_TRAVAILEE);
        assertThat(testDFinanciere.getAnneesTravailee()).isEqualTo(DEFAULT_ANNEES_TRAVAILEE);
        assertThat(testDFinanciere.getScr()).isEqualTo(DEFAULT_SCR);
        assertThat(testDFinanciere.getChiffreAffaireCalculee()).isEqualTo(DEFAULT_CHIFFRE_AFFAIRE_CALCULEE);
        assertThat(testDFinanciere.getCoutsCalculee()).isEqualTo(DEFAULT_COUTS_CALCULEE);
    }

    @Test
    @Transactional
    public void createDFinanciereWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dFinanciereRepository.findAll().size();

        // Create the DFinanciere with an existing ID
        dFinanciere.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDFinanciereMockMvc.perform(post("/api/d-financieres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dFinanciere)))
            .andExpect(status().isBadRequest());

        // Validate the DFinanciere in the database
        List<DFinanciere> dFinanciereList = dFinanciereRepository.findAll();
        assertThat(dFinanciereList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDFinancieres() throws Exception {
        // Initialize the database
        dFinanciereRepository.saveAndFlush(dFinanciere);

        // Get all the dFinanciereList
        restDFinanciereMockMvc.perform(get("/api/d-financieres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dFinanciere.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE)))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE)))
            .andExpect(jsonPath("$.[*].tjm").value(hasItem(DEFAULT_TJM.doubleValue())))
            .andExpect(jsonPath("$.[*].rfa").value(hasItem(DEFAULT_RFA.doubleValue())))
            .andExpect(jsonPath("$.[*].fraisMentuels").value(hasItem(DEFAULT_FRAIS_MENTUELS.doubleValue())))
            .andExpect(jsonPath("$.[*].fraisJournaliers").value(hasItem(DEFAULT_FRAIS_JOURNALIERS.doubleValue())))
            .andExpect(jsonPath("$.[*].margeCalculee").value(hasItem(DEFAULT_MARGE_CALCULEE.doubleValue())))
            .andExpect(jsonPath("$.[*].indicateurTRevenue").value(hasItem(DEFAULT_INDICATEUR_T_REVENUE)))
            .andExpect(jsonPath("$.[*].joursTravailee").value(hasItem(DEFAULT_JOURS_TRAVAILEE.toString())))
            .andExpect(jsonPath("$.[*].moisTravailee").value(hasItem(DEFAULT_MOIS_TRAVAILEE.toString())))
            .andExpect(jsonPath("$.[*].anneesTravailee").value(hasItem(DEFAULT_ANNEES_TRAVAILEE.toString())))
            .andExpect(jsonPath("$.[*].scr").value(hasItem(DEFAULT_SCR)))
            .andExpect(jsonPath("$.[*].chiffreAffaireCalculee").value(hasItem(DEFAULT_CHIFFRE_AFFAIRE_CALCULEE.doubleValue())))
            .andExpect(jsonPath("$.[*].coutsCalculee").value(hasItem(DEFAULT_COUTS_CALCULEE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDFinanciere() throws Exception {
        // Initialize the database
        dFinanciereRepository.saveAndFlush(dFinanciere);

        // Get the dFinanciere
        restDFinanciereMockMvc.perform(get("/api/d-financieres/{id}", dFinanciere.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dFinanciere.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE))
            .andExpect(jsonPath("$.tjm").value(DEFAULT_TJM.doubleValue()))
            .andExpect(jsonPath("$.rfa").value(DEFAULT_RFA.doubleValue()))
            .andExpect(jsonPath("$.fraisMentuels").value(DEFAULT_FRAIS_MENTUELS.doubleValue()))
            .andExpect(jsonPath("$.fraisJournaliers").value(DEFAULT_FRAIS_JOURNALIERS.doubleValue()))
            .andExpect(jsonPath("$.margeCalculee").value(DEFAULT_MARGE_CALCULEE.doubleValue()))
            .andExpect(jsonPath("$.indicateurTRevenue").value(DEFAULT_INDICATEUR_T_REVENUE))
            .andExpect(jsonPath("$.joursTravailee").value(DEFAULT_JOURS_TRAVAILEE.toString()))
            .andExpect(jsonPath("$.moisTravailee").value(DEFAULT_MOIS_TRAVAILEE.toString()))
            .andExpect(jsonPath("$.anneesTravailee").value(DEFAULT_ANNEES_TRAVAILEE.toString()))
            .andExpect(jsonPath("$.scr").value(DEFAULT_SCR))
            .andExpect(jsonPath("$.chiffreAffaireCalculee").value(DEFAULT_CHIFFRE_AFFAIRE_CALCULEE.doubleValue()))
            .andExpect(jsonPath("$.coutsCalculee").value(DEFAULT_COUTS_CALCULEE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDFinanciere() throws Exception {
        // Get the dFinanciere
        restDFinanciereMockMvc.perform(get("/api/d-financieres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDFinanciere() throws Exception {
        // Initialize the database
        dFinanciereRepository.saveAndFlush(dFinanciere);

        int databaseSizeBeforeUpdate = dFinanciereRepository.findAll().size();

        // Update the dFinanciere
        DFinanciere updatedDFinanciere = dFinanciereRepository.findById(dFinanciere.getId()).get();
        // Disconnect from session so that the updates on updatedDFinanciere are not directly saved in db
        em.detach(updatedDFinanciere);
        updatedDFinanciere
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .tjm(UPDATED_TJM)
            .rfa(UPDATED_RFA)
            .fraisMentuels(UPDATED_FRAIS_MENTUELS)
            .fraisJournaliers(UPDATED_FRAIS_JOURNALIERS)
            .margeCalculee(UPDATED_MARGE_CALCULEE)
            .indicateurTRevenue(UPDATED_INDICATEUR_T_REVENUE)
            .joursTravailee(UPDATED_JOURS_TRAVAILEE)
            .moisTravailee(UPDATED_MOIS_TRAVAILEE)
            .anneesTravailee(UPDATED_ANNEES_TRAVAILEE)
            .scr(UPDATED_SCR)
            .chiffreAffaireCalculee(UPDATED_CHIFFRE_AFFAIRE_CALCULEE)
            .coutsCalculee(UPDATED_COUTS_CALCULEE);

        restDFinanciereMockMvc.perform(put("/api/d-financieres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDFinanciere)))
            .andExpect(status().isOk());

        // Validate the DFinanciere in the database
        List<DFinanciere> dFinanciereList = dFinanciereRepository.findAll();
        assertThat(dFinanciereList).hasSize(databaseSizeBeforeUpdate);
        DFinanciere testDFinanciere = dFinanciereList.get(dFinanciereList.size() - 1);
        assertThat(testDFinanciere.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testDFinanciere.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testDFinanciere.getTjm()).isEqualTo(UPDATED_TJM);
        assertThat(testDFinanciere.getRfa()).isEqualTo(UPDATED_RFA);
        assertThat(testDFinanciere.getFraisMentuels()).isEqualTo(UPDATED_FRAIS_MENTUELS);
        assertThat(testDFinanciere.getFraisJournaliers()).isEqualTo(UPDATED_FRAIS_JOURNALIERS);
        assertThat(testDFinanciere.getMargeCalculee()).isEqualTo(UPDATED_MARGE_CALCULEE);
        assertThat(testDFinanciere.getIndicateurTRevenue()).isEqualTo(UPDATED_INDICATEUR_T_REVENUE);
        assertThat(testDFinanciere.getJoursTravailee()).isEqualTo(UPDATED_JOURS_TRAVAILEE);
        assertThat(testDFinanciere.getMoisTravailee()).isEqualTo(UPDATED_MOIS_TRAVAILEE);
        assertThat(testDFinanciere.getAnneesTravailee()).isEqualTo(UPDATED_ANNEES_TRAVAILEE);
        assertThat(testDFinanciere.getScr()).isEqualTo(UPDATED_SCR);
        assertThat(testDFinanciere.getChiffreAffaireCalculee()).isEqualTo(UPDATED_CHIFFRE_AFFAIRE_CALCULEE);
        assertThat(testDFinanciere.getCoutsCalculee()).isEqualTo(UPDATED_COUTS_CALCULEE);
    }

    @Test
    @Transactional
    public void updateNonExistingDFinanciere() throws Exception {
        int databaseSizeBeforeUpdate = dFinanciereRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDFinanciereMockMvc.perform(put("/api/d-financieres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dFinanciere)))
            .andExpect(status().isBadRequest());

        // Validate the DFinanciere in the database
        List<DFinanciere> dFinanciereList = dFinanciereRepository.findAll();
        assertThat(dFinanciereList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDFinanciere() throws Exception {
        // Initialize the database
        dFinanciereRepository.saveAndFlush(dFinanciere);

        int databaseSizeBeforeDelete = dFinanciereRepository.findAll().size();

        // Delete the dFinanciere
        restDFinanciereMockMvc.perform(delete("/api/d-financieres/{id}", dFinanciere.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DFinanciere> dFinanciereList = dFinanciereRepository.findAll();
        assertThat(dFinanciereList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
