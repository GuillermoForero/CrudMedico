package com.me.crudmedico.ui.doctor.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.me.crudmedico.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoctorActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_activity_doctor)
    Toolbar toolbarPatient;
    @BindView(R.id.fab_activity_doctor)
    FloatingActionButton floatingActionButtonDoctor;
    @BindView(R.id.recyclerView_activity_doctor)
    RecyclerView recyclerViewDoctor;


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
}
