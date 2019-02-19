package com.example.javi.gestion_de_clientes.pojos;

/**
 * Created by Javi on 12/11/2017.
 */

public class ZonaVenta {
    int id;
    String numZona;
    String localidad;

    public ZonaVenta(){


    }

    public ZonaVenta(int id, String numZona, String localidad) {
        this.id= id;
        this.numZona = numZona;
        this.localidad = localidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumZona() {
        return numZona;
    }

    public void setNumZona(String numZona) {
        this.numZona = numZona;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
