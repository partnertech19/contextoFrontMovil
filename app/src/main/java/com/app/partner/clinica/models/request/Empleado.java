package com.app.partner.clinica.models.request;

import java.io.Serializable;
import java.util.Date;

public class Empleado implements Serializable {

    private Integer id;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombres;
    private String userId;
    private String email;
    private String password;
    private Integer estado;
    private String accion;
    private Boolean auth;
    private Date fechacumpleanios;
    private String fechacumpleaniosStr;
    private Integer idarea;
    private Integer idcargo;
//    private Sutipodocumentoidentidad sutipodocumentoidentidad;
    private String documentoidentidad;
    private Integer idsutipodocumentoidentidad;
    private Integer estadoregistrado;
    private Integer idempresa;
    private Integer idtipoempleado;
//    private List<Empresa> lsEmpresasEmpleado;
//    private Tipoempleado tipoempleado;
    private Perfiles perfiles;
    private Integer idperfiles;
    private String username;
    private String role;
    private String direccion;
    private String celular;
    private String foto;
    private Integer idlocal;
    private Boolean estadochat;
    private Integer iidreferencia; // id: doctor, alumno, paciente

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Date getFechacumpleanios() {
        return fechacumpleanios;
    }

    public void setFechacumpleanios(Date fechacumpleanios) {
        this.fechacumpleanios = fechacumpleanios;
    }

    public String getFechacumpleaniosStr() {
        return fechacumpleaniosStr;
    }

    public void setFechacumpleaniosStr(String fechacumpleaniosStr) {
        this.fechacumpleaniosStr = fechacumpleaniosStr;
    }

    public Integer getIdarea() {
        return idarea;
    }

    public void setIdarea(Integer idarea) {
        this.idarea = idarea;
    }

    public Integer getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Integer idcargo) {
        this.idcargo = idcargo;
    }

    public String getDocumentoidentidad() {
        return documentoidentidad;
    }

    public void setDocumentoidentidad(String documentoidentidad) {
        this.documentoidentidad = documentoidentidad;
    }

    public Integer getIdsutipodocumentoidentidad() {
        return idsutipodocumentoidentidad;
    }

    public void setIdsutipodocumentoidentidad(Integer idsutipodocumentoidentidad) {
        this.idsutipodocumentoidentidad = idsutipodocumentoidentidad;
    }

    public Integer getEstadoregistrado() {
        return estadoregistrado;
    }

    public void setEstadoregistrado(Integer estadoregistrado) {
        this.estadoregistrado = estadoregistrado;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Integer getIdtipoempleado() {
        return idtipoempleado;
    }

    public void setIdtipoempleado(Integer idtipoempleado) {
        this.idtipoempleado = idtipoempleado;
    }

    public Integer getIdperfiles() {
        return idperfiles;
    }

    public void setIdperfiles(Integer idperfiles) {
        this.idperfiles = idperfiles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(Integer idlocal) {
        this.idlocal = idlocal;
    }

    public Boolean getEstadochat() {
        return estadochat;
    }

    public void setEstadochat(Boolean estadochat) {
        this.estadochat = estadochat;
    }

    public Perfiles getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(Perfiles perfiles) {
        this.perfiles = perfiles;
    }

    public Integer getIidreferencia() {
        return iidreferencia;
    }

    public void setIidreferencia(Integer iidreferencia) {
        this.iidreferencia = iidreferencia;
    }
}
