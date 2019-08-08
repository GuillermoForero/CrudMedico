package com.me.crudmedico.ui.doctor.contract;

import android.content.Context;

import com.me.crudmedico.model.Doctor;


public class CreateDoctorContract {
    public interface View {
        void confirm(String message);
    }

    public interface Presenter {
        public void getDoctor();

        void setView(CreateDoctorContract.View view);

        void setContext(Context context);

        public void createDoctor(Doctor doctor);
    }
}
