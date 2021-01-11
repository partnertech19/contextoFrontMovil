package com.app.partner.clinica.models.request;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Entrevistas implements Serializable {

    private Integer iidentrevista;
    private String snombresolicitante;
    private String snrodocumento;
    private Long tfechaentrevista;
    private Double dmontopagado;
    private String slugarresidencia;
    private String sgradoinstruccion;
    private String socupacion;
    private Integer iinformeadmision;
    private String sentrevistador;
    private Integer iestado;
    private Integer iidtipodocumento;
    private Integer iidtipooperacionpago;
    private Integer iidubigeo;
    private Integer iiddoctor;
    private Boolean bpacienteregistrado;  // nueva columna
    private Ubigeo ubigeo;
    private Pagos pagos;
    private Integer iidtipomoneda; // contenedor
    private List<Comprobantes> lsComprobante;
    private Integer iidempresa;
    private Integer iidtiposesion;
    private Integer iduracionmin;
    private Integer iidtiporeferencia;
    private Integer iidconsultorio;

    public Integer getIidentrevista() {
        return iidentrevista;
    }

    public void setIidentrevista(Integer iidentrevista) {
        this.iidentrevista = iidentrevista;
    }

    public String getSnombresolicitante() {
        return snombresolicitante;
    }

    public void setSnombresolicitante(String snombresolicitante) {
        this.snombresolicitante = snombresolicitante;
    }

    public String getSnrodocumento() {
        return snrodocumento;
    }

    public void setSnrodocumento(String snrodocumento) {
        this.snrodocumento = snrodocumento;
    }

    public Long getTfechaentrevista() {
        return tfechaentrevista;
    }

    public void setTfechaentrevista(Long tfechaentrevista) {
        this.tfechaentrevista = tfechaentrevista;
    }

    public Double getDmontopagado() {
        return dmontopagado;
    }

    public void setDmontopagado(Double dmontopagado) {
        this.dmontopagado = dmontopagado;
    }

    public String getSlugarresidencia() {
        return slugarresidencia;
    }

    public void setSlugarresidencia(String slugarresidencia) {
        this.slugarresidencia = slugarresidencia;
    }

    public String getSgradoinstruccion() {
        return sgradoinstruccion;
    }

    public void setSgradoinstruccion(String sgradoinstruccion) {
        this.sgradoinstruccion = sgradoinstruccion;
    }

    public String getSocupacion() {
        return socupacion;
    }

    public void setSocupacion(String socupacion) {
        this.socupacion = socupacion;
    }

    public Integer getIinformeadmision() {
        return iinformeadmision;
    }

    public void setIinformeadmision(Integer iinformeadmision) {
        this.iinformeadmision = iinformeadmision;
    }

    public String getSentrevistador() {
        return sentrevistador;
    }

    public void setSentrevistador(String sentrevistador) {
        this.sentrevistador = sentrevistador;
    }

    public Integer getIestado() {
        return iestado;
    }

    public void setIestado(Integer iestado) {
        this.iestado = iestado;
    }

    public Integer getIidtipodocumento() {
        return iidtipodocumento;
    }

    public void setIidtipodocumento(Integer iidtipodocumento) {
        this.iidtipodocumento = iidtipodocumento;
    }

    public Integer getIidtipooperacionpago() {
        return iidtipooperacionpago;
    }

    public void setIidtipooperacionpago(Integer iidtipooperacionpago) {
        this.iidtipooperacionpago = iidtipooperacionpago;
    }

    public Integer getIidubigeo() {
        return iidubigeo;
    }

    public void setIidubigeo(Integer iidubigeo) {
        this.iidubigeo = iidubigeo;
    }

    public Integer getIiddoctor() {
        return iiddoctor;
    }

    public void setIiddoctor(Integer iiddoctor) {
        this.iiddoctor = iiddoctor;
    }

    public Boolean getBpacienteregistrado() {
        return bpacienteregistrado;
    }

    public void setBpacienteregistrado(Boolean bpacienteregistrado) {
        this.bpacienteregistrado = bpacienteregistrado;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    public Integer getIidtipomoneda() {
        return iidtipomoneda;
    }

    public void setIidtipomoneda(Integer iidtipomoneda) {
        this.iidtipomoneda = iidtipomoneda;
    }

    public List<Comprobantes> getLsComprobante() {
        return lsComprobante;
    }

    public void setLsComprobante(List<Comprobantes> lsComprobante) {
        this.lsComprobante = lsComprobante;
    }

    public Integer getIidempresa() {
        return iidempresa;
    }

    public void setIidempresa(Integer iidempresa) {
        this.iidempresa = iidempresa;
    }

    public Integer getIidtiposesion() {
        return iidtiposesion;
    }

    public void setIidtiposesion(Integer iidtiposesion) {
        this.iidtiposesion = iidtiposesion;
    }

    public Integer getIduracionmin() {
        return iduracionmin;
    }

    public void setIduracionmin(Integer iduracionmin) {
        this.iduracionmin = iduracionmin;
    }

    public Integer getIidtiporeferencia() {
        return iidtiporeferencia;
    }

    public void setIidtiporeferencia(Integer iidtiporeferencia) {
        this.iidtiporeferencia = iidtiporeferencia;
    }

    public Integer getIidconsultorio() {
        return iidconsultorio;
    }

    public void setIidconsultorio(Integer iidconsultorio) {
        this.iidconsultorio = iidconsultorio;
    }
}
