package com.me.crudmedico.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.me.crudmedico.R;
import com.me.crudmedico.data.remote.Client;
import com.me.crudmedico.ui.doctor.view.DoctorActivity;
import com.me.crudmedico.ui.patient.view.PatientActivity;

import java.util.Timer;

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
        TimerSyncTask task = new TimerSyncTask(this);
        Timer timer = new Timer();
        timer.schedule(task, 1000, 10000);
    }

    @OnClick(R.id.button2)
    public void goToDoctors() {
        startActivity(new Intent(this, DoctorActivity.class));
    }

    @OnClick(R.id.button)
    public void goToPatients() {
        startActivity(new Intent(this, PatientActivity.class));
    }

    public void showResponse(String response) {

    }

    class TimerSyncTask extends java.util.TimerTask {

        Client task;
        Context context;

        public TimerSyncTask(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            task = new Client(context);
            task.execute();
        }
    }

}
