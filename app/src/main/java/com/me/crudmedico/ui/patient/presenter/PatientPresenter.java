package com.me.crudmedico.ui.patient.presenter;

import com.me.crudmedico.model.Patient;
import com.me.crudmedico.ui.patient.contract.PatientContract;

import java.lang.ref.WeakReference;

public class PatientPresenter  implements PatientContract.Presenter {

    private WeakReference<PatientContract.View> view;

    @Override
    public void getPatients() {

    }

    @Override
    public void createPatient(Patient patient) {

    }

    @Override
    public void setView(PatientContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void deletePatient(Patient patient) {

    }

    @Override
    public void editPatient(Patient patient) {

    }
}
