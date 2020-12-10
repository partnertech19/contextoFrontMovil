package com.app.partner.clinica.models.response;

import java.util.List;

public class ResponseWrapper {

    Object defaultObj;
    List<Object> aaData;
    List<Object> items;
    private Integer estado;
    private String msg;
    private String token;
    private String page;
    private Integer cantidad;
    private Integer total_count;
    private String pass;
    private Boolean ok;
    private Boolean incomplete_results;

    public Boolean getOk() {
        return ok;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Object getDefaultObj() {
        return defaultObj;
    }

    public void setDefaultObj(Object defaultObj) {
        this.defaultObj = defaultObj;
    }

    public List<Object> getAaData() {
        return aaData;
    }

    public void setAaData(List<Object> aaData) {
        this.aaData = aaData;
    }
}
