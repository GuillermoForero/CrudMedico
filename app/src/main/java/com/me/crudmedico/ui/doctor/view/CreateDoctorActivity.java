package com.me.crudmedico.ui.doctor.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.ui.doctor.contract.CreateDoctorContract;
import com.me.crudmedico.ui.doctor.presenter.CreateDoctorPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateDoctorActivity extends AppCompatActivity implements CreateDoctorContract.View {

    @BindView(R.id.code)
    EditText codeEditText;
    @BindView(R.id.especialty)
    EditText especialtyEditText;
    @BindView(R.id.years)
    EditText yearsEditText;
    @BindView(R.id.consulting_room)
    EditText consultingRoomEditText;
    @BindView(R.id.checkBox_yes)
    CheckBox checkBoxYes;

    CreateDoctorContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        presenter = new CreateDoctorPresenter();
        presenter.setView(this);
        presenter.setContext(this);
    }

    @OnClick(R.id.btn_sign_up)
    public void createDoctor(){
        Doctor doctor = new Doctor();
        doctor.setCode(codeEditText.getText().toString());
        doctor.setSpeciality(especialtyEditText.getText().toString());
        doctor.setConsultingRoom(consultingRoomEditText.getText().toString());
        doctor.setYearsOfExperiences(Float.valueOf(yearsEditText.getText().toString()));
        doctor.setHome(checkBoxYes.isChecked());
        presenter.createDoctor(doctor);
    }


    @Override
    public void confirm(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
