package com.app.partner.clinica.models.request;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comprobantes implements Serializable {

    private Integer iidcomprobante;
    private String sseriedocumento;
    private String snrodocumento;
    private Integer iflagfactelect;
    private Timestamp tfechadocumento;
    private Integer iidtipomoneda;
    private String snrodocreceptor;
    private Double dtipocambio;
    private Double dsubtotal;
    private Double digv;
    private Double dtotal;
    private Integer iidreferencia;
    private Integer iidtipodocfacturacion;
    private Integer iidtiposervicio;
    private String snombrereceptor;
//    private List<Detallecomprobante> lsdetalle;
    private Integer iidempresa;
    private String sterminos; // nuevo
    private String snotas; // nuevo
    private String sasuntoemail; // nuevo
//    private Tipodocfacturacion tipodocfacturacion;

    public Integer getIidcomprobante() {
        return iidcomprobante;
    }

    public void setIidcomprobante(Integer iidcomprobante) {
        this.iidcomprobante = iidcomprobante;
    }

    public String getSseriedocumento() {
        return sseriedocumento;
    }

    public void setSseriedocumento(String sseriedocumento) {
        this.sseriedocumento = sseriedocumento;
    }

    public String getSnrodocumento() {
        return snrodocumento;
    }

    public void setSnrodocumento(String snrodocumento) {
        this.snrodocumento = snrodocumento;
    }

    public Integer getIflagfactelect() {
        return iflagfactelect;
    }

    public void setIflagfactelect(Integer iflagfactelect) {
        this.iflagfactelect = iflagfactelect;
    }

    public Timestamp getTfechadocumento() {
        return tfechadocumento;
    }

    public void setTfechadocumento(Timestamp tfechadocumento) {
        this.tfechadocumento = tfechadocumento;
    }

    public Integer getIidtipomoneda() {
        return iidtipomoneda;
    }

    public void setIidtipomoneda(Integer iidtipomoneda) {
        this.iidtipomoneda = iidtipomoneda;
    }

    public String getSnrodocreceptor() {
        return snrodocreceptor;
    }

    public void setSnrodocreceptor(String snrodocreceptor) {
        this.snrodocreceptor = snrodocreceptor;
    }

    public Double getDtipocambio() {
        return dtipocambio;
    }

    public void setDtipocambio(Double dtipocambio) {
        this.dtipocambio = dtipocambio;
    }

    public Double getDsubtotal() {
        return dsubtotal;
    }

    public void setDsubtotal(Double dsubtotal) {
        this.dsubtotal = dsubtotal;
    }

    public Double getDigv() {
        return digv;
    }

    public void setDigv(Double digv) {
        this.digv = digv;
    }

    public Double getDtotal() {
        return dtotal;
    }

    public void setDtotal(Double dtotal) {
        this.dtotal = dtotal;
    }

    public Integer getIidreferencia() {
        return iidreferencia;
    }

    public void setIidreferencia(Integer iidreferencia) {
        this.iidreferencia = iidreferencia;
    }

    public Integer getIidtipodocfacturacion() {
        return iidtipodocfacturacion;
    }

    public void setIidtipodocfacturacion(Integer iidtipodocfacturacion) {
        this.iidtipodocfacturacion = iidtipodocfacturacion;
    }

    public Integer getIidtiposervicio() {
        return iidtiposervicio;
    }

    public void setIidtiposervicio(Integer iidtiposervicio) {
        this.iidtiposervicio = iidtiposervicio;
    }

    public String getSnombrereceptor() {
        return snombrereceptor;
    }

    public void setSnombrereceptor(String snombrereceptor) {
        this.snombrereceptor = snombrereceptor;
    }

    public Integer getIidempresa() {
        return iidempresa;
    }

    public void setIidempresa(Integer iidempresa) {
        this.iidempresa = iidempresa;
    }

    public String getSterminos() {
        return sterminos;
    }

    public void setSterminos(String sterminos) {
        this.sterminos = sterminos;
    }

    public String getSnotas() {
        return snotas;
    }

    public void setSnotas(String snotas) {
        this.snotas = snotas;
    }

    public String getSasuntoemail() {
        return sasuntoemail;
    }

    public void setSasuntoemail(String sasuntoemail) {
        this.sasuntoemail = sasuntoemail;
    }
}
