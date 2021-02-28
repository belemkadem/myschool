package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.MyschoolApp;
import com.lastgeneration.product.domain.TutorType;
import com.lastgeneration.product.repository.TutorTypeRepository;
import com.lastgeneration.product.service.TutorTypeService;

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
 * Integration tests for the {@link TutorTypeResource} REST controller.
 */
@SpringBootTest(classes = MyschoolApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TutorTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private TutorTypeRepository tutorTypeRepository;

    @Autowired
    private TutorTypeService tutorTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTutorTypeMockMvc;

    private TutorType tutorType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TutorType createEntity(EntityManager em) {
        TutorType tutorType = new TutorType()
            .name(DEFAULT_NAME);
        return tutorType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TutorType createUpdatedEntity(EntityManager em) {
        TutorType tutorType = new TutorType()
            .name(UPDATED_NAME);
        return tutorType;
    }

    @BeforeEach
    public void initTest() {
        tutorType = createEntity(em);
    }

    @Test
    @Transactional
    public void createTutorType() throws Exception {
        int databaseSizeBeforeCreate = tutorTypeRepository.findAll().size();
        // Create the TutorType
        restTutorTypeMockMvc.perform(post("/api/tutor-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorType)))
            .andExpect(status().isCreated());

        // Validate the TutorType in the database
        List<TutorType> tutorTypeList = tutorTypeRepository.findAll();
        assertThat(tutorTypeList).hasSize(databaseSizeBeforeCreate + 1);
        TutorType testTutorType = tutorTypeList.get(tutorTypeList.size() - 1);
        assertThat(testTutorType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createTutorTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tutorTypeRepository.findAll().size();

        // Create the TutorType with an existing ID
        tutorType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTutorTypeMockMvc.perform(post("/api/tutor-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorType)))
            .andExpect(status().isBadRequest());

        // Validate the TutorType in the database
        List<TutorType> tutorTypeList = tutorTypeRepository.findAll();
        assertThat(tutorTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTutorTypes() throws Exception {
        // Initialize the database
        tutorTypeRepository.saveAndFlush(tutorType);

        // Get all the tutorTypeList
        restTutorTypeMockMvc.perform(get("/api/tutor-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tutorType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getTutorType() throws Exception {
        // Initialize the database
        tutorTypeRepository.saveAndFlush(tutorType);

        // Get the tutorType
        restTutorTypeMockMvc.perform(get("/api/tutor-types/{id}", tutorType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tutorType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingTutorType() throws Exception {
        // Get the tutorType
        restTutorTypeMockMvc.perform(get("/api/tutor-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTutorType() throws Exception {
        // Initialize the database
        tutorTypeService.save(tutorType);

        int databaseSizeBeforeUpdate = tutorTypeRepository.findAll().size();

        // Update the tutorType
        TutorType updatedTutorType = tutorTypeRepository.findById(tutorType.getId()).get();
        // Disconnect from session so that the updates on updatedTutorType are not directly saved in db
        em.detach(updatedTutorType);
        updatedTutorType
            .name(UPDATED_NAME);

        restTutorTypeMockMvc.perform(put("/api/tutor-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTutorType)))
            .andExpect(status().isOk());

        // Validate the TutorType in the database
        List<TutorType> tutorTypeList = tutorTypeRepository.findAll();
        assertThat(tutorTypeList).hasSize(databaseSizeBeforeUpdate);
        TutorType testTutorType = tutorTypeList.get(tutorTypeList.size() - 1);
        assertThat(testTutorType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingTutorType() throws Exception {
        int databaseSizeBeforeUpdate = tutorTypeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTutorTypeMockMvc.perform(put("/api/tutor-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutorType)))
            .andExpect(status().isBadRequest());

        // Validate the TutorType in the database
        List<TutorType> tutorTypeList = tutorTypeRepository.findAll();
        assertThat(tutorTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTutorType() throws Exception {
        // Initialize the database
        tutorTypeService.save(tutorType);

        int databaseSizeBeforeDelete = tutorTypeRepository.findAll().size();

        // Delete the tutorType
        restTutorTypeMockMvc.perform(delete("/api/tutor-types/{id}", tutorType.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TutorType> tutorTypeList = tutorTypeRepository.findAll();
        assertThat(tutorTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
