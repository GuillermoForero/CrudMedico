package com.me.crudmedico.ui.patient.contract;

import com.me.crudmedico.model.Patient;

import java.util.List;

public class MainPatientContract {
    public interface View {
        public void setPatients(List<Patient> patients);

        void confirm(String message);
    }

    public interface Presenter {
        public void getPatients();

        void setView(View view);
    }
}
