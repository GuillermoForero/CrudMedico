package com.me.crudmedico.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Patient implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("birthdate")
    @Expose
    private Date birthdate;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("doctor")
    @Expose
    private Doctor doctor;
    @SerializedName("treatment")
    @Expose
    private Boolean treatment;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("otherDate")
    @Expose
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

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", id='" + id + '\'' +
                ", doctor=" + doctor +
                ", treatment=" + treatment +
                ", value=" + value +
                ", otherDate=" + otherDate +
                '}';
    }
}
