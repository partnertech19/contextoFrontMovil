package com.app.partner.clinica.models.request;

import java.io.Serializable;

public class Pacientes implements Serializable {

    private Integer iidpaciente;
    private String snombre;
    private Integer iidtipodocumento;
    private String snrodocumento;
    private String sdireccion;
    private String semail;
    private String stelefono;
    private String smovil;
    private String sdocfacturacion;
    private Integer iidtipodocfacturacion;
    private Integer iidterapeuta;
    private Integer iidpsiquiatra;
    private Boolean bflagsocial;  // 1
    private Long tfechainicioterapeuta;
    private Long tfechafinterapeuta;
    private Long tfechainiciopsi;
    private Long tfechafinpsi;
    private Double dtarifasocialindterapeuta;
    private Double dporcentsocialindcentroterapeuta;
    private Double dtarifasocialgrupterapeuta;
    private Double dporcentsocialgrupcentroterapeuta;
    private Double dtarifasocialindpsi;
    private Double dporcentsocialindcentropsi;
    private Double dtarifasocialgruppsi;
    private Double dporcentsocialgrupcentropsi;
    private Integer iflaggrupo;
    private String snombrereceptorfacturacion;
    private Boolean bllevagrupo;
    private Integer iidempresa;
    private Integer iidservicio; // contenedor
    private Integer idiasproxcontrol;
    private Integer iidentrevista; // contenedor
    private Integer iidubigeo;
    private Ubigeo ubigeo;

    public Integer getIidpaciente() {
        return iidpaciente;
    }

