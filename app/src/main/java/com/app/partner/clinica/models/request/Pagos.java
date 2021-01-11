package com.app.partner.clinica.models.request;

import java.sql.Timestamp;
import java.util.List;

public class Pagos {

    private Integer iidpago;
    private Integer iidreferencia;
    private Double dtotal;
    private Double dmontopagado;
    private Double dmontopendiente;
    private Double dmontoacancelar;
    private Integer iidtiposervicio;
    private Integer iidtipomoneda;
    private Integer iidtipopago;
    private Integer iidtipooperacion;
    private List<Detallepago> lsdetalle;
    private Timestamp tfechapago;

    public Integer getIidpago() {
        return iidpago;
    }

    public void setIidpago(Integer iidpago) {
        this.iidpago = iidpago;
    }

    public Integer getIidreferencia() {
        return iidreferencia;
    }

    public void setIidreferencia(Integer iidreferencia) {
        this.iidreferencia = iidreferencia;
    }

    public Double getDtotal() {
        return dtotal;
    }

    public void setDtotal(Double dtotal) {
        this.dtotal = dtotal;
    }

    public Double getDmontopagado() {
        return dmontopagado;
    }

    public void setDmontopagado(Double dmontopagado) {
        this.dmontopagado = dmontopagado;
    }

    public Double getDmontopendiente() {
        return dmontopendiente;
    }

    public void setDmontopendiente(Double dmontopendiente) {
        this.dmontopendiente = dmontopendiente;
    }

    public Double getDmontoacancelar() {
        return dmontoacancelar;
    }

    public void setDmontoacancelar(Double dmontoacancelar) {
        this.dmontoacancelar = dmontoacancelar;
    }

    public Integer getIidtiposervicio() {
        return iidtiposervicio;
    }

    public void setIidtiposervicio(Integer iidtiposervicio) {
        this.iidtiposervicio = iidtiposervicio;
    }

    public Integer getIidtipomoneda() {
        return iidtipomoneda;
    }

    public void setIidtipomoneda(Integer iidtipomoneda) {
        this.iidtipomoneda = iidtipomoneda;
    }

    public Integer getIidtipopago() {
        return iidtipopago;
    }

    public void setIidtipopago(Integer iidtipopago) {
        this.iidtipopago = iidtipopago;
    }

    public Integer getIidtipooperacion() {
        return iidtipooperacion;
    }

    public void setIidtipooperacion(Integer iidtipooperacion) {
        this.iidtipooperacion = iidtipooperacion;
    }

    public List<Detallepago> getLsdetalle() {
        return lsdetalle;
    }

    public void setLsdetalle(List<Detallepago> lsdetalle) {
        this.lsdetalle = lsdetalle;
    }

    public Timestamp getTfechapago() {
        return tfechapago;
    }

    public void setTfechapago(Timestamp tfechapago) {
        this.tfechapago = tfechapago;
    }
}
