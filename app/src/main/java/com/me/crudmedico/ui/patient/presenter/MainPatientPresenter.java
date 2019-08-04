package com.me.crudmedico.ui.patient.presenter;

import com.me.crudmedico.model.Patient;
import com.me.crudmedico.ui.patient.contract.MainPatientContract;

import java.lang.ref.WeakReference;

public class MainPatientPresenter implements MainPatientContract.Presenter {

    private WeakReference<MainPatientContract.View> view;

    @Override
    public void getPatients() {

    }

    @Override
    public void createPatient(Patient patient) {

    }

    @Override
    public void setView(MainPatientContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void deletePatient(Patient patient) {

    }

    @Override
    public void editPatient(Patient patient) {

    }
}
