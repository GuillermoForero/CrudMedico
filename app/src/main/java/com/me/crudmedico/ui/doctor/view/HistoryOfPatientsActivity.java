package com.me.crudmedico.ui.doctor.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Patient;
import com.me.crudmedico.ui.patient.contract.MainPatientContract;

import java.util.List;

import butterknife.ButterKnife;

public class HistoryOfPatientsActivity extends AppCompatActivity implements MainPatientContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_of_patients);
        ButterKnife.bind(this);
    }

    @Override
    public void setPatients(List<Patient> patients) {

    }

    @Override
    public void confirm(String message) {

    }
}
