package com.app.partner.clinica.models.request;

import java.io.Serializable;

public class Doctores implements Serializable {

    private Integer iiddoctor;
    private String snombre;
    private String sapepaterno;
    private String sapematerno;
    private Integer iidtipodocumento;
    private Integer iidcategoriadoctor;
    private Integer iidtipo;
    private String snrodocumento;
    private String semail;
    private String smovil;
    private Integer itarifaind;
    private Integer iidprofesion;
    private Integer iidempresa;
    private Integer iidservicio;
//    private Categoriadoctor categoriadoctor;

    public Integer getIiddoctor() {
        return iiddoctor;
    }

    public void setIiddoctor(Integer iiddoctor) {
        this.iiddoctor = iiddoctor;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    public String getSapepaterno() {
        return sapepaterno;
    }

    public void setSapepaterno(String sapepaterno) {
        this.sapepaterno = sapepaterno;
    }

    public String getSapematerno() {
        return sapematerno;
    }

    public void setSapematerno(String sapematerno) {
        this.sapematerno = sapematerno;
    }

    public Integer getIidtipodocumento() {
        return iidtipodocumento;
    }

    public void setIidtipodocumento(Integer iidtipodocumento) {
        this.iidtipodocumento = iidtipodocumento;
    }

    public Integer getIidcategoriadoctor() {
        return iidcategoriadoctor;
    }

    public void setIidcategoriadoctor(Integer iidcategoriadoctor) {
        this.iidcategoriadoctor = iidcategoriadoctor;
    }

    public Integer getIidtipo() {
        return iidtipo;
    }

    public void setIidtipo(Integer iidtipo) {
        this.iidtipo = iidtipo;
    }

    public String getSnrodocumento() {
        return snrodocumento;
    }

    public void setSnrodocumento(String snrodocumento) {
        this.snrodocumento = snrodocumento;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getSmovil() {
        return smovil;
    }

    public void setSmovil(String smovil) {
        this.smovil = smovil;
    }

    public Integer getItarifaind() {
        return itarifaind;
    }

    public void setItarifaind(Integer itarifaind) {
        this.itarifaind = itarifaind;
    }

    public Integer getIidprofesion() {
        return iidprofesion;
    }

    public void setIidprofesion(Integer iidprofesion) {
        this.iidprofesion = iidprofesion;
    }

    public Integer getIidempresa() {
        return iidempresa;
    }

    public void setIidempresa(Integer iidempresa) {
        this.iidempresa = iidempresa;
    }

    public Integer getIidservicio() {
        return iidservicio;
    }

    public void setIidservicio(Integer iidservicio) {
        this.iidservicio = iidservicio;
    }
}
