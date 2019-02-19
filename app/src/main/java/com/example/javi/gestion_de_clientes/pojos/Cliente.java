package com.example.javi.gestion_de_clientes.pojos;

import com.example.javi.gestion_de_clientes.constantes.G;

/**
 * Created by Javi on 16/11/2017.
 */

public class Cliente {

    int id;
    String nombre;
    String apellido;
    String telefono;
    int idZona;


    public Cliente(){
        this.id = G.SIN_VALOR_INT;
        this.nombre = G.SIN_VALOR_STRING;
        this.apellido = G.SIN_VALOR_STRING;
        this.telefono = G.SIN_VALOR_STRING;
        this.idZona = G.SIN_VALOR_INT;

    }

    public Cliente(int id , String nombre, String apellido, String telefono, int idZona) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.idZona = idZona;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
