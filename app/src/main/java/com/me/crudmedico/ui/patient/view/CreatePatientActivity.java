package com.me.crudmedico.ui.patient.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Patient;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePatientActivity extends AppCompatActivity implements  View.OnClickListener{

    @BindView(R.id.birthday_textview)
    TextView textViewBirthDay;
    @BindView(R.id.date_of_new_appoinment)
    TextView textViewDateNewAppoinment;
    @BindView(R.id.time_of_new_appoinment)
    TextView textViewTimeNewAppoinment;
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

    Date birthday;
    Date newAppointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_create_patient);
        newAppointment = new Date();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.birthday_textview:
                getDate(textViewBirthDay,0);
                break;
            case R.id.date_of_new_appoinment:
                getDate(textViewDateNewAppoinment, 0);
                break;
            case R.id.time_of_new_appoinment:
                getTime(textViewTimeNewAppoinment);
                break;
        }
    }

    private void getDate(final TextView textView, final int type){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                textView.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);

                if(type == 0){
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH, month);
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    birthday = cal.getTime();
                }
                else{
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
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    private void getTime(final TextView textView){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado

                Calendar cal = Calendar.getInstance();
                cal.setTime(newAppointment);
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                newAppointment = cal.getTime();
                textView.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

    @OnClick(R.id.btn_sign_up)
    public void createPatient(){
        Patient patient = new Patient();
        patient.setName(nameEditText.getText().toString());
        patient.setLastName(lastnameEditText.getText().toString());
        patient.setId(idEditText.getText().toString());
        patient.setTreatment(checkBoxYes.isChecked());
        patient.setBirthdate(birthday);
    }


}