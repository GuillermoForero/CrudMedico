package com.me.crudmedico.ui.doctor.contract;

import com.me.crudmedico.model.Patient;

import java.util.List;

public class MainDoctorContract {
    public interface View {
        public void setDoctor(List<Patient> patients);

        void confirm(String message);
    }

    public interface Presenter {
        public void getDoctor();

        void setView(MainDoctorContract.View view);
    }
}
