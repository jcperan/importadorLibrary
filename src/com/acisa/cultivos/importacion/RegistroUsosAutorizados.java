/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.importacion;

/**
 *
 * @author jcperan
 */
public class RegistroUsosAutorizados {

    String parentesis;
    String cultivo;
    String plaga;
    String plazo;
    String dosificacion;
    String observaciones;

    public RegistroUsosAutorizados() {
        this.plaga = "";
        this.plazo = "";
        this.cultivo = "";
        this.parentesis = "";
        this.dosificacion = "";
        this.observaciones = "";
    }

    public RegistroUsosAutorizados(String cultivo, String plaga, String plazo, String parentesis, String dosificacion, String observaciones) {
        this.cultivo = cultivo;
        this.plaga = plaga;
        this.plazo = plazo;
        this.parentesis = parentesis;
        this.dosificacion = dosificacion;
        this.observaciones = observaciones;
    }

    public String getPlaga() {
        return plaga;
    }

    public void setPlaga(String plaga) {
        this.plaga = plaga;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public String getParentesis() {
        return parentesis;
    }

    public void setParentesis(String parentesis) {
        this.parentesis = parentesis;
    }

    public String getDosificacion() {
        return dosificacion;
    }

    public void setDosificacion(String dosificacion) {
        this.dosificacion = dosificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
