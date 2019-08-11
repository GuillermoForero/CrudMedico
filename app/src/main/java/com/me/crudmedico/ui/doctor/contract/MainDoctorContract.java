package com.me.crudmedico.ui.doctor.contract;

import android.content.Context;

import com.me.crudmedico.model.Doctor;

import java.util.List;

public class MainDoctorContract {
    public interface View {
        void setDoctor(List<Doctor> doctors);

        void confirm(String message);
    }

    public interface Presenter {
        void getDoctor();

        void setContext(Context context);

        void setView(MainDoctorContract.View view);
    }
}
