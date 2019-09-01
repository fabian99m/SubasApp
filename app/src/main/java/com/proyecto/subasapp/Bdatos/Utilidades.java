package com.proyecto.subasapp.Bdatos;

public class Utilidades {

    public final static String TABLA_OFERTANTE = "OFERTANTE";
    public final static String CAMPO_nombre = "nombre";
    public final static String CAMPO_cedula = "cedula";
    public final static String CAMPO_deposito = "deposito";

    public  final static String TABLA_CELULARES = "CELULAR";
    public final static String CAMPO_id = "id";
    public final static String CAMPO_marca = "marca";
    public final static String CAMPO_modelo = "modelo";
    public final static String CAMPO_gama = "gama";
    public final static String CAMPO_costo = "costo";


    public final static String CREAR_TABLA_OFERTANTE = "CREATE TABLE " + TABLA_OFERTANTE + "  " +
            "(" + CAMPO_nombre + " TEXT , " + CAMPO_cedula + " INTEGER , " + CAMPO_deposito + " REAL)";

    public final  static String CREAR_TABLA_CELULAR = "CREATE TABLE "+TABLA_CELULARES+"  "+
            "("+CAMPO_id+" INTEGER , " + CAMPO_marca+" TEXT ," + CAMPO_modelo+" TEXT ," +
            CAMPO_gama+" TEXT ," + CAMPO_costo+" REAL)";

}