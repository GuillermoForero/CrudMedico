package com.me.crudmedico.ui.patient.contract;

import com.me.crudmedico.model.Patient;

import java.util.List;

public class PatientContract {
    public interface View {
        public void setPatients(List<Patient> patients);

        void confirm(String message);
    }

    public interface Presenter {
        public void getPatients();

        public void createPatient(Patient patient);

        void setView(View view);

        public void deletePatient(Patient patient);

        public void editPatient(Patient patient);
    }
}
