package com.atos.maldiv.staffing.web.rest;

import com.atos.maldiv.staffing.ReferentialStaffingApp;
import com.atos.maldiv.staffing.domain.Client;
import com.atos.maldiv.staffing.repository.ClientRepository;

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
 * Integration tests for the {@link ClientResource} REST controller.
 */
@SpringBootTest(classes = ReferentialStaffingApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClientResourceIT {

    private static final String DEFAULT_NAME_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_NAM_CLIENT_IRIS = "AAAAAAAAAA";
    private static final String UPDATED_NAM_CLIENT_IRIS = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CLIENT_IRIS = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CLIENT_IRIS = "BBBBBBBBBB";

    private static final String DEFAULT_MARCHE = "AAAAAAAAAA";
    private static final String UPDATED_MARCHE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientMockMvc;

    private Client client;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createEntity(EntityManager em) {
        Client client = new Client()
            .nameClient(DEFAULT_NAME_CLIENT)
            .namClientIris(DEFAULT_NAM_CLIENT_IRIS)
            .codeClientIris(DEFAULT_CODE_CLIENT_IRIS)
            .marche(DEFAULT_MARCHE)
            .isActive(DEFAULT_IS_ACTIVE);
        return client;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createUpdatedEntity(EntityManager em) {
        Client client = new Client()
            .nameClient(UPDATED_NAME_CLIENT)
            .namClientIris(UPDATED_NAM_CLIENT_IRIS)
            .codeClientIris(UPDATED_CODE_CLIENT_IRIS)
            .marche(UPDATED_MARCHE)
            .isActive(UPDATED_IS_ACTIVE);
        return client;
    }

    @BeforeEach
    public void initTest() {
        client = createEntity(em);
    }

    @Test
    @Transactional
    public void createClient() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();
        // Create the Client
        restClientMockMvc.perform(post("/api/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(client)))
            .andExpect(status().isCreated());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate + 1);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getNameClient()).isEqualTo(DEFAULT_NAME_CLIENT);
        assertThat(testClient.getNamClientIris()).isEqualTo(DEFAULT_NAM_CLIENT_IRIS);
        assertThat(testClient.getCodeClientIris()).isEqualTo(DEFAULT_CODE_CLIENT_IRIS);
        assertThat(testClient.getMarche()).isEqualTo(DEFAULT_MARCHE);
        assertThat(testClient.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void createClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();

        // Create the Client with an existing ID
        client.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientMockMvc.perform(post("/api/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(client)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClients() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList
        restClientMockMvc.perform(get("/api/clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameClient").value(hasItem(DEFAULT_NAME_CLIENT)))
            .andExpect(jsonPath("$.[*].namClientIris").value(hasItem(DEFAULT_NAM_CLIENT_IRIS)))
            .andExpect(jsonPath("$.[*].codeClientIris").value(hasItem(DEFAULT_CODE_CLIENT_IRIS)))
            .andExpect(jsonPath("$.[*].marche").value(hasItem(DEFAULT_MARCHE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", client.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(client.getId().intValue()))
            .andExpect(jsonPath("$.nameClient").value(DEFAULT_NAME_CLIENT))
            .andExpect(jsonPath("$.namClientIris").value(DEFAULT_NAM_CLIENT_IRIS))
            .andExpect(jsonPath("$.codeClientIris").value(DEFAULT_CODE_CLIENT_IRIS))
            .andExpect(jsonPath("$.marche").value(DEFAULT_MARCHE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingClient() throws Exception {
        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client
        Client updatedClient = clientRepository.findById(client.getId()).get();
        // Disconnect from session so that the updates on updatedClient are not directly saved in db
        em.detach(updatedClient);
        updatedClient
            .nameClient(UPDATED_NAME_CLIENT)
            .namClientIris(UPDATED_NAM_CLIENT_IRIS)
            .codeClientIris(UPDATED_CODE_CLIENT_IRIS)
            .marche(UPDATED_MARCHE)
            .isActive(UPDATED_IS_ACTIVE);

        restClientMockMvc.perform(put("/api/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedClient)))
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getNameClient()).isEqualTo(UPDATED_NAME_CLIENT);
        assertThat(testClient.getNamClientIris()).isEqualTo(UPDATED_NAM_CLIENT_IRIS);
        assertThat(testClient.getCodeClientIris()).isEqualTo(UPDATED_CODE_CLIENT_IRIS);
        assertThat(testClient.getMarche()).isEqualTo(UPDATED_MARCHE);
        assertThat(testClient.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientMockMvc.perform(put("/api/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(client)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeDelete = clientRepository.findAll().size();

        // Delete the client
        restClientMockMvc.perform(delete("/api/clients/{id}", client.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
