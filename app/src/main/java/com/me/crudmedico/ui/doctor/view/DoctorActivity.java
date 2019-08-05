package com.me.crudmedico.ui.doctor.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.model.Patient;
import com.me.crudmedico.model.adapters.AdaptertextNameDoctors;
import com.me.crudmedico.ui.doctor.contract.MainDoctorContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DoctorActivity extends AppCompatActivity implements MainDoctorContract.View {

    @BindView(R.id.toolbar_activity_doctor)
    Toolbar toolbarPatient;
    @BindView(R.id.fab_activity_doctor)
    FloatingActionButton floatingActionButtonDoctor;
    @BindView(R.id.recyclerView_activity_doctor)
    RecyclerView recyclerViewDoctor;


    AdaptertextNameDoctors adaptertextNameDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        ButterKnife.bind(this);
        toolbarPatient.setTitle(R.string.doctors);
        setSupportActionBar(toolbarPatient);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @OnClick(R.id.fab_activity_doctor)
    public void createDoctor(){
        startActivity(new Intent(this, CreateDoctorActivity.class));
    }

    @Override
    public void setDoctor(List<Doctor> doctors) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewDoctor.setLayoutManager(linearLayoutManager);
        adaptertextNameDoctors = new AdaptertextNameDoctors(doctors);
        recyclerViewDoctor.setAdapter(adaptertextNameDoctors);
    }

    @Override
    public void confirm(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
