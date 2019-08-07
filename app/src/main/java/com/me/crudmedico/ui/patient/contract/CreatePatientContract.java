package com.me.crudmedico.ui.patient.contract;

import android.content.Context;

import com.me.crudmedico.model.Patient;

import java.util.List;

public class CreatePatientContract {
    public interface View {

        void confirm(String message);
    }

    public interface Presenter {

        void setView(CreatePatientContract.View view);

        void setContext(Context context);

        public void createPatient(Patient patient);
    }
}
