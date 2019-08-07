package com.me.crudmedico.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.me.crudmedico.R;
import com.me.crudmedico.ui.doctor.view.DoctorActivity;
import com.me.crudmedico.ui.patient.view.PatientActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button patientsButton;
    @BindView(R.id.button2)
    Button doctorsButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button2)
    public void goToDoctors(){
        startActivity(new Intent(this, DoctorActivity.class));
    }

    @OnClick(R.id.button)
    public void goToPatients(){
        startActivity(new Intent(this, PatientActivity.class));
    }

}
