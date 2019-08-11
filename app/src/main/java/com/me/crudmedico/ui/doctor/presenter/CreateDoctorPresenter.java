package com.me.crudmedico.ui.doctor.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.me.crudmedico.data.DataBase;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.ui.doctor.contract.CreateDoctorContract;

import java.lang.ref.WeakReference;

public class CreateDoctorPresenter implements CreateDoctorContract.Presenter {

    private WeakReference<CreateDoctorContract.View> view;
    private Context context;

    @Override
    public void getDoctor() {

    }

    @Override
    public void setView(CreateDoctorContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void createDoctor(Doctor doctor) {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("code", doctor.getCode());
        contentValues.put("speciality", doctor.getSpeciality());
        contentValues.put("yearsOfExperiences", doctor.getYearsOfExperiences());
        contentValues.put("consultingRoom", doctor.getConsultingRoom());
        contentValues.put("home", doctor.getHome());
        sqLiteDatabase.insert("doctor", null, contentValues);
        sqLiteDatabase.close();
        view.get().confirm("el doctor fue creado");
    }
}
