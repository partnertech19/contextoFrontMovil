package com.app.partner.clinica.models.request;

import java.io.Serializable;

public class Asistenciacurso implements Serializable {

    private Integer iidasistenciacurso;
    private Integer iidalumnocurso;
    private Long tfechasesion;
    private Integer iidempresa;
//    private List<Alumnocurso> lsAlumnosCursos = new ArrayList<>();
    private Integer iflagasistencia; // 0 - no asistió / 1 - asistió
    private Integer iidreferencia;
    private String tfechasesiontmp;
    private Integer iidsesion;
    private Integer iiddocente;
//    private List<Docente> lsDocentes;
    private Integer iestadopago;
    private Integer iidcurso; // Contenedor - NO GUARDAR INFORMACION
    private Integer iidalumno;

    public Integer getIidasistenciacurso() {
        return iidasistenciacurso;
    }

    public void setIidasistenciacurso(Integer iidasistenciacurso) {
        this.iidasistenciacurso = iidasistenciacurso;
    }

    public Integer getIidalumnocurso() {
        return iidalumnocurso;
    }

    public void setIidalumnocurso(Integer iidalumnocurso) {
        this.iidalumnocurso = iidalumnocurso;
    }

    public Long getTfechasesion() {
        return tfechasesion;
    }

    public void setTfechasesion(Long tfechasesion) {
        this.tfechasesion = tfechasesion;
    }

    public Integer getIidempresa() {
        return iidempresa;
    }

    public void setIidempresa(Integer iidempresa) {
        this.iidempresa = iidempresa;
    }

    public Integer getIflagasistencia() {
        return iflagasistencia;
    }

    public void setIflagasistencia(Integer iflagasistencia) {
        this.iflagasistencia = iflagasistencia;
    }

    public Integer getIidreferencia() {
        return iidreferencia;
    }

    public void setIidreferencia(Integer iidreferencia) {
        this.iidreferencia = iidreferencia;
    }

    public String getTfechasesiontmp() {
        return tfechasesiontmp;
    }

    public void setTfechasesiontmp(String tfechasesiontmp) {
        this.tfechasesiontmp = tfechasesiontmp;
    }

    public Integer getIidsesion() {
        return iidsesion;
    }

    public void setIidsesion(Integer iidsesion) {
        this.iidsesion = iidsesion;
    }

    public Integer getIiddocente() {
        return iiddocente;
    }

    public void setIiddocente(Integer iiddocente) {
        this.iiddocente = iiddocente;
    }

    public Integer getIestadopago() {
        return iestadopago;
    }

    public void setIestadopago(Integer iestadopago) {
        this.iestadopago = iestadopago;
    }

    public Integer getIidcurso() {
        return iidcurso;
    }

    public void setIidcurso(Integer iidcurso) {
        this.iidcurso = iidcurso;
    }

    public Integer getIidalumno() {
        return iidalumno;
    }

    public void setIidalumno(Integer iidalumno) {
        this.iidalumno = iidalumno;
    }
}
