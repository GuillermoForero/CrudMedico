package com.me.crudmedico.ui.doctor.contract;

import com.me.crudmedico.model.Doctor;

import java.util.List;

public class CreateDoctorContract {
    public interface View {
        public void setDoctor(List<Doctor> doctors);

        void confirm(String message);
    }

    public interface Presenter {
        public void getDoctor();

        void setView(MainDoctorContract.View view);

        public void createDoctor(Doctor doctor);
    }
}
