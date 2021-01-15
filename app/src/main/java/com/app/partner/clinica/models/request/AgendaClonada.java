package com.app.partner.clinica.models.request;

public class AgendaClonada {

    private Integer iiddoctor;
    private Long tsemanainicio;
    private Long tsemanafin;
    private Long tsemanaclonada;
    private String sdias;

    public Integer getIiddoctor() {
        return iiddoctor;
    }

    public void setIiddoctor(Integer iiddoctor) {
        this.iiddoctor = iiddoctor;
    }

    public Long getTsemanainicio() {
        return tsemanainicio;
    }

    public void setTsemanainicio(Long tsemanainicio) {
        this.tsemanainicio = tsemanainicio;
    }

    public Long getTsemanafin() {
        return tsemanafin;
    }

    public void setTsemanafin(Long tsemanafin) {
        this.tsemanafin = tsemanafin;
    }

    public Long getTsemanaclonada() {
        return tsemanaclonada;
    }

    public void setTsemanaclonada(Long tsemanaclonada) {
        this.tsemanaclonada = tsemanaclonada;
    }

    public String getSdias() {
        return sdias;
    }

    public void setSdias(String sdias) {
        this.sdias = sdias;
    }
}
