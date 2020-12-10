package com.app.partner.clinica.models.response;

import com.app.partner.clinica.models.request.Usuario;

public class ResponseUsuario {

    private String access_token;
    private String token_type;
    private int expires_in;
    private String scope;
    private String jti;
    private String nombre;
    private Integer iid_perfil;
    private Integer iid_usuario;
    private Usuario usuario;

    public ResponseUsuario() {
    }

    public ResponseUsuario(String access_token, String token_type, int expires_in, String scope, String jti, String nombre, Integer iid_perfil, Integer iid_usuario) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.scope = scope;
        this.jti = jti;
        this.nombre = nombre;
        this.iid_perfil = iid_perfil;
        this.iid_usuario = iid_usuario;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getIid_perfil() {
        return iid_perfil;
    }

    public Integer getIid_usuario() {
        return iid_usuario;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIid_perfil(Integer iid_perfil) {
        this.iid_perfil = iid_perfil;
    }

    public void setIid_usuario(Integer iid_usuario) {
        this.iid_usuario = iid_usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
