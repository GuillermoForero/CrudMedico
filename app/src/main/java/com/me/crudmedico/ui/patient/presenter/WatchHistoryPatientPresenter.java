package com.me.crudmedico.ui.patient.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.crudmedico.data.DataBase;
import com.me.crudmedico.model.MedicalAppointment;

import com.me.crudmedico.ui.patient.contract.WatchHistoryPatientContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WatchHistoryPatientPresenter implements WatchHistoryPatientContract.Presenter {
    private Context context;
    private WeakReference<WatchHistoryPatientContract.View> view;

    @Override
    public void getMedicalAppointment(String patient) {
        System.out.println("id de busqueda: "+ patient);
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from appointment where idPatient='"+patient+"'", null);
        List<MedicalAppointment> medicalAppointments = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                MedicalAppointment medicalAppointment = new MedicalAppointment();
                medicalAppointment.setDoctorCode(cursor.getString(0));
                medicalAppointment.setPatientId(cursor.getString(1));
                medicalAppointment.setDate(new Date(cursor.getLong(2)));
                medicalAppointment.setAttended(Boolean.valueOf(cursor.getString(3)));
                medicalAppointment.setImageFirmSVG(cursor.getString(4));
                medicalAppointments.add(medicalAppointment);
                if(cursor.getString(3).equals("1")){
                    medicalAppointment.setAttended(true);
                }
                else{
                    medicalAppointment.setAttended(false);
                }
                System.out.println((cursor.getString(3)));
            } while(cursor.moveToNext());
        }
        view.get().setMedicalAppointment(medicalAppointments);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setView(WatchHistoryPatientContract.View view) {
        this.view = new WeakReference<>(view);
    }


    @Override
    public void setAttended(MedicalAppointment medicalAppointment) {
        DataBase dataBase = new DataBase(context, "databaseMedicalCrud", null, 1);
        System.out.println("codeDoctor: "+medicalAppointment.getDoctorCode());
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codeDoctor", medicalAppointment.getDoctorCode());
        contentValues.put("idPatient", medicalAppointment.getPatientId());
        contentValues.put("newMedical", medicalAppointment.getDate().getTime());
        contentValues.put("attended", true);
        contentValues.put("imagefirmsvg", medicalAppointment.getImageFirmSVG());
        int i =sqLiteDatabase.update("appointment", contentValues, "codeDoctor="+"'"+medicalAppointment.getDoctorCode()+"' AND idPatient='"+medicalAppointment.getPatientId()+"' AND newMedical="+medicalAppointment.getDate().getTime(), null);
        sqLiteDatabase.close();
        System.out.println("cantidad: "+ i);
    }
}
