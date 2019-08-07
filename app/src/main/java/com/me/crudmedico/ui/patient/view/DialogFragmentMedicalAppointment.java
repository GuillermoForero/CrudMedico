package com.me.crudmedico.ui.patient.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.model.MedicalAppointment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogFragmentMedicalAppointment extends DialogFragment {
    private Context context;

    @BindView(R.id.date_of_new_appoinment)
    TextView textViewDateNewAppoinment;
    @BindView(R.id.time_of_new_appoinment)
    TextView textViewTimeNewAppoinment;
    @BindView(R.id.doctors_spinner)
    Spinner spinnerDoctors;


    List<Doctor> doctors;
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
    Date newAppointment;
    createMedicalAppointment createMedicalAppointment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_create_medical_appointment, container, false);
        ButterKnife.bind(this, v);
        createMedicalAppointment.isReady();
        newAppointment = new Date();
        return v;
    }

    public void setCreateMedicalAppointment(DialogFragmentMedicalAppointment.createMedicalAppointment createMedicalAppointment) {
        this.createMedicalAppointment = createMedicalAppointment;
    }

    @OnClick(R.id.date_of_new_appoinment)
    public void dateOfNewAppoinment(){
        getDate();
    }
    @OnClick(R.id.time_of_new_appoinment)
    public void timeOfNewAppoinment(){
        getTime();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void getDate(){
        DatePickerDialog recogerFecha = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                textViewDateNewAppoinment.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(newAppointment);
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH, month);
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    newAppointment = cal.getTime();


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
    public void setDoctors(List<Doctor> doctors){
        List<String> stringsDoctors = new ArrayList<>();
        for(Doctor doctor: doctors){
            stringsDoctors.add(doctor.getCode());
        }
        System.out.println("context: "+ context);
        this.doctors = doctors;
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, stringsDoctors);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctors.setAdapter(dataAdapter);
    }

    private void getTime(){
        TimePickerDialog recogerHora = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
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
                textViewTimeNewAppoinment.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

    @OnClick(R.id.btn_new_medical_appointment)
    public void createMedicalAppointment(){
        MedicalAppointment medicalAppointment = new MedicalAppointment();
        medicalAppointment.setDate(newAppointment);
        medicalAppointment.setDoctorCode(doctors.get(spinnerDoctors.getSelectedItemPosition()).getCode());
        createMedicalAppointment.createMedicalAppointment(medicalAppointment);
        getDialog().dismiss();
    }

    public interface createMedicalAppointment{
        public void createMedicalAppointment(MedicalAppointment medicalAppointment);

        public void isReady();
    }

}
