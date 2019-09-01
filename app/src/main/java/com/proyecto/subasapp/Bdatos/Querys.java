package com.proyecto.subasapp.Bdatos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.proyecto.subasapp.Modelo.Ofertante;

import static com.proyecto.subasapp.UI.MainActivity.subas;


public class Querys {


    public static void GuardarBD(Activity act, String nombre, int cedula, float deposito) {
        Conexion con = new Conexion(act, "datos", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_nombre, nombre);
        values.put(Utilidades.CAMPO_cedula, cedula);
        values.put(Utilidades.CAMPO_deposito, deposito);
        db.insert(Utilidades.TABLA_OFERTANTE, Utilidades.CAMPO_nombre, values);
        db.close();
        con.close();
    }
    public static void GuardarBD(Activity act, int id , String marca , String modelo , String gama , float costo) {
        Log.d("test111",Utilidades.CREAR_TABLA_CELULAR);
        Conexion con = new Conexion(act, "datos", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_id, id);
        values.put(Utilidades.CAMPO_marca, marca);
        values.put(Utilidades.CAMPO_modelo, modelo);
        values.put(Utilidades.CAMPO_gama, gama);
        values.put(Utilidades.CAMPO_costo, costo);
        db.insert(Utilidades.TABLA_CELULARES ,Utilidades.CAMPO_id, values);
        db.close();
        con.close();
    }

    public static void CargarOferBD(Activity act) {
        Conexion con = new Conexion(act, "datos", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_OFERTANTE, null);
        //Subasta subas = new Subasta(new ArrayList<Ofertante>());
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                subas.ofer.add(new Ofertante(cursor.getString(0), cursor.getInt(1), cursor.getFloat(2)));
            }
        }
        cursor.close();
        db.close();
        con.close();
    }

    public static void ElimnarOferBD(Context c, int id) {
        Conexion con = new Conexion(c, "datos", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        db.execSQL("DELETE FROM " + Utilidades.TABLA_OFERTANTE + " WHERE " + Utilidades.CAMPO_cedula + "=" + id);
        db.close();
        con.close();
    }

    public static void EditarOferBD(Context c, int id, String nombre, int cedula, float deposito) {
        Conexion con = new Conexion(c, "datos", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        db.execSQL("UPDATE " + Utilidades.TABLA_OFERTANTE + " set " + Utilidades.CAMPO_nombre + "=" + "\'" + nombre + "\'"
                + "," + Utilidades.CAMPO_cedula + "=" + cedula + "," + Utilidades.CAMPO_deposito + "=" + deposito + " WHERE " + Utilidades.CAMPO_cedula + "=" + id);
        db.close();
        con.close();
    }






}