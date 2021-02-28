package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.MyschoolApp;
import com.lastgeneration.product.domain.GeneralTimeTable;
import com.lastgeneration.product.repository.GeneralTimeTableRepository;
import com.lastgeneration.product.service.GeneralTimeTableService;

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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GeneralTimeTableResource} REST controller.
 */
@SpringBootTest(classes = MyschoolApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeneralTimeTableResourceIT {

    @Autowired
    private GeneralTimeTableRepository generalTimeTableRepository;

    @Autowired
    private GeneralTimeTableService generalTimeTableService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeneralTimeTableMockMvc;

    private GeneralTimeTable generalTimeTable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeneralTimeTable createEntity(EntityManager em) {
        GeneralTimeTable generalTimeTable = new GeneralTimeTable();
        return generalTimeTable;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeneralTimeTable createUpdatedEntity(EntityManager em) {
        GeneralTimeTable generalTimeTable = new GeneralTimeTable();
        return generalTimeTable;
    }

    @BeforeEach
    public void initTest() {
        generalTimeTable = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeneralTimeTable() throws Exception {
        int databaseSizeBeforeCreate = generalTimeTableRepository.findAll().size();
        // Create the GeneralTimeTable
        restGeneralTimeTableMockMvc.perform(post("/api/general-time-tables").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(generalTimeTable)))
            .andExpect(status().isCreated());

        // Validate the GeneralTimeTable in the database
        List<GeneralTimeTable> generalTimeTableList = generalTimeTableRepository.findAll();
        assertThat(generalTimeTableList).hasSize(databaseSizeBeforeCreate + 1);
        GeneralTimeTable testGeneralTimeTable = generalTimeTableList.get(generalTimeTableList.size() - 1);
    }

    @Test
    @Transactional
    public void createGeneralTimeTableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = generalTimeTableRepository.findAll().size();

        // Create the GeneralTimeTable with an existing ID
        generalTimeTable.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeneralTimeTableMockMvc.perform(post("/api/general-time-tables").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(generalTimeTable)))
            .andExpect(status().isBadRequest());

        // Validate the GeneralTimeTable in the database
        List<GeneralTimeTable> generalTimeTableList = generalTimeTableRepository.findAll();
        assertThat(generalTimeTableList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeneralTimeTables() throws Exception {
        // Initialize the database
        generalTimeTableRepository.saveAndFlush(generalTimeTable);

        // Get all the generalTimeTableList
        restGeneralTimeTableMockMvc.perform(get("/api/general-time-tables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(generalTimeTable.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getGeneralTimeTable() throws Exception {
        // Initialize the database
        generalTimeTableRepository.saveAndFlush(generalTimeTable);

        // Get the generalTimeTable
        restGeneralTimeTableMockMvc.perform(get("/api/general-time-tables/{id}", generalTimeTable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(generalTimeTable.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingGeneralTimeTable() throws Exception {
        // Get the generalTimeTable
        restGeneralTimeTableMockMvc.perform(get("/api/general-time-tables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeneralTimeTable() throws Exception {
        // Initialize the database
        generalTimeTableService.save(generalTimeTable);

        int databaseSizeBeforeUpdate = generalTimeTableRepository.findAll().size();

        // Update the generalTimeTable
        GeneralTimeTable updatedGeneralTimeTable = generalTimeTableRepository.findById(generalTimeTable.getId()).get();
        // Disconnect from session so that the updates on updatedGeneralTimeTable are not directly saved in db
        em.detach(updatedGeneralTimeTable);

        restGeneralTimeTableMockMvc.perform(put("/api/general-time-tables").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGeneralTimeTable)))
            .andExpect(status().isOk());

        // Validate the GeneralTimeTable in the database
        List<GeneralTimeTable> generalTimeTableList = generalTimeTableRepository.findAll();
        assertThat(generalTimeTableList).hasSize(databaseSizeBeforeUpdate);
        GeneralTimeTable testGeneralTimeTable = generalTimeTableList.get(generalTimeTableList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingGeneralTimeTable() throws Exception {
        int databaseSizeBeforeUpdate = generalTimeTableRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeneralTimeTableMockMvc.perform(put("/api/general-time-tables").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(generalTimeTable)))
            .andExpect(status().isBadRequest());

        // Validate the GeneralTimeTable in the database
        List<GeneralTimeTable> generalTimeTableList = generalTimeTableRepository.findAll();
        assertThat(generalTimeTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeneralTimeTable() throws Exception {
        // Initialize the database
        generalTimeTableService.save(generalTimeTable);

        int databaseSizeBeforeDelete = generalTimeTableRepository.findAll().size();

        // Delete the generalTimeTable
        restGeneralTimeTableMockMvc.perform(delete("/api/general-time-tables/{id}", generalTimeTable.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeneralTimeTable> generalTimeTableList = generalTimeTableRepository.findAll();
        assertThat(generalTimeTableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
