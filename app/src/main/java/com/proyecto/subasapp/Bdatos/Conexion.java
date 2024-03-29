package com.proyecto.subasapp.Bdatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class Conexion extends SQLiteOpenHelper {


    public Conexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Consulta.CREAR_TABLA_OFERTANTE);
        db.execSQL(Consulta.CREAR_TABLA_CELULAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int vantigua, int vnueva) {
        db.execSQL(Consulta.ELIMINAR_TABLA_OFERTANTE);
        db.execSQL(Consulta.ELIMINAR_TABLA_CELULAR);
        onCreate(db);
    }

}