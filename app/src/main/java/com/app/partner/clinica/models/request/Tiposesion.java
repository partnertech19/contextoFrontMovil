package com.app.partner.clinica.models.request;

import java.io.Serializable;

public class Tiposesion implements Serializable {

    private int iidtiposesion;
    private String snombre;

    public int getIidtiposesion() {
        return iidtiposesion;
    }

    public void setIidtiposesion(int iidtiposesion) {
        this.iidtiposesion = iidtiposesion;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    @Override
    public String toString() {
        return snombre;
    }
}
