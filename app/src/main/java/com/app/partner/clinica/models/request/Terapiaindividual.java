package com.app.partner.clinica.models.request;

import java.io.Serializable;

public class Terapiaindividual implements Serializable {

    private int iidterapiaindiv;
    private int iiddoctor;
    private int iidpaciente;
    private int iidtipo;
    private Long tfechaterapia;
    private int iestado; // 0 - eliminado / 1 - programado / 2 - completado
    private String snotas;
    private int iidempresa;
    private Pacientes pacientes;
    private int iestadofactura;
    private int iestadodeuda;
    private int iidtiposesion;
    private int iduracionmin;
    private int iidconsultorio;

    public int getIidterapiaindiv() {
        return iidterapiaindiv;
    }

    public void setIidterapiaindiv(int iidterapiaindiv) {
        this.iidterapiaindiv = iidterapiaindiv;
    }

    public int getIiddoctor() {
        return iiddoctor;
    }

    public void setIiddoctor(int iiddoctor) {
        this.iiddoctor = iiddoctor;
    }

    public int getIidpaciente() {
        return iidpaciente;
    }

    public void setIidpaciente(int iidpaciente) {
        this.iidpaciente = iidpaciente;
    }

    public int getIidtipo() {
        return iidtipo;
    }

    public void setIidtipo(int iidtipo) {
        this.iidtipo = iidtipo;
    }

    public Long getTfechaterapia() {
        return tfechaterapia;
    }

    public void setTfechaterapia(Long tfechaterapia) {
        this.tfechaterapia = tfechaterapia;
    }

    public int getIestado() {
        return iestado;
    }

    public void setIestado(int iestado) {
        this.iestado = iestado;
    }

    public String getSnotas() {
        return snotas;
    }

    public void setSnotas(String snotas) {
        this.snotas = snotas;
    }

    public int getIidempresa() {
        return iidempresa;
    }

    public void setIidempresa(int iidempresa) {
        this.iidempresa = iidempresa;
    }

    public Pacientes getPacientes() {
        return pacientes;
    }

    public void setPacientes(Pacientes pacientes) {
        this.pacientes = pacientes;
    }

    public int getIestadofactura() {
        return iestadofactura;
    }

    public void setIestadofactura(int iestadofactura) {
        this.iestadofactura = iestadofactura;
    }

    public int getIestadodeuda() {
        return iestadodeuda;
    }

    public void setIestadodeuda(int iestadodeuda) {
        this.iestadodeuda = iestadodeuda;
    }

    public int getIidtiposesion() {
        return iidtiposesion;
    }

    public void setIidtiposesion(int iidtiposesion) {
        this.iidtiposesion = iidtiposesion;
    }

    public int getIduracionmin() {
        return iduracionmin;
    }

    public void setIduracionmin(int iduracionmin) {
        this.iduracionmin = iduracionmin;
    }

    public Integer getIidconsultorio() {
        return iidconsultorio;
    }

    public void setIidconsultorio(Integer iidconsultorio) {
        this.iidconsultorio = iidconsultorio;
    }
}
