package com.app.partner.clinica.models.request;

public class MesSpinner {
    private String nombre;
    private Integer numero;

    public MesSpinner() {
    }

    public MesSpinner(String nombre, Integer numero) {
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
