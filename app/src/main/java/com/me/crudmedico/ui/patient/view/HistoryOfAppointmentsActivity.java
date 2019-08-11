package com.me.crudmedico.ui.patient.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.me.crudmedico.R;
import com.me.crudmedico.model.MedicalAppointment;
import com.me.crudmedico.model.adapters.AdapterHistory;
import com.me.crudmedico.ui.patient.contract.WatchHistoryPatientContract;
import com.me.crudmedico.ui.patient.presenter.WatchHistoryPatientPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryOfAppointmentsActivity extends AppCompatActivity implements WatchHistoryPatientContract.View, AdapterHistory.LaunchFirmActivity {

    @BindView(R.id.recyclerView_history_patient)
    RecyclerView recyclerViewPatient;
    WatchHistoryPatientContract.Presenter presenter;
    String idPatient;
    MedicalAppointment medicalAppointment;
    private AdapterHistory adapterHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_of_appointments);
        ButterKnife.bind(this);
        idPatient = getIntent().getStringExtra("patient");
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getMedicalAppointment(idPatient);
    }

    private void initView() {
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

    public void changeState(String imageSVG) {
        medicalAppointment.setImageFirmSVG(imageSVG);
        presenter.setAttended(medicalAppointment);
    }

    @Override
    public void confirm(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void launchFirmActivity(MedicalAppointment medicalAppointment) {
        startActivityForResult(new Intent(this, FirmActivity.class), 666);
        this.medicalAppointment = medicalAppointment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666) {
            if (resultCode == RESULT_OK) {
                String returnedResult = data.getData().toString();
                changeState(returnedResult);
            }
        }
    }
}
