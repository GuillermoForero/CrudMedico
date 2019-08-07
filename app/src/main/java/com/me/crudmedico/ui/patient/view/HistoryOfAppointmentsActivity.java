package com.me.crudmedico.ui.patient.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.me.crudmedico.R;
import com.me.crudmedico.model.MedicalAppointment;
import com.me.crudmedico.model.adapters.AdapterHistory;
import com.me.crudmedico.ui.patient.contract.WatchHistoryPatientContract;
import com.me.crudmedico.ui.patient.presenter.MainPatientPresenter;
import com.me.crudmedico.ui.patient.presenter.WatchHistoryPatientPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryOfAppointmentsActivity extends AppCompatActivity implements WatchHistoryPatientContract.View, AdapterHistory.LaunchFirmActivity {

    @BindView(R.id.recyclerView_history_patient)
    RecyclerView recyclerViewPatient;
    private AdapterHistory adapterHistory;
    WatchHistoryPatientContract.Presenter presenter;
    String idPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_of_appointments);
        ButterKnife.bind(this);
        idPatient = getIntent().getStringExtra("patient");
        initView();
    }

    private void initView(){
        presenter = new WatchHistoryPatientPresenter();
        presenter.setView(this);
        presenter.setContext(this);
        presenter.getMedicalAppointment(idPatient);
    }


    @Override
    public void setMedicalAppointment(List<MedicalAppointment> medicalAppointments) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewPatient.setLayoutManager(linearLayoutManager);
        adapterHistory = new AdapterHistory();
        adapterHistory.setMedicalAppointments(medicalAppointments);
        recyclerViewPatient.setAdapter(adapterHistory);
        adapterHistory.setLaunchFirmActivity(this);
    }

    @Override
    public void confirm(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void launchFirmActivity() {
        startActivity(new Intent(this, FirmActivity.class));
    }
}
