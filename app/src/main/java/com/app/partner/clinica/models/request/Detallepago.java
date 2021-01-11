package com.app.partner.clinica.models.request;

import java.sql.Timestamp;

public class Detallepago {

    private Integer iiddetallepago;
    private Integer iidpaciente;
    private Integer iidservicio;
    private Double dtotal;
    private Double dmontodoctor;
    private Double dmontopagado;
    private Integer iidestadopago;
    private Timestamp tfechapago;
    private Integer iidpago;
    private Integer iidtiposervicio;

    public Integer getIiddetallepago() {
        return iiddetallepago;
    }

    public void setIiddetallepago(Integer iiddetallepago) {
        this.iiddetallepago = iiddetallepago;
    }

    public Integer getIidpaciente() {
        return iidpaciente;
    }

    public void setIidpaciente(Integer iidpaciente) {
        this.iidpaciente = iidpaciente;
    }

    public Integer getIidservicio() {
        return iidservicio;
    }

    public void setIidservicio(Integer iidservicio) {
        this.iidservicio = iidservicio;
    }

    public Double getDtotal() {
        return dtotal;
    }

    public void setDtotal(Double dtotal) {
        this.dtotal = dtotal;
    }

    public Double getDmontodoctor() {
        return dmontodoctor;
    }

    public void setDmontodoctor(Double dmontodoctor) {
        this.dmontodoctor = dmontodoctor;
    }

    public Double getDmontopagado() {
        return dmontopagado;
    }

    public void setDmontopagado(Double dmontopagado) {
        this.dmontopagado = dmontopagado;
    }

    public Integer getIidestadopago() {
        return iidestadopago;
    }

    public void setIidestadopago(Integer iidestadopago) {
        this.iidestadopago = iidestadopago;
    }

    public Timestamp getTfechapago() {
        return tfechapago;
    }

    public void setTfechapago(Timestamp tfechapago) {
        this.tfechapago = tfechapago;
    }

    public Integer getIidpago() {
        return iidpago;
    }

    public void setIidpago(Integer iidpago) {
        this.iidpago = iidpago;
    }

    public Integer getIidtiposervicio() {
        return iidtiposervicio;
    }

    public void setIidtiposervicio(Integer iidtiposervicio) {
        this.iidtiposervicio = iidtiposervicio;
    }
}
