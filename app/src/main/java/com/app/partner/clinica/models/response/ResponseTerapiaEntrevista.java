package com.app.partner.clinica.models.response;

import com.app.partner.clinica.models.request.TerapiaEntrevista;
import com.app.partner.clinica.models.request.Terapiaindividual;

import java.util.List;

public class ResponseTerapiaEntrevista {

    private TerapiaEntrevista defaultObj;
    private List<TerapiaEntrevista> aaData;
    private List<Object> items;
    private Integer estado;
    private String msg;
    private String token;
    private String page;
    private Integer cantidad;
    private Integer total_count;
    private String pass;
    private Boolean ok;
    private Boolean incomplete_results;

    public TerapiaEntrevista getDefaultObj() {
        return defaultObj;
    }

    public void setDefaultObj(TerapiaEntrevista defaultObj) {
        this.defaultObj = defaultObj;
    }

    public List<TerapiaEntrevista> getAaData() {
        return aaData;
    }

    public void setAaData(List<TerapiaEntrevista> aaData) {
        this.aaData = aaData;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }
}
