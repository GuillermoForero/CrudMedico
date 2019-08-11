package com.me.crudmedico.ui.doctor.contract;

import android.content.Context;

import com.me.crudmedico.model.MedicalAppointment;

import java.util.List;

public class WatchHistoryDoctorContract {
    public interface View {
        void setMedicalAppointment(List<MedicalAppointment> medicalAppointments);

        void confirm(String message);
    }

    public interface Presenter {
        void getMedicalAppointment(String doctor);

        void setContext(Context context);

        void setView(WatchHistoryDoctorContract.View view);

    }
}
