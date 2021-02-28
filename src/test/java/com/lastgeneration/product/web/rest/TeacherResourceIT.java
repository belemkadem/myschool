package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.MyschoolApp;
import com.lastgeneration.product.domain.Teacher;
import com.lastgeneration.product.repository.TeacherRepository;
import com.lastgeneration.product.service.TeacherService;

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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lastgeneration.product.domain.enumeration.BloodGroup;
import com.lastgeneration.product.domain.enumeration.Gender;
/**
 * Integration tests for the {@link TeacherResource} REST controller.
 */
@SpringBootTest(classes = MyschoolApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TeacherResourceIT {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIALITY = "AAAAAAAAAA";
    private static final String UPDATED_SPECIALITY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ARABIC_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ARABIC_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ARABIC_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ARABIC_FIRST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());

    private static final BloodGroup DEFAULT_BLOOD_GROUP = BloodGroup.OP;
    private static final BloodGroup UPDATED_BLOOD_GROUP = BloodGroup.OM;

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTeacherMockMvc;

    private Teacher teacher;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Teacher createEntity(EntityManager em) {
        Teacher teacher = new Teacher()
            .designation(DEFAULT_DESIGNATION)
            .speciality(DEFAULT_SPECIALITY)
            .lastName(DEFAULT_LAST_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .arabicLastName(DEFAULT_ARABIC_LAST_NAME)
            .arabicFirstName(DEFAULT_ARABIC_FIRST_NAME)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .gender(DEFAULT_GENDER)
            .address(DEFAULT_ADDRESS)
            .email(DEFAULT_EMAIL)
            .picture(DEFAULT_PICTURE)
            .password(DEFAULT_PASSWORD)
            .nationality(DEFAULT_NATIONALITY);
        return teacher;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Teacher createUpdatedEntity(EntityManager em) {
        Teacher teacher = new Teacher()
            .designation(UPDATED_DESIGNATION)
            .speciality(UPDATED_SPECIALITY)
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .arabicLastName(UPDATED_ARABIC_LAST_NAME)
            .arabicFirstName(UPDATED_ARABIC_FIRST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .gender(UPDATED_GENDER)
            .address(UPDATED_ADDRESS)
            .email(UPDATED_EMAIL)
            .picture(UPDATED_PICTURE)
            .password(UPDATED_PASSWORD)
            .nationality(UPDATED_NATIONALITY);
        return teacher;
    }

    @BeforeEach
    public void initTest() {
        teacher = createEntity(em);
    }

    @Test
    @Transactional
    public void createTeacher() throws Exception {
        int databaseSizeBeforeCreate = teacherRepository.findAll().size();
        // Create the Teacher
        restTeacherMockMvc.perform(post("/api/teachers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(teacher)))
            .andExpect(status().isCreated());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeCreate + 1);
        Teacher testTeacher = teacherList.get(teacherList.size() - 1);
        assertThat(testTeacher.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testTeacher.getSpeciality()).isEqualTo(DEFAULT_SPECIALITY);
        assertThat(testTeacher.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testTeacher.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testTeacher.getArabicLastName()).isEqualTo(DEFAULT_ARABIC_LAST_NAME);
        assertThat(testTeacher.getArabicFirstName()).isEqualTo(DEFAULT_ARABIC_FIRST_NAME);
        assertThat(testTeacher.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testTeacher.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testTeacher.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testTeacher.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testTeacher.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testTeacher.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testTeacher.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testTeacher.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
    }

    @Test
    @Transactional
    public void createTeacherWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = teacherRepository.findAll().size();

        // Create the Teacher with an existing ID
        teacher.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTeacherMockMvc.perform(post("/api/teachers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(teacher)))
            .andExpect(status().isBadRequest());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTeachers() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        // Get all the teacherList
        restTeacherMockMvc.perform(get("/api/teachers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teacher.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].speciality").value(hasItem(DEFAULT_SPECIALITY)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].arabicLastName").value(hasItem(DEFAULT_ARABIC_LAST_NAME)))
            .andExpect(jsonPath("$.[*].arabicFirstName").value(hasItem(DEFAULT_ARABIC_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)));
    }
    
    @Test
    @Transactional
    public void getTeacher() throws Exception {
        // Initialize the database
        teacherRepository.saveAndFlush(teacher);

        // Get the teacher
        restTeacherMockMvc.perform(get("/api/teachers/{id}", teacher.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(teacher.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION))
            .andExpect(jsonPath("$.speciality").value(DEFAULT_SPECIALITY))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.arabicLastName").value(DEFAULT_ARABIC_LAST_NAME))
            .andExpect(jsonPath("$.arabicFirstName").value(DEFAULT_ARABIC_FIRST_NAME))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY));
    }
    @Test
    @Transactional
    public void getNonExistingTeacher() throws Exception {
        // Get the teacher
        restTeacherMockMvc.perform(get("/api/teachers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTeacher() throws Exception {
        // Initialize the database
        teacherService.save(teacher);

        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();

        // Update the teacher
        Teacher updatedTeacher = teacherRepository.findById(teacher.getId()).get();
        // Disconnect from session so that the updates on updatedTeacher are not directly saved in db
        em.detach(updatedTeacher);
        updatedTeacher
            .designation(UPDATED_DESIGNATION)
            .speciality(UPDATED_SPECIALITY)
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .arabicLastName(UPDATED_ARABIC_LAST_NAME)
            .arabicFirstName(UPDATED_ARABIC_FIRST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .gender(UPDATED_GENDER)
            .address(UPDATED_ADDRESS)
            .email(UPDATED_EMAIL)
            .picture(UPDATED_PICTURE)
            .password(UPDATED_PASSWORD)
            .nationality(UPDATED_NATIONALITY);

        restTeacherMockMvc.perform(put("/api/teachers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTeacher)))
            .andExpect(status().isOk());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
        Teacher testTeacher = teacherList.get(teacherList.size() - 1);
        assertThat(testTeacher.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testTeacher.getSpeciality()).isEqualTo(UPDATED_SPECIALITY);
        assertThat(testTeacher.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testTeacher.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testTeacher.getArabicLastName()).isEqualTo(UPDATED_ARABIC_LAST_NAME);
        assertThat(testTeacher.getArabicFirstName()).isEqualTo(UPDATED_ARABIC_FIRST_NAME);
        assertThat(testTeacher.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testTeacher.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testTeacher.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testTeacher.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testTeacher.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testTeacher.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testTeacher.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testTeacher.getNationality()).isEqualTo(UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    public void updateNonExistingTeacher() throws Exception {
        int databaseSizeBeforeUpdate = teacherRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeacherMockMvc.perform(put("/api/teachers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(teacher)))
            .andExpect(status().isBadRequest());

        // Validate the Teacher in the database
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTeacher() throws Exception {
        // Initialize the database
        teacherService.save(teacher);

        int databaseSizeBeforeDelete = teacherRepository.findAll().size();

        // Delete the teacher
        restTeacherMockMvc.perform(delete("/api/teachers/{id}", teacher.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Teacher> teacherList = teacherRepository.findAll();
        assertThat(teacherList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
