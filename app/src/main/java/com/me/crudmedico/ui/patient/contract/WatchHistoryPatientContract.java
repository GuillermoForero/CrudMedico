package com.me.crudmedico.ui.patient.contract;

import android.content.Context;

import com.me.crudmedico.model.MedicalAppointment;

import java.util.List;

public class WatchHistoryPatientContract {
    public interface View {
        public void setMedicalAppointment(List<MedicalAppointment> medicalAppointments);

        void confirm(String message);
    }

    public interface Presenter {
        public void getMedicalAppointment(String patient);

        void setContext(Context context);

        void setView(WatchHistoryPatientContract.View view);

        void setAttended(MedicalAppointment medicalAppointment);
    }
}
