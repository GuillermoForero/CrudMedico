package com.me.crudmedico.ui.doctor.contract;

import com.me.crudmedico.model.Doctor;

public class DetailDoctorContract {
    public interface View {

        void confirm(String message);
    }

    public interface Presenter {

        void setView(MainDoctorContract.View view);

        public void deletePatient(Doctor doctor);

        public void editDoctor(Doctor doctor);
    }
}
