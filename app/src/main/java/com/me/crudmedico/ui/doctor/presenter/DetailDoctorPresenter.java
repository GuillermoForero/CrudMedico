package com.me.crudmedico.ui.doctor.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.me.crudmedico.data.DataBase;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.ui.doctor.contract.CreateDoctorContract;
import com.me.crudmedico.ui.doctor.contract.DetailDoctorContract;
import com.me.crudmedico.ui.doctor.contract.MainDoctorContract;

import java.lang.ref.WeakReference;

public class DetailDoctorPresenter implements DetailDoctorContract.Presenter {
    private WeakReference<DetailDoctorContract.View> view;
    private Context context;

    @Override
    public void setView(DetailDoctorContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        sqLiteDatabase.delete("doctor", "code=" + "'"+doctor.getCode()+"'", null);
        sqLiteDatabase.close();
        view.get().confirm("El doctor fue borrado");
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void editDoctor(Doctor doctor) {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("code", doctor.getCode());
        contentValues.put("speciality", doctor.getSpeciality());
        contentValues.put("yearsOfExperiences", doctor.getYearsOfExperiences());
        contentValues.put("consultingRoom", doctor.getConsultingRoom());
        contentValues.put("home", doctor.getHome());
        sqLiteDatabase.update("doctor", contentValues, "code="+"'"+doctor.getCode()+"'", null);
        sqLiteDatabase.close();
    }
}
