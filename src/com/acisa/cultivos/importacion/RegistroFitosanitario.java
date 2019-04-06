/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.importacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jcperan
 */
public class RegistroFitosanitario {
    
    
    private Integer numeroRegistro;
    private String regMagrama;
    private String nombreComercial;
    private Date fechaInscripcion;
    private Date fechaCaducidad;
    private String titular;
    private String fabricante;
    private String composicion;
    private String tipoFuncion;
    private String presentacion;
    private String ordenacion;
    
    private Date fechaActualizacion;
    
    private List<RegistroUsosAutorizados> listaUsos;
    

    // <editor-fold defaultstate="collapsed" desc="Getter/Setter">
    public Integer getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Integer numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getRegMagrama() {
        return regMagrama;
    }

    public void setRegMagrama(String regMagrama) {
        this.regMagrama = regMagrama;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    public String getTipoFuncion() {
        return tipoFuncion;
    }

    public void setTipoFuncion(String tipoFuncion) {
        this.tipoFuncion = tipoFuncion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public List<?> getListaUsos() {
        return listaUsos;
    }

    public void setListaUsos(List<?> listaUsos) {
        this.listaUsos = (List<RegistroUsosAutorizados>) listaUsos;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    public String getOrdenacion() {
        return ordenacion;
    }

    public void setOrdenacion(String ordenacion) {
        this.ordenacion = ordenacion;
    }    
    // </editor-fold>

    /**
     * Constructor del Objeto - Inicialización de Valores
     */
    public RegistroFitosanitario() {
        this.numeroRegistro = 0;
        this.regMagrama = "";
        this.nombreComercial = "";
        this.fechaInscripcion = new Date();
        this.fechaCaducidad = new Date();
        this.titular = "";
        this.fabricante = "";
        this.composicion = "";
        this.tipoFuncion = "";
        this.presentacion = "";
        this.ordenacion = "";
        
        this.fechaActualizacion = new Date();
        
        this.listaUsos = new ArrayList<>();
    }
    
    
    
}
