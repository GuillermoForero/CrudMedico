package com.me.crudmedico.ui.doctor.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.crudmedico.data.DataBase;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.ui.doctor.contract.MainDoctorContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainDoctorPresenter implements MainDoctorContract.Presenter {

    private WeakReference<MainDoctorContract.View> view;
    private Context context;

    @Override
    public void getDoctor() {
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
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        view.get().setDoctor(doctors);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setView(MainDoctorContract.View view) {
        this.view = new WeakReference<>(view);
    }
}
