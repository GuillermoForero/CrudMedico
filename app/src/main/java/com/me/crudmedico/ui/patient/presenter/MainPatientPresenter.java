package com.me.crudmedico.ui.patient.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.crudmedico.data.DataBase;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.model.Patient;
import com.me.crudmedico.ui.patient.contract.MainPatientContract;

import java.lang.ref.WeakReference;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainPatientPresenter implements MainPatientContract.Presenter {

    private WeakReference<MainPatientContract.View> view;
    private Context context;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void getPatients() {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from patient", null);
        List<Patient> patients = new ArrayList<>();
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Patient patient = new Patient();
                patient.setId(cursor.getString(0));
                patient.setName(cursor.getString(1));
                patient.setLastName(cursor.getString(2));
                patient.setBirthdate(new Date(cursor.getLong(3)));
                patient.setTreatment(Boolean.valueOf(cursor.getString(4)));
                patient.setValue(cursor.getDouble(5));
                patients.add(patient);
            } while(cursor.moveToNext());
        }
        view.get().setPatients(patients);
        sqLiteDatabase.close();
    }


    @Override
    public void setView(MainPatientContract.View view) {
        this.view = new WeakReference<>(view);
    }


}
