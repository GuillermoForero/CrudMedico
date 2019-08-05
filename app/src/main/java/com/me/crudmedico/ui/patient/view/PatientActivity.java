package com.me.crudmedico.ui.patient.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.me.crudmedico.model.Patient;
import com.me.crudmedico.model.adapters.AdapterTextNamePatient;
import com.me.crudmedico.model.adapters.AdaptertextNameDoctors;
import com.me.crudmedico.ui.patient.contract.MainPatientContract;
import com.me.crudmedico.R;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PatientActivity extends AppCompatActivity implements  MainPatientContract.View {

    @BindView(R.id.toolbar_activity_patient)
    Toolbar toolbarPatient;
    @BindView(R.id.fab_activity_patient)
    FloatingActionButton floatingActionButtonPatient;
    @BindView(R.id.recyclerView_activity_patient)
    RecyclerView recyclerViewPatient;

    MainPatientContract.Presenter presenter;
    AdapterTextNamePatient adapterTextNamePatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        ButterKnife.bind(this);
        toolbarPatient.setTitle(R.string.doctors);
        setSupportActionBar(toolbarPatient);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }




    @OnClick(R.id.fab_activity_patient)
    public void createPatient(){
        startActivity(new Intent(this, CreatePatientActivity.class));
    }


    @Override
    public void setPatients(List<Patient> patients) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewPatient.setLayoutManager(linearLayoutManager);
        adapterTextNamePatient = new AdapterTextNamePatient(patients);
        recyclerViewPatient.setAdapter(adapterTextNamePatient);
    }

    @Override
    public void confirm(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
