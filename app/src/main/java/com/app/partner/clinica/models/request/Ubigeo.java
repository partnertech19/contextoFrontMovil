package com.app.partner.clinica.models.request;

import java.io.Serializable;

public class Ubigeo implements Serializable {

    private Integer idubigeo;
    private String departamento;
    private String provincia;
    private String distrito;
    private Integer estado;
    private String accion;

    public Integer getIdubigeo() {
        return idubigeo;
    }

    public void setIdubigeo(Integer idubigeo) {
        this.idubigeo = idubigeo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
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
}
