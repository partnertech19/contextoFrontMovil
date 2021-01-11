package com.app.partner.clinica.models.request;

import java.io.Serializable;
import java.util.Calendar;

public class Consultoriodoctor implements Serializable {

    private Integer iidconsultoriodoctor;
    private Integer iidconsultorio;
    private Integer iiddoctor;
    private Long tfechainicio;
    private Long tfechafin;
    private Long thorainicio;
    private Long thorafin;
    private Integer itiporepeticion; // 1 - semanal / 2 - mensual
    private String sdias; // [1,2,3,4,5,6,7]
    private Doctores doctor;

    public Integer getIidconsultoriodoctor() {
        return iidconsultoriodoctor;
    }

    public void setIidconsultoriodoctor(Integer iidconsultoriodoctor) {
        this.iidconsultoriodoctor = iidconsultoriodoctor;
    }

    public Integer getIidconsultorio() {
        return iidconsultorio;
    }

    public void setIidconsultorio(Integer iidconsultorio) {
        this.iidconsultorio = iidconsultorio;
    }

    public Integer getIiddoctor() {
        return iiddoctor;
    }

    public void setIiddoctor(Integer iiddoctor) {
        this.iiddoctor = iiddoctor;
    }

    public Long getTfechainicio() {
        return tfechainicio;
    }

    public void setTfechainicio(Long tfechainicio) {
        this.tfechainicio = tfechainicio;
    }

    public Long getTfechafin() {
        return tfechafin;
    }

    public void setTfechafin(Long tfechafin) {
        this.tfechafin = tfechafin;
    }

    public Long getThorainicio() {
        return thorainicio;
    }

    public void setThorainicio(Long thorainicio) {
        this.thorainicio = thorainicio;
    }

    public Long getThorafin() {
        return thorafin;
    }

    public void setThorafin(Long thorafin) {
        this.thorafin = thorafin;
    }

    public Integer getItiporepeticion() {
        return itiporepeticion;
    }

    public void setItiporepeticion(Integer itiporepeticion) {
        this.itiporepeticion = itiporepeticion;
    }

    public String getSdias() {
        return sdias;
    }

    public void setSdias(String sdias) {
        this.sdias = sdias;
    }

    public Doctores getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctores doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        Calendar fechai = Calendar.getInstance();
        fechai.setTimeInMillis(tfechainicio);
        String hi = fechai.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + fechai.get(Calendar.HOUR_OF_DAY) : "" + fechai.get(Calendar.HOUR_OF_DAY);
        String mi = fechai.get(Calendar.MINUTE) < 10 ? "0" + fechai.get(Calendar.MINUTE) : "" + fechai.get(Calendar.MINUTE);

        Calendar fechaf = Calendar.getInstance();
        fechaf.setTimeInMillis(tfechafin);
        String hf = fechaf.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + fechaf.get(Calendar.HOUR_OF_DAY) : "" + fechaf.get(Calendar.HOUR_OF_DAY);
        String mf = fechaf.get(Calendar.MINUTE) < 10 ? "0" + fechaf.get(Calendar.MINUTE) : "" + fechaf.get(Calendar.MINUTE);

        String time = hi + ":" + mi + " - " + hf + ":" + mf;
        return time;
    }
}
