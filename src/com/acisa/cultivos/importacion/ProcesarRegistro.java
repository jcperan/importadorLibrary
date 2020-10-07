/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.importacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author jcperan
 */
public class ProcesarRegistro {

    private RegistroFitosanitario registro = new RegistroFitosanitario();

    public RegistroFitosanitario getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroFitosanitario registro) {
        this.registro = registro;
    }

    public ProcesarRegistro() {

    }

    /*
    Obtener datos del PDF obtenido
     */
    public ProcesarRegistro(Integer numeroRegistro, String documento) {
        
        int MAX_CHAR = 50;

        registro.setNumeroRegistro(numeroRegistro);
        registro.setNombreComercial(ObtenerDato("Nombre Comerci", documento));

        String titular = ObtenerSiguienteDato("Titular", documento).trim();
        String fabrica = ObtenerSiguienteDato("Fabricante", documento).trim();
        registro.setTitular(titular.substring(0, (titular.length() < MAX_CHAR)?titular.length():MAX_CHAR));
        registro.setFabricante(fabrica.substring(0, (fabrica.length() < MAX_CHAR)?fabrica.length():MAX_CHAR));

        registro.setRegMagrama(ObtenerDato("Registro:", documento));
        registro.setComposicion(ObtenerDato("Composici", documento));
        registro.setTipoFuncion(ObtenerDato("Tipo de Func", documento));
        
        String presentacion = ObtenerSiguienteDato("Tipo de Envase", documento);
        if (presentacion=="") presentacion=ObtenerSiguienteDato("Presentaci", documento);
        if (presentacion=="") presentacion=ObtenerSiguienteDato("Capacidad", documento);
        registro.setPresentacion(presentacion);
        registro.setOrdenacion(ObtenerOrdenacion(registro.getComposicion()));

        registro.setFechaInscripcion(FormatoFecha(ObtenerDato("cha de Inscripci", documento)));
        registro.setFechaCaducidad(FormatoFecha(ObtenerDato("cha de Caducidad", documento)));
        if (registro.getFechaCaducidad() == null) registro.setFechaCaducidad(FormatoFecha(ObtenerDato("cha limite de Ve", documento)));
        
        if (registro.getFechaInscripcion() == null) {
            registro.setFechaInscripcion(FormatoFecha(""));
        }

        if (registro.getFechaCaducidad() == null) {
            registro.setFechaCaducidad(FormatoFecha(""));
        }

        registro.setListaUsos(ObtenerUsos(documento));
        this.ProcesarObservaciones(documento);

        int nada = 1;
        nada = 0;
    }

    /**
     * Devuelve el valor encontrado para el texto a buscar
     *
     * @param TextoBuscar
     * @param documento
     * @return
     */
    private String ObtenerDato(String TextoBuscar, String documento) {

        String resultadoObtenerDato = "";
        StringTokenizer tokens = new StringTokenizer(documento);

        while (tokens.hasMoreTokens()) {

            String primero = tokens.nextToken("\n").replace("_", "");
            if (primero.toUpperCase().trim().contains(TextoBuscar.toUpperCase())) {
                if (primero.contains(":")) {
                    String dato = primero.split(":")[1].substring(1);
                    resultadoObtenerDato = dato.trim();
                    return resultadoObtenerDato;
                } else {
                    resultadoObtenerDato = primero.substring(1);
                    return resultadoObtenerDato;
                }
            }

        }

        return resultadoObtenerDato;

    }

    private String ObtenerSiguienteDato(String TextoBuscar, String documento) {

        String resultadoObtenerSiguienteDato = "";
        StringTokenizer tokens = new StringTokenizer(documento);

        while (tokens.hasMoreTokens()) {

            String primero = tokens.nextToken("\n");
            if (primero.toUpperCase().contains(TextoBuscar.toUpperCase())) {
                String dato = tokens.nextToken("\n");
                resultadoObtenerSiguienteDato = dato.trim();
                if (resultadoObtenerSiguienteDato.contains("_")) {
                    if (TextoBuscar=="Fabricante") {
                        if (resultadoObtenerSiguienteDato.split("_").length > 1) {
                            resultadoObtenerSiguienteDato = resultadoObtenerSiguienteDato.split("_")[1];
                        } else {
                            resultadoObtenerSiguienteDato = resultadoObtenerSiguienteDato.split("_")[0];                            
                        }
                    } else {
                        resultadoObtenerSiguienteDato = resultadoObtenerSiguienteDato.split("_")[0];
                    }
                }
                return resultadoObtenerSiguienteDato;
            }

        }

        return resultadoObtenerSiguienteDato;

    }

    /**
     * Obtener el dato entre corchetes
     *
     * @param dato
     * @return
     */
    private String ObtenerOrdenacion(String dato) {

        String resultadoObtenerOrdenacion = "";

        Integer posicion = dato.indexOf("[");
        if (posicion > 0) {
            dato = dato.substring(posicion + 1);
            posicion = dato.indexOf("]");
            if (posicion > 0) {
                dato = dato.substring(0, posicion);
                resultadoObtenerOrdenacion = dato;
            }
        }

        return resultadoObtenerOrdenacion;
    }

    /**
     * Convertir el string dato en una fecha
     *
     * @param dato
     * @return
     */
    private Date FormatoFecha(String dato) {

        Date resultadoFormatoFecha = null;
        String entrada = dato.replace(" ","");

        if (!"".equals(entrada)) {
            int dd = Integer.parseInt(entrada.substring(0, 2));
            int mm = Integer.parseInt(entrada.substring(3, 5));
            int aa = Integer.parseInt(entrada.substring(6, 10));
            Calendar cal = Calendar.getInstance();
            cal.set(aa, mm - 1, dd);
            resultadoFormatoFecha = cal.getTime();
        }

        return resultadoFormatoFecha;

    }

    private List<RegistroUsosAutorizados> resultado;
    private RegistroUsosAutorizados anterior;
    private RegistroUsosAutorizados actual;
    int index = 0;

    private List<RegistroUsosAutorizados> ObtenerUsos(String documento) {

        boolean swUsos = false;
        String documentoUsos = "";
        resultado = new ArrayList<>();

        StringTokenizer tokens = new StringTokenizer(documento);
        while (tokens.hasMoreTokens()) {

            String primero = tokens.nextToken("\n");
            if (primero.contains("Plazo de seguridad")) {
                break;
            }
            if (primero.contains("Usos autorizados:")) {
                swUsos = true;
            }
            if (primero.contains("Usos y dosis")) {
                swUsos = true;
            }
            if (swUsos) {
                documentoUsos = documentoUsos + "\n" + primero;
            }

        }

        anterior = new RegistroUsosAutorizados();
        actual = new RegistroUsosAutorizados();
        index = 0;

        String datos[];

        tokens = new StringTokenizer(documentoUsos, "\n", true);
        while (tokens.hasMoreTokens()) {

            String primero = tokens.nextToken("\n");
            datos = primero.split("_");

            if (datos[0].substring(0, 1).equals("(")) {
                int pos = datos[0].indexOf(' ');
                if (pos>0) {
                    if (datos.length>0) actual.parentesis = datos[0].substring(0, pos);
                    if (datos.length>0) actual.cultivo = datos[0].substring(pos + 1);
                    if (datos.length>1) actual.plaga = datos[1];
                    if (datos.length>2) actual.dosificacion = datos[2];
                    if (datos.length>3) actual.plazo = datos[3];
                    actual.observaciones = "";
                }

                AgregarCultivo("");
            }
        }

        return resultado;

    }

    private void AgregarCultivo(String cultivo) {

        if (!actual.cultivo.equals("")) {
            resultado.add(new RegistroUsosAutorizados(actual.cultivo, actual.plaga, actual.plazo, actual.parentesis, actual.dosificacion, actual.observaciones));
            index = resultado.size();
        }

        anterior = actual;
        actual = new RegistroUsosAutorizados();
        actual.cultivo = cultivo;

    }

    private void ProcesarObservaciones(String documento) {
        
        boolean swInicio = false;
        String resultado = "";
        String linea;

        StringTokenizer tokens = new StringTokenizer(documento);
        while (tokens.hasMoreTokens()) {

            linea = tokens.nextToken("\n");
            
            if (linea.contains("P.S.: Plazo de seguridad")) swInicio = true;
            if (linea.contains("Condicionamientos preventivos")) break;
            
            if (swInicio) resultado += linea + "\n";
            
        }
        
        ArrayList arrayUsos = new ArrayList();

        String observaciones = "";
        
        tokens = new StringTokenizer(resultado);
        while (tokens.hasMoreTokens()) {

            linea = tokens.nextToken("\n");
            if (linea.contains("(")) {
                if (!observaciones.trim().equals("")) {
                    AgregarObservaciones(arrayUsos, observaciones);
                    arrayUsos = new ArrayList();
                    observaciones = "";
                }
            }
            observaciones += linea;
             
            while (observaciones.contains("(")) {
                if (observaciones.contains(")")) {
                    String entre = "";
                    if (observaciones.indexOf(")") > observaciones.indexOf("(")) 
                        entre = observaciones.substring(observaciones.indexOf("(")+1,observaciones.indexOf(")"));
                    if (com.acisa.cultivos.utilidades.Rutinas.Valor(entre)>0) {
                        arrayUsos.add(entre);
                        observaciones = observaciones.substring(observaciones.indexOf(")") + 1);
                    } else break;
                } else {
                    observaciones = observaciones.substring(observaciones.indexOf("(") + 1);
                }
            }
        }

        AgregarObservaciones(arrayUsos, observaciones);
        
    }
    
    private void AgregarObservaciones(ArrayList arrayUsos, String observaciones) {
        
        List<RegistroUsosAutorizados> listaUsos = (List<RegistroUsosAutorizados>) registro.getListaUsos();
        for (RegistroUsosAutorizados uso : listaUsos ) {
            for (int i=0;i<arrayUsos.size();i++) {
                String parentesis = "(" + arrayUsos.get(i).toString() + ")" ;
                if (uso.parentesis.equals(parentesis)) {
                   uso.observaciones = observaciones;
                }
            }
        }
        
    }
            
}
    