package com.me.crudmedico.ui.patient.contract;

import android.content.Context;

import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.model.MedicalAppointment;
import com.me.crudmedico.model.Patient;

import java.util.List;

public class DetailPatientContract {
    public interface View {

        void confirm(String message);

        void setDoctors(List<Doctor> doctors);
    }

    public interface Presenter {

        void setContext(Context context);

        void setView(DetailPatientContract.View view);

        public void deletePatient(Patient patient);

        public void editPatient(Patient patient);

        public void createMedicalAppointment(MedicalAppointment medicalAppointment);

        public void getDoctors();
    }
}