    public void setIidpaciente(Integer iidpaciente) {
        this.iidpaciente = iidpaciente;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    public Integer getIidtipodocumento() {
        return iidtipodocumento;
    }

    public void setIidtipodocumento(Integer iidtipodocumento) {
        this.iidtipodocumento = iidtipodocumento;
    }

    public String getSnrodocumento() {
        return snrodocumento;
    }

    public void setSnrodocumento(String snrodocumento) {
        this.snrodocumento = snrodocumento;
    }

    public String getSdireccion() {
        return sdireccion;
    }

    public void setSdireccion(String sdireccion) {
        this.sdireccion = sdireccion;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getStelefono() {
        return stelefono;
    }

    public void setStelefono(String stelefono) {
        this.stelefono = stelefono;
    }

    public String getSmovil() {
        return smovil;
    }

    public void setSmovil(String smovil) {
        this.smovil = smovil;
    }

    public String getSdocfacturacion() {
        return sdocfacturacion;
    }

    public void setSdocfacturacion(String sdocfacturacion) {
        this.sdocfacturacion = sdocfacturacion;
    }

    public Integer getIidtipodocfacturacion() {
        return iidtipodocfacturacion;
    }

    public void setIidtipodocfacturacion(Integer iidtipodocfacturacion) {
        this.iidtipodocfacturacion = iidtipodocfacturacion;
    }

    public Integer getIidterapeuta() {
        return iidterapeuta;
    }

    public void setIidterapeuta(Integer iidterapeuta) {
        this.iidterapeuta = iidterapeuta;
    }

    public Integer getIidpsiquiatra() {
        return iidpsiquiatra;
    }

    public void setIidpsiquiatra(Integer iidpsiquiatra) {
        this.iidpsiquiatra = iidpsiquiatra;
    }

    public Boolean getBflagsocial() {
        return bflagsocial;
    }

    public void setBflagsocial(Boolean bflagsocial) {
        this.bflagsocial = bflagsocial;
    }

    public Long getTfechainicioterapeuta() {
        return tfechainicioterapeuta;
    }

    public void setTfechainicioterapeuta(Long tfechainicioterapeuta) {
        this.tfechainicioterapeuta = tfechainicioterapeuta;
    }

    public Long getTfechafinterapeuta() {
        return tfechafinterapeuta;
    }

    public void setTfechafinterapeuta(Long tfechafinterapeuta) {
        this.tfechafinterapeuta = tfechafinterapeuta;
    }

    public Long getTfechainiciopsi() {
        return tfechainiciopsi;
    }

    public void setTfechainiciopsi(Long tfechainiciopsi) {
        this.tfechainiciopsi = tfechainiciopsi;
    }

    public Long getTfechafinpsi() {
        return tfechafinpsi;
    }

    public void setTfechafinpsi(Long tfechafinpsi) {
        this.tfechafinpsi = tfechafinpsi;
    }

    public Double getDtarifasocialindterapeuta() {
        return dtarifasocialindterapeuta;
    }

    public void setDtarifasocialindterapeuta(Double dtarifasocialindterapeuta) {
        this.dtarifasocialindterapeuta = dtarifasocialindterapeuta;
    }

    public Double getDporcentsocialindcentroterapeuta() {
        return dporcentsocialindcentroterapeuta;
    }

    public void setDporcentsocialindcentroterapeuta(Double dporcentsocialindcentroterapeuta) {
        this.dporcentsocialindcentroterapeuta = dporcentsocialindcentroterapeuta;
    }

    public Double getDtarifasocialgrupterapeuta() {
        return dtarifasocialgrupterapeuta;
    }

    public void setDtarifasocialgrupterapeuta(Double dtarifasocialgrupterapeuta) {
        this.dtarifasocialgrupterapeuta = dtarifasocialgrupterapeuta;
    }

    public Double getDporcentsocialgrupcentroterapeuta() {
        return dporcentsocialgrupcentroterapeuta;
    }

    public void setDporcentsocialgrupcentroterapeuta(Double dporcentsocialgrupcentroterapeuta) {
        this.dporcentsocialgrupcentroterapeuta = dporcentsocialgrupcentroterapeuta;
    }

    public Double getDtarifasocialindpsi() {
        return dtarifasocialindpsi;
    }

    public void setDtarifasocialindpsi(Double dtarifasocialindpsi) {
        this.dtarifasocialindpsi = dtarifasocialindpsi;
    }

    public Double getDporcentsocialindcentropsi() {
        return dporcentsocialindcentropsi;
    }

    public void setDporcentsocialindcentropsi(Double dporcentsocialindcentropsi) {
        this.dporcentsocialindcentropsi = dporcentsocialindcentropsi;
    }

    public Double getDtarifasocialgruppsi() {
        return dtarifasocialgruppsi;
    }

    public void setDtarifasocialgruppsi(Double dtarifasocialgruppsi) {
        this.dtarifasocialgruppsi = dtarifasocialgruppsi;
    }

    public Double getDporcentsocialgrupcentropsi() {
        return dporcentsocialgrupcentropsi;
    }

    public void setDporcentsocialgrupcentropsi(Double dporcentsocialgrupcentropsi) {
        this.dporcentsocialgrupcentropsi = dporcentsocialgrupcentropsi;
    }

    public Integer getIflaggrupo() {
        return iflaggrupo;
    }

    public void setIflaggrupo(Integer iflaggrupo) {
        this.iflaggrupo = iflaggrupo;
    }

    public String getSnombrereceptorfacturacion() {
        return snombrereceptorfacturacion;
    }

    public void setSnombrereceptorfacturacion(String snombrereceptorfacturacion) {
        this.snombrereceptorfacturacion = snombrereceptorfacturacion;
    }

    public Boolean getBllevagrupo() {
        return bllevagrupo;
    }

    public void setBllevagrupo(Boolean bllevagrupo) {
        this.bllevagrupo = bllevagrupo;
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

    public Integer getIdiasproxcontrol() {
        return idiasproxcontrol;
    }

    public void setIdiasproxcontrol(Integer idiasproxcontrol) {
        this.idiasproxcontrol = idiasproxcontrol;
    }

    public Integer getIidentrevista() {
        return iidentrevista;
    }

    public void setIidentrevista(Integer iidentrevista) {
        this.iidentrevista = iidentrevista;
    }

    public Integer getIidubigeo() {
        return iidubigeo;
    }

    public void setIidubigeo(Integer iidubigeo) {
        this.iidubigeo = iidubigeo;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }
}
