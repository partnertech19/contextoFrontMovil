package com.app.partner.clinica.models.request;

import java.io.Serializable;

public class Asistenciagrupo implements Serializable {

    private Integer iidasistenciagrupo;
    private Integer iidpacientegrupo; // buscar en tabla pacientegrupo, por idpaciente y idgrupo
    private Long tfechasesion;
    private String tfechasesiontmp;
    private Integer iidempresa;
    private Integer iflagasistencia;
//    private List<Pacientegrupo> lsGrupoPaciente = new ArrayList<>();
//    private List<Doctores> lsResponsables = new ArrayList<>();
    private Integer iiddoctor;
    private Integer iidsesion;
    private Integer iidpaciente; // Contenedor - NO GUARDAR INFORMACION
    private Integer iidgrupohabilidades; // Contenedor- NO GUARDAR INFORMACION

    public Integer getIidasistenciagrupo() {
        return iidasistenciagrupo;
    }

    public void setIidasistenciagrupo(Integer iidasistenciagrupo) {
        this.iidasistenciagrupo = iidasistenciagrupo;
    }

    public Integer getIidpacientegrupo() {
        return iidpacientegrupo;
    }

    public void setIidpacientegrupo(Integer iidpacientegrupo) {
        this.iidpacientegrupo = iidpacientegrupo;
    }

    public Long getTfechasesion() {
        return tfechasesion;
    }

    public void setTfechasesion(Long tfechasesion) {
        this.tfechasesion = tfechasesion;
    }

    public String getTfechasesiontmp() {
        return tfechasesiontmp;
    }

    public void setTfechasesiontmp(String tfechasesiontmp) {
        this.tfechasesiontmp = tfechasesiontmp;
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

    public Integer getIiddoctor() {
        return iiddoctor;
    }

    public void setIiddoctor(Integer iiddoctor) {
        this.iiddoctor = iiddoctor;
    }

    public Integer getIidsesion() {
        return iidsesion;
    }

    public void setIidsesion(Integer iidsesion) {
        this.iidsesion = iidsesion;
    }

    public Integer getIidpaciente() {
        return iidpaciente;
    }

    public void setIidpaciente(Integer iidpaciente) {
        this.iidpaciente = iidpaciente;
    }

    public Integer getIidgrupohabilidades() {
        return iidgrupohabilidades;
    }

    public void setIidgrupohabilidades(Integer iidgrupohabilidades) {
        this.iidgrupohabilidades = iidgrupohabilidades;
    }
}
