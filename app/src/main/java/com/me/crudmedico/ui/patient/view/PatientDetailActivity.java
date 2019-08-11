package com.me.crudmedico.ui.patient.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.model.MedicalAppointment;
import com.me.crudmedico.model.Patient;
import com.me.crudmedico.ui.patient.contract.DetailPatientContract;
import com.me.crudmedico.ui.patient.presenter.DetailPatientPresenter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.me.crudmedico.utils.Util.edittextNotEmpty;
import static com.me.crudmedico.utils.Util.textviewNotEmpty;

public class PatientDetailActivity extends AppCompatActivity implements DialogFragmentMedicalAppointment.createMedicalAppointment, DetailPatientContract.View {


    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);
    @BindView(R.id.birthday_textview_detail)
    TextView textViewBirthDay;
    @BindView(R.id.name_edit_text)
    EditText nameEditText;
    @BindView(R.id.lastname_edit_text)
    EditText lastnameEditText;
    @BindView(R.id.id_text_view)
    TextView idTextView;
    @BindView(R.id.checkBox_yes)
    CheckBox checkBoxYes;
    @BindView(R.id.checkBox_no)
    CheckBox checkBoxNo;
    @BindView(R.id.modering_fee_edit_text)
    EditText moderingFeeEditText;
    Patient patient;
    Date birthday;
    Date newAppointment;
    DialogFragmentMedicalAppointment dialog;
    DetailPatientContract.Presenter presenter;
    private List<Doctor> doctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        ButterKnife.bind(this);
        patient = (Patient) getIntent().getSerializableExtra("patient");

        initView();
    }

    public void initView() {
        idTextView.setText(patient.getId());
        nameEditText.setText(patient.getName());
        lastnameEditText.setText(patient.getLastName());
        textViewBirthDay.setText(patient.getBirthdate().toString());
        moderingFeeEditText.setText(patient.getValue().toString());
        birthday = patient.getBirthdate();
        if (patient.getTreatment()) {
            checkBoxYes.setChecked(true);
        } else {
            checkBoxNo.setChecked(true);
        }

        presenter = new DetailPatientPresenter();
        presenter.setView(this);
        presenter.setContext(this);
        presenter.getDoctors();
    }


    @OnClick(R.id.btn_sign_up)
    public void createPatient() {
        if (edittextNotEmpty(nameEditText) && edittextNotEmpty(lastnameEditText) && textviewNotEmpty(idTextView) && textviewNotEmpty(textViewBirthDay)) {
            patient.setName(nameEditText.getText().toString());
            patient.setLastName(lastnameEditText.getText().toString());
            patient.setId(idTextView.getText().toString());
            patient.setTreatment(checkBoxYes.isChecked());
            patient.setBirthdate(birthday);
            presenter.editPatient(patient);
        } else {
            confirm("Todos los campos deben estar llenos");
        }
    }

    @OnClick(R.id.btn_launch_new_medical_appointment)
    public void newMedicalAppointment() {
        dialog = new DialogFragmentMedicalAppointment();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "lel");
        dialog.setContext(this);
        dialog.setCreateMedicalAppointment(this);
    }

    @OnClick(R.id.watch_medical_appointments)
    public void watchMedicalAppointments() {
        startActivity(new Intent(this, HistoryOfAppointmentsActivity.class).putExtra("patient", patient.getId()));
    }

    @OnClick(R.id.btn_delete)
    public void deletePatient() {
        Patient patient = new Patient();
        patient.setName(nameEditText.getText().toString());
        patient.setLastName(lastnameEditText.getText().toString());
        patient.setId(idTextView.getText().toString());
        patient.setTreatment(checkBoxYes.isChecked());
        patient.setBirthdate(birthday);
        patient.setValue(Double.parseDouble(moderingFeeEditText.getText().toString()));
        presenter.deletePatient(patient);
    }

    private void getDate(final TextView textView, final int type) {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + dayOfMonth : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + mesActual : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                textViewBirthDay.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);

                if (type == 0) {
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH, month);
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    birthday = cal.getTime();
                } else {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(newAppointment);
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH, month);
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    newAppointment = cal.getTime();
                }

            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    @Override
    public void createMedicalAppointment(MedicalAppointment medicalAppointment) {
        medicalAppointment.setPatientId(patient.getId());
        presenter.createMedicalAppointment(medicalAppointment);
    }

    @Override
    public void isReady() {
        dialog.setDoctors(doctors);
    }

    @Override
    public void confirm(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }


}
