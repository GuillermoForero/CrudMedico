package com.me.crudmedico.model;

import java.io.Serializable;

public class Doctor implements Serializable {
    private String code;
    private String speciality;
    private Float yearsOfExperiences;
    private String consultingRoom;
    private Boolean home;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Float getYearsOfExperiences() {
        return yearsOfExperiences;
    }

    public void setYearsOfExperiences(Float yearsOfExperiences) {
        this.yearsOfExperiences = yearsOfExperiences;
    }

    public String getConsultingRoom() {
        return consultingRoom;
    }

    public void setConsultingRoom(String consultingRoom) {
        this.consultingRoom = consultingRoom;
    }

    public Boolean getHome() {
        return home;
    }

    public void setHome(Boolean home) {
        this.home = home;
    }
}
