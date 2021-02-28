package com.lastgeneration.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeneralTimeTable.
 */
@Entity
@Table(name = "general_time_table")
public class GeneralTimeTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "generalTimeTable")
    private Set<GeneralTimeTableElement> elements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "generalTimeTables", allowSetters = true)
    private GradeClass gradeClass;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<GeneralTimeTableElement> getElements() {
        return elements;
    }

    public GeneralTimeTable elements(Set<GeneralTimeTableElement> generalTimeTableElements) {
        this.elements = generalTimeTableElements;
        return this;
    }

    public GeneralTimeTable addElements(GeneralTimeTableElement generalTimeTableElement) {
        this.elements.add(generalTimeTableElement);
        generalTimeTableElement.setGeneralTimeTable(this);
        return this;
    }

    public GeneralTimeTable removeElements(GeneralTimeTableElement generalTimeTableElement) {
        this.elements.remove(generalTimeTableElement);
        generalTimeTableElement.setGeneralTimeTable(null);
        return this;
    }

    public void setElements(Set<GeneralTimeTableElement> generalTimeTableElements) {
        this.elements = generalTimeTableElements;
    }

    public GradeClass getGradeClass() {
        return gradeClass;
    }

    public GeneralTimeTable gradeClass(GradeClass gradeClass) {
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
        if (!(o instanceof GeneralTimeTable)) {
            return false;
        }
        return id != null && id.equals(((GeneralTimeTable) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeneralTimeTable{" +
            "id=" + getId() +
            "}";
    }
}
