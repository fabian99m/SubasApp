package com.proyecto.subasapp.Modelo;


public class Ofertante {

    private String nombre;
    private Long cedula;
    private int deposito;


    public Ofertante() {

    }

    public Ofertante(String nombre, Long cedula, int deposito) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.deposito = deposito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public int getDeposito() {
        return deposito;
    }

    public void setDeposito(int deposito) {
        this.deposito = deposito;
    }
}