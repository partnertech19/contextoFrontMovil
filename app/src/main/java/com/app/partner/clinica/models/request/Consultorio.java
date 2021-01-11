package com.app.partner.clinica.models.request;

import java.io.Serializable;

public class Consultorio implements Serializable {

    private Integer iidconsultorio;
    private String snombre;
    private String sdescripcion;
    private Integer iestado;
    private Integer idempresa;
    private String thorainicio;
    private String thorafin;

    public Integer getIidconsultorio() {
        return iidconsultorio;
    }

    public void setIidconsultorio(Integer iidconsultorio) {
        this.iidconsultorio = iidconsultorio;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    public String getSdescripcion() {
        return sdescripcion;
    }

    public void setSdescripcion(String sdescripcion) {
        this.sdescripcion = sdescripcion;
    }

    public Integer getIestado() {
        return iestado;
    }

    public void setIestado(Integer iestado) {
        this.iestado = iestado;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getThorainicio() {
        return thorainicio;
    }

    public void setThorainicio(String thorainicio) {
        this.thorainicio = thorainicio;
    }

    public String getThorafin() {
        return thorafin;
    }

    public void setThorafin(String thorafin) {
        this.thorafin = thorafin;
    }

    @Override
    public String toString() {
        return snombre;
    }
}
