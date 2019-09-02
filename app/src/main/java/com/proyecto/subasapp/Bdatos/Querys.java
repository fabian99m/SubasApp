package com.proyecto.subasapp.Bdatos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.proyecto.subasapp.Modelo.Ofertante;

import java.util.ArrayList;


public class Querys {


    public static void GuardarBD(Activity act, String nombre, int cedula, float deposito) {
        Conexion conexion = new Conexion(act, "datos", null, 1);
        SQLiteDatabase database = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_nombre, nombre);
        values.put(Utilidades.CAMPO_cedula, cedula);
        values.put(Utilidades.CAMPO_deposito, deposito);
        database.insert(Utilidades.TABLA_OFERTANTE, Utilidades.CAMPO_nombre, values);
        database.close();
        conexion.close();
    }

    public static void GuardarBD(Activity act, int id, String marca, String modelo, String gama, float costo) {
        Log.d("test111", Utilidades.CREAR_TABLA_CELULAR);
        Conexion conexion = new Conexion(act, "datos", null, 1);
        SQLiteDatabase database = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_id, id);
        values.put(Utilidades.CAMPO_marca, marca);
        values.put(Utilidades.CAMPO_modelo, modelo);
        values.put(Utilidades.CAMPO_gama, gama);
        values.put(Utilidades.CAMPO_costo, costo);
        database.insert(Utilidades.TABLA_CELULARES, Utilidades.CAMPO_id, values);
        database.close();
        conexion.close();
    }

    public static ArrayList<Ofertante> CargarOferBD(Activity act) {
        ArrayList<Ofertante> ofer = new ArrayList<>();
        Conexion conexion = new Conexion(act, "datos", null, 1);
        SQLiteDatabase database = conexion.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + Utilidades.TABLA_OFERTANTE, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ofer.add(new Ofertante(cursor.getString(0), cursor.getInt(1), cursor.getFloat(2)));
            }
        }
        cursor.close();
        database.close();
        conexion.close();
        return ofer;

    }

    public static void ElimnarOferBD(Context c, int id) {
        Conexion conexion = new Conexion(c, "datos", null, 1);
        SQLiteDatabase database = conexion.getWritableDatabase();
        database.delete(Utilidades.TABLA_OFERTANTE, Utilidades.CAMPO_cedula + "=" + id, null);
        database.close();
        conexion.close();
    }

    public static void EditarOferBD(Context c, int id, String nombre, int cedula, float deposito) {
        Conexion conexion = new Conexion(c, "datos", null, 1);
        SQLiteDatabase database = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_nombre, nombre);
        values.put(Utilidades.CAMPO_cedula, cedula);
        values.put(Utilidades.CAMPO_deposito, deposito);
        database.update(Utilidades.TABLA_OFERTANTE, values, Utilidades.CAMPO_cedula + "=" + id, null);
        database.close();
        conexion.close();
    }

}