package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.MyschoolApp;
import com.lastgeneration.product.domain.GradeClass;
import com.lastgeneration.product.repository.GradeClassRepository;
import com.lastgeneration.product.service.GradeClassService;

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
 * Integration tests for the {@link GradeClassResource} REST controller.
 */
@SpringBootTest(classes = MyschoolApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GradeClassResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private GradeClassRepository gradeClassRepository;

    @Autowired
    private GradeClassService gradeClassService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGradeClassMockMvc;

    private GradeClass gradeClass;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GradeClass createEntity(EntityManager em) {
        GradeClass gradeClass = new GradeClass()
            .name(DEFAULT_NAME);
        return gradeClass;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GradeClass createUpdatedEntity(EntityManager em) {
        GradeClass gradeClass = new GradeClass()
            .name(UPDATED_NAME);
        return gradeClass;
    }

    @BeforeEach
    public void initTest() {
        gradeClass = createEntity(em);
    }

    @Test
    @Transactional
    public void createGradeClass() throws Exception {
        int databaseSizeBeforeCreate = gradeClassRepository.findAll().size();
        // Create the GradeClass
        restGradeClassMockMvc.perform(post("/api/grade-classes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gradeClass)))
            .andExpect(status().isCreated());

        // Validate the GradeClass in the database
        List<GradeClass> gradeClassList = gradeClassRepository.findAll();
        assertThat(gradeClassList).hasSize(databaseSizeBeforeCreate + 1);
        GradeClass testGradeClass = gradeClassList.get(gradeClassList.size() - 1);
        assertThat(testGradeClass.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createGradeClassWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gradeClassRepository.findAll().size();

        // Create the GradeClass with an existing ID
        gradeClass.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGradeClassMockMvc.perform(post("/api/grade-classes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gradeClass)))
            .andExpect(status().isBadRequest());

        // Validate the GradeClass in the database
        List<GradeClass> gradeClassList = gradeClassRepository.findAll();
        assertThat(gradeClassList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGradeClasses() throws Exception {
        // Initialize the database
        gradeClassRepository.saveAndFlush(gradeClass);

        // Get all the gradeClassList
        restGradeClassMockMvc.perform(get("/api/grade-classes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gradeClass.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getGradeClass() throws Exception {
        // Initialize the database
        gradeClassRepository.saveAndFlush(gradeClass);

        // Get the gradeClass
        restGradeClassMockMvc.perform(get("/api/grade-classes/{id}", gradeClass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gradeClass.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingGradeClass() throws Exception {
        // Get the gradeClass
        restGradeClassMockMvc.perform(get("/api/grade-classes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGradeClass() throws Exception {
        // Initialize the database
        gradeClassService.save(gradeClass);

        int databaseSizeBeforeUpdate = gradeClassRepository.findAll().size();

        // Update the gradeClass
        GradeClass updatedGradeClass = gradeClassRepository.findById(gradeClass.getId()).get();
        // Disconnect from session so that the updates on updatedGradeClass are not directly saved in db
        em.detach(updatedGradeClass);
        updatedGradeClass
            .name(UPDATED_NAME);

        restGradeClassMockMvc.perform(put("/api/grade-classes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGradeClass)))
            .andExpect(status().isOk());

        // Validate the GradeClass in the database
        List<GradeClass> gradeClassList = gradeClassRepository.findAll();
        assertThat(gradeClassList).hasSize(databaseSizeBeforeUpdate);
        GradeClass testGradeClass = gradeClassList.get(gradeClassList.size() - 1);
        assertThat(testGradeClass.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingGradeClass() throws Exception {
        int databaseSizeBeforeUpdate = gradeClassRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGradeClassMockMvc.perform(put("/api/grade-classes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gradeClass)))
            .andExpect(status().isBadRequest());

        // Validate the GradeClass in the database
        List<GradeClass> gradeClassList = gradeClassRepository.findAll();
        assertThat(gradeClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGradeClass() throws Exception {
        // Initialize the database
        gradeClassService.save(gradeClass);

        int databaseSizeBeforeDelete = gradeClassRepository.findAll().size();

        // Delete the gradeClass
        restGradeClassMockMvc.perform(delete("/api/grade-classes/{id}", gradeClass.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GradeClass> gradeClassList = gradeClassRepository.findAll();
        assertThat(gradeClassList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
