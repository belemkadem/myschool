package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.MyschoolApp;
import com.lastgeneration.product.domain.Student;
import com.lastgeneration.product.repository.StudentRepository;
import com.lastgeneration.product.service.StudentService;

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
 * Integration tests for the {@link StudentResource} REST controller.
 */
@SpringBootTest(classes = MyschoolApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StudentResourceIT {

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

    private static final String DEFAULT_CLASSROOM = "AAAAAAAAAA";
    private static final String UPDATED_CLASSROOM = "BBBBBBBBBB";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    private static final String DEFAULT_SCHOOL_OF_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOL_OF_ORIGIN = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStudentMockMvc;

    private Student student;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createEntity(EntityManager em) {
        Student student = new Student()
            .lastName(DEFAULT_LAST_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .arabicLastName(DEFAULT_ARABIC_LAST_NAME)
            .arabicFirstName(DEFAULT_ARABIC_FIRST_NAME)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .classroom(DEFAULT_CLASSROOM)
            .gender(DEFAULT_GENDER)
            .address(DEFAULT_ADDRESS)
            .email(DEFAULT_EMAIL)
            .picture(DEFAULT_PICTURE)
            .schoolOfOrigin(DEFAULT_SCHOOL_OF_ORIGIN)
            .password(DEFAULT_PASSWORD)
            .nationality(DEFAULT_NATIONALITY);
        return student;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createUpdatedEntity(EntityManager em) {
        Student student = new Student()
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .arabicLastName(UPDATED_ARABIC_LAST_NAME)
            .arabicFirstName(UPDATED_ARABIC_FIRST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .classroom(UPDATED_CLASSROOM)
            .gender(UPDATED_GENDER)
            .address(UPDATED_ADDRESS)
            .email(UPDATED_EMAIL)
            .picture(UPDATED_PICTURE)
            .schoolOfOrigin(UPDATED_SCHOOL_OF_ORIGIN)
            .password(UPDATED_PASSWORD)
            .nationality(UPDATED_NATIONALITY);
        return student;
    }

    @BeforeEach
    public void initTest() {
        student = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();
        // Create the Student
        restStudentMockMvc.perform(post("/api/students").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testStudent.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testStudent.getArabicLastName()).isEqualTo(DEFAULT_ARABIC_LAST_NAME);
        assertThat(testStudent.getArabicFirstName()).isEqualTo(DEFAULT_ARABIC_FIRST_NAME);
        assertThat(testStudent.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testStudent.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testStudent.getClassroom()).isEqualTo(DEFAULT_CLASSROOM);
        assertThat(testStudent.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testStudent.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testStudent.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testStudent.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testStudent.getSchoolOfOrigin()).isEqualTo(DEFAULT_SCHOOL_OF_ORIGIN);
        assertThat(testStudent.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testStudent.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
    }

    @Test
    @Transactional
    public void createStudentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // Create the Student with an existing ID
        student.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentMockMvc.perform(post("/api/students").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStudents() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList
        restStudentMockMvc.perform(get("/api/students?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].arabicLastName").value(hasItem(DEFAULT_ARABIC_LAST_NAME)))
            .andExpect(jsonPath("$.[*].arabicFirstName").value(hasItem(DEFAULT_ARABIC_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP.toString())))
            .andExpect(jsonPath("$.[*].classroom").value(hasItem(DEFAULT_CLASSROOM)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE)))
            .andExpect(jsonPath("$.[*].schoolOfOrigin").value(hasItem(DEFAULT_SCHOOL_OF_ORIGIN)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)));
    }
    
    @Test
    @Transactional
    public void getStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get the student
        restStudentMockMvc.perform(get("/api/students/{id}", student.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(student.getId().intValue()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.arabicLastName").value(DEFAULT_ARABIC_LAST_NAME))
            .andExpect(jsonPath("$.arabicFirstName").value(DEFAULT_ARABIC_FIRST_NAME))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP.toString()))
            .andExpect(jsonPath("$.classroom").value(DEFAULT_CLASSROOM))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE))
            .andExpect(jsonPath("$.schoolOfOrigin").value(DEFAULT_SCHOOL_OF_ORIGIN))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY));
    }
    @Test
    @Transactional
    public void getNonExistingStudent() throws Exception {
        // Get the student
        restStudentMockMvc.perform(get("/api/students/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudent() throws Exception {
        // Initialize the database
        studentService.save(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student
        Student updatedStudent = studentRepository.findById(student.getId()).get();
        // Disconnect from session so that the updates on updatedStudent are not directly saved in db
        em.detach(updatedStudent);
        updatedStudent
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .arabicLastName(UPDATED_ARABIC_LAST_NAME)
            .arabicFirstName(UPDATED_ARABIC_FIRST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .classroom(UPDATED_CLASSROOM)
            .gender(UPDATED_GENDER)
            .address(UPDATED_ADDRESS)
            .email(UPDATED_EMAIL)
            .picture(UPDATED_PICTURE)
            .schoolOfOrigin(UPDATED_SCHOOL_OF_ORIGIN)
            .password(UPDATED_PASSWORD)
            .nationality(UPDATED_NATIONALITY);

        restStudentMockMvc.perform(put("/api/students").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStudent)))
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testStudent.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testStudent.getArabicLastName()).isEqualTo(UPDATED_ARABIC_LAST_NAME);
        assertThat(testStudent.getArabicFirstName()).isEqualTo(UPDATED_ARABIC_FIRST_NAME);
        assertThat(testStudent.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testStudent.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testStudent.getClassroom()).isEqualTo(UPDATED_CLASSROOM);
        assertThat(testStudent.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testStudent.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStudent.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testStudent.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testStudent.getSchoolOfOrigin()).isEqualTo(UPDATED_SCHOOL_OF_ORIGIN);
        assertThat(testStudent.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testStudent.getNationality()).isEqualTo(UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    public void updateNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc.perform(put("/api/students").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStudent() throws Exception {
        // Initialize the database
        studentService.save(student);

        int databaseSizeBeforeDelete = studentRepository.findAll().size();

        // Delete the student
        restStudentMockMvc.perform(delete("/api/students/{id}", student.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
