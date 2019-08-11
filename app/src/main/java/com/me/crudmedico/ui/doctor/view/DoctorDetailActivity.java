package com.me.crudmedico.ui.doctor.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.ui.doctor.contract.DetailDoctorContract;
import com.me.crudmedico.ui.doctor.presenter.DetailDoctorPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.me.crudmedico.utils.Util.edittextNotEmpty;
import static com.me.crudmedico.utils.Util.textviewNotEmpty;

public class DoctorDetailActivity extends AppCompatActivity implements DetailDoctorContract.View {

    @BindView(R.id.code)
    TextView codeTextView;
    @BindView(R.id.especialty)
    EditText especialtyEditText;
    @BindView(R.id.years)
    EditText yearsEditText;
    @BindView(R.id.consulting_room)
    EditText consultingRoomEditText;
    @BindView(R.id.checkBox_yes)
    CheckBox checkBoxYes;
    @BindView(R.id.checkBox_no)
    CheckBox checkBoxNo;

    DetailDoctorContract.Presenter presenter;
    Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        ButterKnife.bind(this);
        this.doctor = (Doctor) getIntent().getSerializableExtra("doctor");
        initView();
    }

    public void initView() {
        codeTextView.setText(doctor.getCode());
        especialtyEditText.setText(doctor.getSpeciality());
        consultingRoomEditText.setText(doctor.getConsultingRoom());
        yearsEditText.setText(String.valueOf(doctor.getYearsOfExperiences()));
        if ((doctor.getHome())) {
            checkBoxYes.setChecked(true);
        } else {
            checkBoxNo.setChecked(true);
        }
        presenter = new DetailDoctorPresenter();
        presenter.setView(this);
        presenter.setContext(this);
    }

    @OnClick(R.id.change_data)
    public void changeData() {
        Doctor doctor = new Doctor();
        if (textviewNotEmpty(codeTextView) && edittextNotEmpty(especialtyEditText) && edittextNotEmpty(consultingRoomEditText) && edittextNotEmpty(yearsEditText)) {
            doctor.setCode(codeTextView.getText().toString());
            doctor.setSpeciality(especialtyEditText.getText().toString());
            doctor.setConsultingRoom(consultingRoomEditText.getText().toString());
            doctor.setYearsOfExperiences(Float.valueOf(yearsEditText.getText().toString()));
            doctor.setHome(checkBoxYes.isChecked());
            presenter.editDoctor(doctor);
        } else {
            confirm("Todos los campos deben estar llenos");
        }
    }

    @OnClick(R.id.watch_patients)
    public void watchPatients() {
        startActivity(new Intent(this, HistoryOfPatientsActivity.class).putExtra("doctor", doctor.getCode()));
    }

    @OnClick(R.id.btn_delete)
    public void deleteDoctor() {
        Doctor doctor = new Doctor();
        doctor.setCode(codeTextView.getText().toString());
        doctor.setSpeciality(especialtyEditText.getText().toString());
        doctor.setConsultingRoom(consultingRoomEditText.getText().toString());
        doctor.setYearsOfExperiences(Float.valueOf(yearsEditText.getText().toString()));
        doctor.setHome(checkBoxYes.isChecked());
        presenter.deleteDoctor(doctor);
    }

    @OnClick(R.id.btn_delete_history)
    public void deleteHistory() {
        presenter.deleteHistory(doctor);
    }

    @Override
    public void confirm(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
