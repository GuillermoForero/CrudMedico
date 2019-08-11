package com.me.crudmedico.ui.doctor.contract;

import android.content.Context;

import com.me.crudmedico.model.Doctor;

public class DetailDoctorContract {
    public interface View {

        void confirm(String message);
    }

    public interface Presenter {

        void setView(DetailDoctorContract.View view);

        void deleteDoctor(Doctor doctor);

        void setContext(Context context);

        void editDoctor(Doctor doctor);

        void deleteHistory(Doctor doctor);
    }
}
