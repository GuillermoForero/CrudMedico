package com.me.crudmedico.ui.patient.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Patient;
import com.me.crudmedico.ui.patient.contract.CreatePatientContract;
import com.me.crudmedico.ui.patient.presenter.CreatePatientPresenter;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.me.crudmedico.utils.Util.edittextNotEmpty;
import static com.me.crudmedico.utils.Util.textviewNotEmpty;

public class CreatePatientActivity extends AppCompatActivity implements CreatePatientContract.View {

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
    @BindView(R.id.birthday_textview)
    TextView textViewBirthDay;
    @BindView(R.id.name_edit_text)
    EditText nameEditText;
    @BindView(R.id.lastname_edit_text)
    EditText lastnameEditText;
    @BindView(R.id.id_edit_text)
    EditText idEditText;
    @BindView(R.id.checkBox_yes)
    CheckBox checkBoxYes;
    @BindView(R.id.checkBox_no)
    CheckBox checkBoxNo;
    @BindView(R.id.modering_fee_edit_text)
    EditText moderingFeeEditText;
    CreatePatientContract.Presenter presenter;
    Date birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.birthday_textview)
    public void getBirthday() {
        getDate();
    }

    private void initView() {
        presenter = new CreatePatientPresenter();
        presenter.setView(this);
        presenter.setContext(this);
    }


    private void getDate() {
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

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                birthday = cal.getTime();


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    @OnClick(R.id.btn_sign_up)
    public void createPatient() {
        Patient patient = new Patient();
        if (edittextNotEmpty(nameEditText) && edittextNotEmpty(lastnameEditText) && edittextNotEmpty(idEditText) && textviewNotEmpty(textViewBirthDay)) {
            patient.setName(nameEditText.getText().toString());
            patient.setLastName(lastnameEditText.getText().toString());
            patient.setId(idEditText.getText().toString());
            patient.setTreatment(checkBoxYes.isChecked());
            patient.setBirthdate(birthday);
            presenter.createPatient(patient);
        } else {
            confirm("Todos los campos deben estar llenos");
        }
    }


    @Override
    public void confirm(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}