package com.lastgeneration.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.lastgeneration.product.domain.enumeration.DayOfWeek;

/**
 * A GeneralTimeTableElement.
 */
@Entity
@Table(name = "general_time_table_element")
public class GeneralTimeTableElement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Min(value = 0)
    @Max(value = 23)
    @Column(name = "houre_from")
    private Integer houreFrom;

    @Min(value = 0)
    @Max(value = 59)
    @Column(name = "minute_from")
    private Integer minuteFrom;

    @Min(value = 0)
    @Max(value = 23)
    @Column(name = "houre_to")
    private Integer houreTo;

    @Min(value = 0)
    @Max(value = 59)
    @Column(name = "minute_to")
    private Integer minuteTo;

    @ManyToOne
    @JsonIgnoreProperties(value = "generalTimeTableElements", allowSetters = true)
    private Subject subject;

    @ManyToOne
    @JsonIgnoreProperties(value = "elements", allowSetters = true)
    private GeneralTimeTable generalTimeTable;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public GeneralTimeTableElement dayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getHoureFrom() {
        return houreFrom;
    }

    public GeneralTimeTableElement houreFrom(Integer houreFrom) {
        this.houreFrom = houreFrom;
        return this;
    }

    public void setHoureFrom(Integer houreFrom) {
        this.houreFrom = houreFrom;
    }

    public Integer getMinuteFrom() {
        return minuteFrom;
    }

    public GeneralTimeTableElement minuteFrom(Integer minuteFrom) {
        this.minuteFrom = minuteFrom;
        return this;
    }

    public void setMinuteFrom(Integer minuteFrom) {
        this.minuteFrom = minuteFrom;
    }

    public Integer getHoureTo() {
        return houreTo;
    }

    public GeneralTimeTableElement houreTo(Integer houreTo) {
        this.houreTo = houreTo;
        return this;
    }

    public void setHoureTo(Integer houreTo) {
        this.houreTo = houreTo;
    }

    public Integer getMinuteTo() {
        return minuteTo;
    }

    public GeneralTimeTableElement minuteTo(Integer minuteTo) {
        this.minuteTo = minuteTo;
        return this;
    }

    public void setMinuteTo(Integer minuteTo) {
        this.minuteTo = minuteTo;
    }

    public Subject getSubject() {
        return subject;
    }

    public GeneralTimeTableElement subject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public GeneralTimeTable getGeneralTimeTable() {
        return generalTimeTable;
    }

    public GeneralTimeTableElement generalTimeTable(GeneralTimeTable generalTimeTable) {
        this.generalTimeTable = generalTimeTable;
        return this;
    }

    public void setGeneralTimeTable(GeneralTimeTable generalTimeTable) {
        this.generalTimeTable = generalTimeTable;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeneralTimeTableElement)) {
            return false;
        }
        return id != null && id.equals(((GeneralTimeTableElement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeneralTimeTableElement{" +
            "id=" + getId() +
            ", dayOfWeek='" + getDayOfWeek() + "'" +
            ", houreFrom=" + getHoureFrom() +
            ", minuteFrom=" + getMinuteFrom() +
            ", houreTo=" + getHoureTo() +
            ", minuteTo=" + getMinuteTo() +
            "}";
    }
}
