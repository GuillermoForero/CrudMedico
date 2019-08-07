package com.me.crudmedico.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table doctor(code varchar(100) primary key, speciality varchar(100), yearsOfExperiences FLOAT, consultingRoom varchar(200), home boolean)");
        db.execSQL("create table patient(id varchar(100) primary key, name varchar(100), lastname varchar(100), birthday date, treatment boolean, value DOUBLE)");
        db.execSQL("create table appointment(codeDoctor varchar(100),idPatient varchar(100),newMedical date, attended boolean, imagefirmsvg varchar(500), FOREIGN KEY (codeDoctor) REFERENCES doctor(code), FOREIGN KEY (idPatient) REFERENCES patient(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
