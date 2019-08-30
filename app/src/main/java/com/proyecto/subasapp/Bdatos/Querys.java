package com.proyecto.subasapp.Bdatos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import  com.proyecto.subasapp.Modelo.Ofertante;
import  static com.proyecto.subasapp.UI.MainActivity.subas;


public class Querys {


    public static void GuardaOferBD(Activity a, EditText nombre, EditText cedula, EditText deposito) {

        Conexion con = new Conexion(a, "datos", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_nombre, nombre.getText().toString());
        values.put(Utilidades.CAMPO_cedula, cedula.getText().toString());
        values.put(Utilidades.CAMPO_deposito, deposito.getText().toString());
        db.insert(Utilidades.TABLA_OFERTANTE, Utilidades.CAMPO_nombre, values);
        db.close();
        con.close();
    }

    public static void CargarOferBD(Activity a) {
        Conexion con = new Conexion(a, "datos", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_OFERTANTE, null);
        //Subasta subas = new Subasta(new ArrayList<Ofertante>());
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                subas.ofer.add(new Ofertante(cursor.getString(0), cursor.getInt(1), cursor.getFloat(2)));
            }
        }
        db.close();
        con.close();
    }

    public static void ElimnarOferBD(Context c, int cedula) {
        Conexion con = new Conexion(c, "datos", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
         db.rawQuery("SELECT * FROM " + Utilidades.TABLA_OFERTANTE, null);
         db.execSQL("DELETE FROM "+Utilidades.TABLA_OFERTANTE+" WHERE "+Utilidades.CAMPO_cedula+"="+cedula);
        db.close();
        con.close();
    }


}