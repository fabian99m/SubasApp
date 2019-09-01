package com.proyecto.subasapp.Modelo;

import android.widget.Spinner;

public class Celular {

    private int id;
    private String marca;
    private String modelo;
    private String gama;
    private float costo;


    public Celular() {
    }

    public Celular(int id, String marca, String modelo, String gama, float costo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.gama = gama;
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
        this.gama = gama;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
}
