package com.app.partner.clinica.models.response;

import com.app.partner.clinica.models.request.Empleado;
import com.app.partner.clinica.models.request.TerapiaEntrevista;

import java.sql.Timestamp;
import java.util.List;

public class ResponseTerapiaIndividualFecha {

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


}
