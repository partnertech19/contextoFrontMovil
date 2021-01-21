package com.app.partner.clinica.models.request;

import java.io.Serializable;

public class Perfiles implements Serializable {

    private Integer idperfiles;
    private String nombres;
    private Integer estado;
    private String accion;
    private Integer ambito;
    private Integer idagrupadormodulos;
    private Integer idempresa;
    private String scodigo;
    private Boolean bbloqueado;

    public Integer getIdperfiles() {
        return idperfiles;
    }

    public void setIdperfiles(Integer idperfiles) {
        this.idperfiles = idperfiles;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Integer getAmbito() {
        return ambito;
    }

    public void setAmbito(Integer ambito) {
        this.ambito = ambito;
    }

    public Integer getIdagrupadormodulos() {
        return idagrupadormodulos;
    }

    public void setIdagrupadormodulos(Integer idagrupadormodulos) {
        this.idagrupadormodulos = idagrupadormodulos;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getScodigo() {
        return scodigo;
    }

    public void setScodigo(String scodigo) {
        this.scodigo = scodigo;
    }

    public Boolean getBbloqueado() {
        return bbloqueado;
    }

    public void setBbloqueado(Boolean bbloqueado) {
        this.bbloqueado = bbloqueado;
    }
}
