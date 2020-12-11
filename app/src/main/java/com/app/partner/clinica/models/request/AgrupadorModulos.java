package com.app.partner.clinica.models.request;

import java.util.List;

public class AgrupadorModulos {

    private Integer idagrupadormodulos;
    private String nombre;
    private Integer ambito;
    private Integer estado;
    private String accion;
    private Boolean active;
    private String dash;
    private String icono;
    private Integer orden;
    private List<Pagina> lsPaginas;
    
    public Integer getIdagrupadormodulos() {
        return idagrupadormodulos;
    }

    public void setIdagrupadormodulos(Integer idagrupadormodulos) {
        this.idagrupadormodulos = idagrupadormodulos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAmbito() {
        return ambito;
    }

    public void setAmbito(Integer ambito) {
        this.ambito = ambito;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDash() {
        return dash;
    }

    public void setDash(String dash) {
        this.dash = dash;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public List<Pagina> getLsPaginas() {
        return lsPaginas;
    }

    public void setLsPaginas(List<Pagina> lsPaginas) {
        this.lsPaginas = lsPaginas;
    }
}
