package com.me.crudmedico.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MedicalAppointment {
    @SerializedName("doctorCode")
    @Expose
    private String doctorCode;
    @SerializedName("patientId")
    @Expose
    private String patientId;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("attended")
    @Expose
    private boolean attended;
    @SerializedName("imageFirmSVG")
    @Expose
    private String imageFirmSVG;

    public String getImageFirmSVG() {
        return imageFirmSVG;
    }

    public void setImageFirmSVG(String imageFirmSVG) {
        this.imageFirmSVG = imageFirmSVG;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    @Override
    public String toString() {
        return "MedicalAppointment{" +
                "doctorCode='" + doctorCode + '\'' +
                ", patientId='" + patientId + '\'' +
                ", date=" + date +
                ", attended=" + attended +
                ", imageFirmSVG='" + imageFirmSVG + '\'' +
                '}';
    }
}
