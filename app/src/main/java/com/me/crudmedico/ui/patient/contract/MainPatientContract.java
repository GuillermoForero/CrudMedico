package com.me.crudmedico.ui.patient.contract;

import android.content.Context;

import com.me.crudmedico.model.Patient;

import java.util.List;

public class MainPatientContract {
    public interface View {
        void setPatients(List<Patient> patients);

        void confirm(String message);
    }

    public interface Presenter {

        void setContext(Context context);

        void getPatients();

        void setView(MainPatientContract.View view);
    }
}
