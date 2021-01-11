package com.app.partner.clinica.models.request;

import java.sql.Timestamp;

public class TerapiaEntrevista {

    private Terapiaindividual terapiaindividual;
    private Entrevistas entrevistas;
    private Long tfechaterapia;
    private Long tfechaentrevista;

    public Terapiaindividual getTerapiaindividual() {
        return terapiaindividual;
    }

    public void setTerapiaindividual(Terapiaindividual terapiaindividual) {
        this.terapiaindividual = terapiaindividual;
    }

    public Entrevistas getEntrevistas() {
        return entrevistas;
    }

    public void setEntrevistas(Entrevistas entrevistas) {
        this.entrevistas = entrevistas;
    }

    public Long getTfechaterapia() {
        return tfechaterapia;
    }

    public void setTfechaterapia(Long tfechaterapia) {
        this.tfechaterapia = tfechaterapia;
    }

    public Long getTfechaentrevista() {
        return tfechaentrevista;
    }

    public void setTfechaentrevista(Long tfechaentrevista) {
        this.tfechaentrevista = tfechaentrevista;
    }
}
