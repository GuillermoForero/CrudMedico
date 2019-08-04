package com.me.crudmedico.ui.patient.contract;

import com.me.crudmedico.model.Patient;

import java.util.List;

public class DetailPatientContract {
    public interface View {

        void confirm(String message);
    }

    public interface Presenter {

        void setView(MainPatientContract.View view);

        public void deletePatient(Patient patient);

        public void editPatient(Patient patient);
    }
}
