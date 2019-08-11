package com.me.crudmedico.ui.doctor.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.crudmedico.data.DataBase;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.ui.doctor.contract.DetailDoctorContract;

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
        Cursor cursor = sqLiteDatabase.rawQuery("select * from appointment where codeDoctor='" + doctor.getCode() + "'", null);
        if (cursor.getCount() > 0) {
            view.get().confirm("No se puede borrar el doctor porque tiene citas en el historial");
        } else {
            sqLiteDatabase.delete("doctor", "code=" + "'" + doctor.getCode() + "'", null);
            view.get().confirm("El doctor fue borrado");
        }
        sqLiteDatabase.close();

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
        sqLiteDatabase.update("doctor", contentValues, "code=" + "'" + doctor.getCode() + "'", null);
        sqLiteDatabase.close();
    }

    @Override
    public void deleteHistory(Doctor doctor) {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        sqLiteDatabase.delete("appointment", "codeDoctor=" + "'" + doctor.getCode() + "'", null);
        sqLiteDatabase.close();
    }
}
