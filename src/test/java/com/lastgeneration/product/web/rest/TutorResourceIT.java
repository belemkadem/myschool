package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.MyschoolApp;
import com.lastgeneration.product.domain.Tutor;
import com.lastgeneration.product.repository.TutorRepository;
import com.lastgeneration.product.service.TutorService;

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

import com.lastgeneration.product.domain.enumeration.Gender;
/**
 * Integration tests for the {@link TutorResource} REST controller.
 */
@SpringBootTest(classes = MyschoolApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TutorResourceIT {

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ARABIC_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ARABIC_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ARABIC_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ARABIC_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NIN = "AAAAAAAAAA";
    private static final String UPDATED_NIN = "BBBBBBBBBB";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PASSOWRD = "AAAAAAAAAA";
    private static final String UPDATED_PASSOWRD = "BBBBBBBBBB";

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTutorMockMvc;

    private Tutor tutor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tutor createEntity(EntityManager em) {
        Tutor tutor = new Tutor()
            .lastName(DEFAULT_LAST_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .arabicLastName(DEFAULT_ARABIC_LAST_NAME)
            .arabicFirstName(DEFAULT_ARABIC_FIRST_NAME)
            .nin(DEFAULT_NIN)
            .gender(DEFAULT_GENDER)
            .address(DEFAULT_ADDRESS)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .email(DEFAULT_EMAIL)
            .passowrd(DEFAULT_PASSOWRD);
        return tutor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tutor createUpdatedEntity(EntityManager em) {
        Tutor tutor = new Tutor()
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .arabicLastName(UPDATED_ARABIC_LAST_NAME)
            .arabicFirstName(UPDATED_ARABIC_FIRST_NAME)
            .nin(UPDATED_NIN)
            .gender(UPDATED_GENDER)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .passowrd(UPDATED_PASSOWRD);
        return tutor;
    }

    @BeforeEach
    public void initTest() {
        tutor = createEntity(em);
    }

    @Test
    @Transactional
    public void createTutor() throws Exception {
        int databaseSizeBeforeCreate = tutorRepository.findAll().size();
        // Create the Tutor
        restTutorMockMvc.perform(post("/api/tutors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutor)))
            .andExpect(status().isCreated());

        // Validate the Tutor in the database
        List<Tutor> tutorList = tutorRepository.findAll();
        assertThat(tutorList).hasSize(databaseSizeBeforeCreate + 1);
        Tutor testTutor = tutorList.get(tutorList.size() - 1);
        assertThat(testTutor.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testTutor.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testTutor.getArabicLastName()).isEqualTo(DEFAULT_ARABIC_LAST_NAME);
        assertThat(testTutor.getArabicFirstName()).isEqualTo(DEFAULT_ARABIC_FIRST_NAME);
        assertThat(testTutor.getNin()).isEqualTo(DEFAULT_NIN);
        assertThat(testTutor.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testTutor.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testTutor.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testTutor.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testTutor.getPassowrd()).isEqualTo(DEFAULT_PASSOWRD);
    }

    @Test
    @Transactional
    public void createTutorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tutorRepository.findAll().size();

        // Create the Tutor with an existing ID
        tutor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTutorMockMvc.perform(post("/api/tutors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutor)))
            .andExpect(status().isBadRequest());

        // Validate the Tutor in the database
        List<Tutor> tutorList = tutorRepository.findAll();
        assertThat(tutorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTutors() throws Exception {
        // Initialize the database
        tutorRepository.saveAndFlush(tutor);

        // Get all the tutorList
        restTutorMockMvc.perform(get("/api/tutors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tutor.getId().intValue())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].arabicLastName").value(hasItem(DEFAULT_ARABIC_LAST_NAME)))
            .andExpect(jsonPath("$.[*].arabicFirstName").value(hasItem(DEFAULT_ARABIC_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].nin").value(hasItem(DEFAULT_NIN)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].passowrd").value(hasItem(DEFAULT_PASSOWRD)));
    }
    
    @Test
    @Transactional
    public void getTutor() throws Exception {
        // Initialize the database
        tutorRepository.saveAndFlush(tutor);

        // Get the tutor
        restTutorMockMvc.perform(get("/api/tutors/{id}", tutor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tutor.getId().intValue()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.arabicLastName").value(DEFAULT_ARABIC_LAST_NAME))
            .andExpect(jsonPath("$.arabicFirstName").value(DEFAULT_ARABIC_FIRST_NAME))
            .andExpect(jsonPath("$.nin").value(DEFAULT_NIN))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.passowrd").value(DEFAULT_PASSOWRD));
    }
    @Test
    @Transactional
    public void getNonExistingTutor() throws Exception {
        // Get the tutor
        restTutorMockMvc.perform(get("/api/tutors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTutor() throws Exception {
        // Initialize the database
        tutorService.save(tutor);

        int databaseSizeBeforeUpdate = tutorRepository.findAll().size();

        // Update the tutor
        Tutor updatedTutor = tutorRepository.findById(tutor.getId()).get();
        // Disconnect from session so that the updates on updatedTutor are not directly saved in db
        em.detach(updatedTutor);
        updatedTutor
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .arabicLastName(UPDATED_ARABIC_LAST_NAME)
            .arabicFirstName(UPDATED_ARABIC_FIRST_NAME)
            .nin(UPDATED_NIN)
            .gender(UPDATED_GENDER)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .passowrd(UPDATED_PASSOWRD);

        restTutorMockMvc.perform(put("/api/tutors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTutor)))
            .andExpect(status().isOk());

        // Validate the Tutor in the database
        List<Tutor> tutorList = tutorRepository.findAll();
        assertThat(tutorList).hasSize(databaseSizeBeforeUpdate);
        Tutor testTutor = tutorList.get(tutorList.size() - 1);
        assertThat(testTutor.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testTutor.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testTutor.getArabicLastName()).isEqualTo(UPDATED_ARABIC_LAST_NAME);
        assertThat(testTutor.getArabicFirstName()).isEqualTo(UPDATED_ARABIC_FIRST_NAME);
        assertThat(testTutor.getNin()).isEqualTo(UPDATED_NIN);
        assertThat(testTutor.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testTutor.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testTutor.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testTutor.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testTutor.getPassowrd()).isEqualTo(UPDATED_PASSOWRD);
    }

    @Test
    @Transactional
    public void updateNonExistingTutor() throws Exception {
        int databaseSizeBeforeUpdate = tutorRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTutorMockMvc.perform(put("/api/tutors").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tutor)))
            .andExpect(status().isBadRequest());

        // Validate the Tutor in the database
        List<Tutor> tutorList = tutorRepository.findAll();
        assertThat(tutorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTutor() throws Exception {
        // Initialize the database
        tutorService.save(tutor);

        int databaseSizeBeforeDelete = tutorRepository.findAll().size();

        // Delete the tutor
        restTutorMockMvc.perform(delete("/api/tutors/{id}", tutor.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tutor> tutorList = tutorRepository.findAll();
        assertThat(tutorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
