package com.me.crudmedico.model;

import java.util.Date;

public class Patient {
    private String name;
    private String lastName;
    private Date birthdate;
    private String id;
    private Doctor doctor;
    private Boolean treatment;
    private Double value;
    private Date otherDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Boolean getTreatment() {
        return treatment;
    }

    public void setTreatment(Boolean treatment) {
        this.treatment = treatment;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getOtherDate() {
        return otherDate;
    }

    public void setOtherDate(Date otherDate) {
        this.otherDate = otherDate;
    }
}
