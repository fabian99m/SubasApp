package com.proyecto.subasapp.Modelo;


import java.util.ArrayList;


public class Subasta {

    public ArrayList<Ofertante> ofer;
    public ArrayList<Celular> cel;

    public Subasta() {
    }

    public Subasta(ArrayList<Ofertante> ofer, ArrayList<Celular> cel) {
        this.ofer = ofer;
        this.cel = cel;
    }

    public ArrayList<Ofertante> getOfer() {
        return ofer;
    }

    public void setOfer(ArrayList<Ofertante> ofer) {
        this.ofer = ofer;
    }


    public ArrayList<Celular> getCel() {
        return cel;
    }

    public void setCel(ArrayList<Celular> cel) {
        this.cel = cel;
    }

    public Boolean estaVacia() {
        return (ofer.size() == 0 && cel.size() == 0) ? true : false;
    }
}