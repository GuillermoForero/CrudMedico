package com.me.crudmedico.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Doctor implements Serializable {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("speciality")
    @Expose
    private String speciality;
    @SerializedName("yearsOfExperiences")
    @Expose
    private Float yearsOfExperiences;
    @SerializedName("consultingRoom")
    @Expose
    private String consultingRoom;
    @SerializedName("home")
    @Expose
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

    @Override
    public String toString() {
        return "Doctor{" +
                "code='" + code + '\'' +
                ", speciality='" + speciality + '\'' +
                ", yearsOfExperiences=" + yearsOfExperiences +
                ", consultingRoom='" + consultingRoom + '\'' +
                ", home=" + home +
                '}';
    }
}
