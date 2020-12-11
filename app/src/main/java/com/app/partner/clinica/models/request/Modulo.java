package com.app.partner.clinica.models.request;

import java.util.List;

public class Modulo {

    private Integer idmodulo;
    private String descripcion;
    private String icono;
    private String raiz;
    private Integer estado;
    private String accion;
    private List<Pagina> lsPaginas;

    public Integer getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Integer idmodulo) {
        this.idmodulo = idmodulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getRaiz() {
        return raiz;
    }

    public void setRaiz(String raiz) {
        this.raiz = raiz;
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

    public List<Pagina> getLsPaginas() {
        return lsPaginas;
    }

    public void setLsPaginas(List<Pagina> lsPaginas) {
        this.lsPaginas = lsPaginas;
    }
}
