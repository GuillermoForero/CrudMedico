package com.me.crudmedico.ui.patient.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.crudmedico.data.DataBase;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.model.MedicalAppointment;
import com.me.crudmedico.model.Patient;
import com.me.crudmedico.ui.patient.contract.DetailPatientContract;
import com.me.crudmedico.ui.patient.contract.MainPatientContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DetailPatientPresenter implements DetailPatientContract.Presenter {

    private WeakReference<DetailPatientContract.View> view;
    private Context context;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setView(DetailPatientContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void deletePatient(Patient patient) {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        sqLiteDatabase.delete("patient", "id=" + "'"+patient.getId()+"'", null);
        sqLiteDatabase.close();
        view.get().confirm("El paciente fue borrado");
    }

    @Override
    public void editPatient(Patient patient) {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", patient.getId());
        contentValues.put("name", patient.getName());
        contentValues.put("lastname", patient.getLastName());
        contentValues.put("birthday", patient.getBirthdate().getTime());
        contentValues.put("treatment", patient.getTreatment());
        contentValues.put("value", patient.getValue());
        sqLiteDatabase.update("patient", contentValues, "id="+"'"+patient.getId()+"'", null);
        sqLiteDatabase.close();
    }

    @Override
    public void createMedicalAppointment(MedicalAppointment medicalAppointment) {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        System.out.println("fue creada: id: "+ medicalAppointment.getPatientId());
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codeDoctor", medicalAppointment.getDoctorCode());
        contentValues.put("idPatient", medicalAppointment.getPatientId());
        contentValues.put("newMedical", medicalAppointment.getDate().getTime());
        contentValues.put("attended", false);
        sqLiteDatabase.insert("appointment", null, contentValues);
        sqLiteDatabase.close();
        view.get().confirm("La cita fue creada");
    }

    @Override
    public void getDoctors() {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from doctor", null);
        List<Doctor> doctors = new ArrayList<>();
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Doctor doctor = new Doctor();
                doctor.setCode(cursor.getString(0));
                doctor.setSpeciality(cursor.getString(1));
                doctor.setYearsOfExperiences(cursor.getFloat(2));
                doctor.setConsultingRoom(cursor.getString(3));
                doctor.setHome(Boolean.valueOf(cursor.getString(4)));
                doctors.add(doctor);
            } while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        view.get().setDoctors(doctors);
    }
}
