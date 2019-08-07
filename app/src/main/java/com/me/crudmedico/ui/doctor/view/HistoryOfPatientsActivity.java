package com.me.crudmedico.ui.doctor.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.me.crudmedico.R;
import com.me.crudmedico.model.MedicalAppointment;
import com.me.crudmedico.model.Patient;
import com.me.crudmedico.model.adapters.AdapterHistory;
import com.me.crudmedico.model.adapters.AdaptertextNameDoctors;
import com.me.crudmedico.ui.doctor.contract.WatchHistoryDoctorContract;
import com.me.crudmedico.ui.doctor.presenter.WatchHistoryDoctorPresenter;
import com.me.crudmedico.ui.patient.contract.MainPatientContract;
import com.me.crudmedico.ui.patient.presenter.WatchHistoryPatientPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryOfPatientsActivity extends AppCompatActivity implements WatchHistoryDoctorContract.View{

    @BindView(R.id.recyclerView_history_doctor)
    RecyclerView recyclerViewDoctor;
    AdapterHistory adapterHistory;
    WatchHistoryDoctorContract.Presenter presenter;
    String codeDoctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_of_patients);
        ButterKnife.bind(this);
        codeDoctor = getIntent().getStringExtra("doctor");
        initView();
    }

    private void initView(){
        presenter = new WatchHistoryDoctorPresenter();
        presenter.setView(this);
        presenter.setContext(this);
        presenter.getMedicalAppointment(codeDoctor);
    }

    @Override
    public void setMedicalAppointment(List<MedicalAppointment> medicalAppointments) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewDoctor.setLayoutManager(linearLayoutManager);
        adapterHistory = new AdapterHistory();
        adapterHistory.setMedicalAppointments(medicalAppointments);
        recyclerViewDoctor.setAdapter(adapterHistory);
    }


    @Override
    public void confirm(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
