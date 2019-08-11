package com.me.crudmedico.ui.doctor.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.crudmedico.data.DataBase;
import com.me.crudmedico.model.MedicalAppointment;
import com.me.crudmedico.ui.doctor.contract.WatchHistoryDoctorContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WatchHistoryDoctorPresenter implements WatchHistoryDoctorContract.Presenter {
    private Context context;
    private WeakReference<WatchHistoryDoctorContract.View> view;

    @Override
    public void getMedicalAppointment(String doctor) {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from appointment where codeDoctor='" + doctor + "'", null);
        List<MedicalAppointment> medicalAppointments = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                MedicalAppointment medicalAppointment = new MedicalAppointment();
                medicalAppointment.setDoctorCode(cursor.getString(0));
                medicalAppointment.setPatientId(cursor.getString(1));
                medicalAppointment.setDate(new Date(cursor.getLong(2)));
                medicalAppointment.setAttended(Boolean.valueOf(cursor.getString(3)));
                medicalAppointments.add(medicalAppointment);
            } while (cursor.moveToNext());
        }
        view.get().setMedicalAppointment(medicalAppointments);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setView(WatchHistoryDoctorContract.View view) {
        this.view = new WeakReference<>(view);
    }


}
