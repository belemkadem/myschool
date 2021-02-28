package com.lastgeneration.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

import com.lastgeneration.product.domain.enumeration.Gender;

/**
 * A Tutor.
 */
@Entity
@Table(name = "tutor")
public class Tutor implements Serializable {

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

    @Column(name = "nin")
    private String nin;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "passowrd")
    private String passowrd;

    @ManyToOne
    @JsonIgnoreProperties(value = "tutors", allowSetters = true)
    private TutorType tutorType;

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

    public Tutor lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Tutor firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getArabicLastName() {
        return arabicLastName;
    }

    public Tutor arabicLastName(String arabicLastName) {
        this.arabicLastName = arabicLastName;
        return this;
    }

    public void setArabicLastName(String arabicLastName) {
        this.arabicLastName = arabicLastName;
    }

    public String getArabicFirstName() {
        return arabicFirstName;
    }

    public Tutor arabicFirstName(String arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
        return this;
    }

    public void setArabicFirstName(String arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
    }

    public String getNin() {
        return nin;
    }

    public Tutor nin(String nin) {
        this.nin = nin;
        return this;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public Gender getGender() {
        return gender;
    }

    public Tutor gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public Tutor address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Tutor phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Tutor email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public Tutor passowrd(String passowrd) {
        this.passowrd = passowrd;
        return this;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public TutorType getTutorType() {
        return tutorType;
    }

    public Tutor tutorType(TutorType tutorType) {
        this.tutorType = tutorType;
        return this;
    }

    public void setTutorType(TutorType tutorType) {
        this.tutorType = tutorType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tutor)) {
            return false;
        }
        return id != null && id.equals(((Tutor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tutor{" +
            "id=" + getId() +
            ", lastName='" + getLastName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", arabicLastName='" + getArabicLastName() + "'" +
            ", arabicFirstName='" + getArabicFirstName() + "'" +
            ", nin='" + getNin() + "'" +
            ", gender='" + getGender() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", passowrd='" + getPassowrd() + "'" +
            "}";
    }
}
