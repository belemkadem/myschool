package com.lastgeneration.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.lastgeneration.product.domain.enumeration.BloodGroup;

import com.lastgeneration.product.domain.enumeration.Gender;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "arabic_last_name")
    private String arabicLastName;

    @Column(name = "arabic_first_name")
    private String arabicFirstName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private BloodGroup bloodGroup;

    @Column(name = "classroom")
    private String classroom;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "picture")
    private String picture;

    @Column(name = "school_of_origin")
    private String schoolOfOrigin;

    @Column(name = "password")
    private String password;

    @Column(name = "nationality")
    private String nationality;

    @ManyToOne
    @JsonIgnoreProperties(value = "students", allowSetters = true)
    private Tutor tutor1;

    @ManyToOne
    @JsonIgnoreProperties(value = "students", allowSetters = true)
    private Tutor tutor2;

    @ManyToOne
    @JsonIgnoreProperties(value = "students", allowSetters = true)
    private GradeClass gradeClass;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public Student lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Student firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getArabicLastName() {
        return arabicLastName;
    }

    public Student arabicLastName(String arabicLastName) {
        this.arabicLastName = arabicLastName;
        return this;
    }

    public void setArabicLastName(String arabicLastName) {
        this.arabicLastName = arabicLastName;
    }

    public String getArabicFirstName() {
        return arabicFirstName;
    }

    public Student arabicFirstName(String arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
        return this;
    }

    public void setArabicFirstName(String arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Student dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public Student bloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getClassroom() {
        return classroom;
    }

    public Student classroom(String classroom) {
        this.classroom = classroom;
        return this;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Gender getGender() {
        return gender;
    }

    public Student gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public Student address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public Student email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public Student picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSchoolOfOrigin() {
        return schoolOfOrigin;
    }

    public Student schoolOfOrigin(String schoolOfOrigin) {
        this.schoolOfOrigin = schoolOfOrigin;
        return this;
    }

    public void setSchoolOfOrigin(String schoolOfOrigin) {
        this.schoolOfOrigin = schoolOfOrigin;
    }

    public String getPassword() {
        return password;
    }

    public Student password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationality() {
        return nationality;
    }

    public Student nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Tutor getTutor1() {
        return tutor1;
    }

    public Student tutor1(Tutor tutor) {
        this.tutor1 = tutor;
        return this;
    }

    public void setTutor1(Tutor tutor) {
        this.tutor1 = tutor;
    }

    public Tutor getTutor2() {
        return tutor2;
    }

    public Student tutor2(Tutor tutor) {
        this.tutor2 = tutor;
        return this;
    }

    public void setTutor2(Tutor tutor) {
        this.tutor2 = tutor;
    }

    public GradeClass getGradeClass() {
        return gradeClass;
    }

    public Student gradeClass(GradeClass gradeClass) {
        this.gradeClass = gradeClass;
        return this;
    }

    public void setGradeClass(GradeClass gradeClass) {
        this.gradeClass = gradeClass;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        return id != null && id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", lastName='" + getLastName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", arabicLastName='" + getArabicLastName() + "'" +
            ", arabicFirstName='" + getArabicFirstName() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", classroom='" + getClassroom() + "'" +
            ", gender='" + getGender() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", picture='" + getPicture() + "'" +
            ", schoolOfOrigin='" + getSchoolOfOrigin() + "'" +
            ", password='" + getPassword() + "'" +
            ", nationality='" + getNationality() + "'" +
            "}";
    }
}
