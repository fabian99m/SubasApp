package com.proyecto.subasapp.Bdatos;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.proyecto.subasapp.Modelo.Celular;
import com.proyecto.subasapp.Modelo.Ofertante;

import java.util.ArrayList;

public interface Consulta {

     String TABLA_OFERTANTE = "OFERTANTE";
     String CAMPO_nombre = "nombre";
     String CAMPO_cedula = "cedula";
     String CAMPO_deposito = "deposito";

     String TABLA_CELULAR = "CELULAR";
     String CAMPO_id = "id";
     String CAMPO_marca = "marca";
     String CAMPO_modelo = "modelo";
     String CAMPO_gama = "gama";
     String CAMPO_costo = "costo";


     String CREAR_TABLA_OFERTANTE = "CREATE TABLE " + TABLA_OFERTANTE + "  " +
            "(" + CAMPO_nombre + " TEXT , " + CAMPO_cedula + " INTEGER , " + CAMPO_deposito + " REAL)";

     String CREAR_TABLA_CELULAR = "CREATE TABLE "+TABLA_CELULAR+"  "+
            "("+CAMPO_id+" INTEGER , " + CAMPO_marca+" TEXT ," + CAMPO_modelo+" TEXT ," +
            CAMPO_gama+" TEXT ," + CAMPO_costo+" REAL)";

     String ELIMINAR_TABLA_OFERTANTE = "DROP TABLE if exists "+TABLA_OFERTANTE;

     String ELIMINAR_TABLA_CELULAR = "DROP TABLE if exists "+TABLA_CELULAR;


      static void GuardarBD(Activity act, String nombre, Long cedula, float deposito) {
          Conexion conexion = new Conexion(act, "datos", null, 1);
          SQLiteDatabase database = conexion.getWritableDatabase();
          ContentValues values = new ContentValues();
          values.put(Consulta.CAMPO_nombre, nombre);
          values.put(Consulta.CAMPO_cedula, cedula);
          values.put(Consulta.CAMPO_deposito, deposito);
          database.insert(Consulta.TABLA_OFERTANTE, Consulta.CAMPO_nombre, values);
          database.close();
          conexion.close();
     }
      static void GuardarBD(Activity act, int id, String marca, String modelo, String gama, float costo) {
          Conexion conexion = new Conexion(act, "datos", null, 1);
          SQLiteDatabase database = conexion.getWritableDatabase();
          ContentValues values = new ContentValues();
          values.put(Consulta.CAMPO_id, id);
          values.put(Consulta.CAMPO_marca, marca);
          values.put(Consulta.CAMPO_modelo, modelo);
          values.put(Consulta.CAMPO_gama, gama);
          values.put(Consulta.CAMPO_costo, costo);
          database.insert(Consulta.TABLA_CELULAR, Consulta.CAMPO_id, values);
          database.close();
          conexion.close();
     }

     static ArrayList<Ofertante> CargarOfertanteBD(Activity act) {
          ArrayList<Ofertante> ofertantes = new ArrayList<>();
          Conexion conexion = new Conexion(act, "datos", null, 1);
          SQLiteDatabase database = conexion.getWritableDatabase();
          Cursor cursor = database.rawQuery("SELECT * FROM " + Consulta.TABLA_OFERTANTE, null);
          if (cursor.getCount() > 0) {
               while (cursor.moveToNext()) {
                    ofertantes.add(new Ofertante(cursor.getString(0), cursor.getLong(1), cursor.getInt(2)));
               }
          }
          cursor.close();
          database.close();
          conexion.close();

          return ofertantes;
     }

    static ArrayList<Celular> CargarCelularBD(Activity act) {
        ArrayList<Celular> celulares = new ArrayList<>();
        Conexion conexion = new Conexion(act, "datos", null, 1);
        SQLiteDatabase database = conexion.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + Consulta.TABLA_CELULAR, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                celulares.add(new Celular(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getFloat(4)));
            }
        }
        cursor.close();
        database.close();
        conexion.close();

        return celulares;
    }

     static void ElimnarBD(Context c, Long id,String tabla,String campo) {
          Conexion conexion = new Conexion(c, "datos", null, 1);
          SQLiteDatabase database = conexion.getWritableDatabase();
          database.delete(tabla, campo + "=" + id, null);
          database.close();
          conexion.close();
     }

    static void ElimnarBD(Context c, int id,String tabla,String campo) {
        Conexion conexion = new Conexion(c, "datos", null, 1);
        SQLiteDatabase database = conexion.getWritableDatabase();
        database.delete(tabla, campo + "=" + id, null);
        database.close();
        conexion.close();
    }


    static void ModificarBD(Context context, Long  id, String nombre, Long cedula, float deposito) {
          Conexion conexion = new Conexion(context, "datos", null, 1);
          SQLiteDatabase database = conexion.getWritableDatabase();
          ContentValues values = new ContentValues();
          values.put(Consulta.CAMPO_nombre, nombre);
          values.put(Consulta.CAMPO_cedula, cedula);
          values.put(Consulta.CAMPO_deposito, deposito);
          database.update(Consulta.TABLA_OFERTANTE, values, Consulta.CAMPO_cedula + "=" + id, null);
          database.close();
          conexion.close();
     }

    static void ModificarBD(Context context,int oldID , int id , String marca, String modelo, String gama, float costo) {
        Conexion conexion = new Conexion(context, "datos", null, 1);
        SQLiteDatabase database = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Consulta.CAMPO_id, id);
        values.put(Consulta.CAMPO_marca, marca);
        values.put(Consulta.CAMPO_modelo, modelo);
        values.put(Consulta.CAMPO_gama, gama);
        values.put(Consulta.CAMPO_costo, costo);
        database.update(Consulta.TABLA_CELULAR, values, Consulta.CAMPO_id + "=" + oldID, null);
        database.close();
        conexion.close();
    }

}
