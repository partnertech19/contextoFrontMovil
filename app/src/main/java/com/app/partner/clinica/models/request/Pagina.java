package com.app.partner.clinica.models.request;

public class Pagina {

    private Integer idpagina;
    private Integer idmodulo;
    private String descripcion;
    private String url;
    private String parametros;
    private String icono;
    private Integer estado;
    private String accion;
    private String nameModulo;
    private Integer siperfil;
    private Integer idperfil;
    private Modulo modulo;
    private Integer idagrupadormodulos;

    public Integer getIdpagina() {
        return idpagina;
    }

    public void setIdpagina(Integer idpagina) {
        this.idpagina = idpagina;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
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

    public String getNameModulo() {
        return nameModulo;
    }

    public void setNameModulo(String nameModulo) {
        this.nameModulo = nameModulo;
    }

    public Integer getSiperfil() {
        return siperfil;
    }

    public void setSiperfil(Integer siperfil) {
        this.siperfil = siperfil;
    }

    public Integer getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Integer getIdagrupadormodulos() {
        return idagrupadormodulos;
    }

    public void setIdagrupadormodulos(Integer idagrupadormodulos) {
        this.idagrupadormodulos = idagrupadormodulos;
    }
}
