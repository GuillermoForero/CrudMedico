package com.me.crudmedico.model;

import java.util.Date;

public class MedicalAppointment {
    private String doctorCode;
    private String patientId;
    private Date date;
    private boolean attended;
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
}
