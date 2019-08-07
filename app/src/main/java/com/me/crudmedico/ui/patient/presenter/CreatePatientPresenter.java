package com.me.crudmedico.ui.patient.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.me.crudmedico.data.DataBase;
import com.me.crudmedico.model.Patient;
import com.me.crudmedico.ui.doctor.contract.MainDoctorContract;
import com.me.crudmedico.ui.patient.contract.CreatePatientContract;

import java.lang.ref.WeakReference;

public class CreatePatientPresenter implements CreatePatientContract.Presenter {
    private WeakReference<CreatePatientContract.View> view;
    private Context context;


    @Override
    public void setView(CreatePatientContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void createPatient(Patient patient) {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", patient.getId());
        contentValues.put("name", patient.getName());
        contentValues.put("lastname", patient.getLastName());
        contentValues.put("birthday", patient.getBirthdate().getTime());
        contentValues.put("treatment", patient.getTreatment());
        contentValues.put("value", patient.getValue());
        sqLiteDatabase.insert("patient", null, contentValues);
        sqLiteDatabase.close();
        view.get().confirm("el paciente fue creado");
    }
}
