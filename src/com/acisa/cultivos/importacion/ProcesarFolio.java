/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.importacion;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author jcperan
 */
public class ProcesarFolio {

    private RegistroFitosanitario registro = new RegistroFitosanitario();

    public RegistroFitosanitario getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroFitosanitario registro) {
        this.registro = registro;
    }
    
    private List<String> listaDeCultivos = new ArrayList<String>();
    private List<String> listaDePlagas = new ArrayList<String>();
    
    public ProcesarFolio() {
        
    }
    
    /*
    Obtener datos del PDF obtenido
    */
    public ProcesarFolio(Integer numeroRegistro, DocumentoPDF documentoPDF, List<String> listaDeCultivos, List<String> listaDePlagas) {
        
        this.listaDeCultivos = listaDeCultivos;
        this.listaDePlagas = listaDePlagas;
        
        String documento = documentoPDF.getFolioPDF();
        // String documento = documentoPDF.getNuevoPDF();
        
        registro.setNumeroRegistro(numeroRegistro);
        registro.setNombreComercial(ObtenerDato("Nombre Comerci", documento));
        
        registro.setTitular(ObtenerSiguienteDato("Titular", documento));
        registro.setFabricante(ObtenerSiguienteDato("Fabricante", documento));
        
        registro.setRegMagrama(ObtenerDato("mero de autoriza", documento));
        registro.setComposicion(ObtenerDato("Composici", documento));
        registro.setTipoFuncion(ObtenerDato("Tipo Func", documento));
        registro.setPresentacion(ObtenerSiguienteDato("Presentac", documento));
        registro.setOrdenacion(ObtenerOrdenacion(registro.getComposicion()));
        
        String presentacion = ObtenerSiguienteDato("Tipo de Envase", documento);
        if (presentacion=="") presentacion=ObtenerSiguienteDato("Presentaci", documento);
        if (presentacion=="") presentacion=ObtenerSiguienteDato("Capacidad", documento);
        registro.setPresentacion(presentacion);
        registro.setOrdenacion(ObtenerOrdenacion(registro.getComposicion()));

        registro.setFechaInscripcion(FormatoFecha(ObtenerDato("cha de Inscripci", documento)));
        registro.setFechaCaducidad(FormatoFecha(ObtenerDato("cha de Caducidad", documento)));
        if (registro.getFechaCaducidad() == null) registro.setFechaCaducidad(FormatoFecha(ObtenerDato("cha limite de Ve", documento)));
        
        // registro.setListaUsos(ObtenerUsos(documentoPDF.getNuevoPDF(), documentoPDF.getTablePDF()));
        registro.setListaUsos(ObtenerUsos(documento));
        
        int nada;
        nada = 0;
    }
    
    /**
     * Dvuelve el valor encontrado para el texto a buscar
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

    private RegistroUsosAutorizados anterior;
    private RegistroUsosAutorizados actual;
    int index = 0;
    
    private List<RegistroUsosAutorizados> ObtenerUsos(String documento) {
        
        boolean swUsos = false;
        String documentoUsos = "";
        List<RegistroUsosAutorizados> listaUsos;
        listaUsos = new ArrayList<>();
        
        StringTokenizer tokens = new StringTokenizer(documento);
        while (tokens.hasMoreTokens()) {

            String primero = tokens.nextToken("\n");
            if (primero.contains("Plazos de Seguridad")) break;
            if (primero.contains("Usos y dosis")) swUsos = true;
            if (swUsos) documentoUsos = documentoUsos + "\n" + primero;

        }
        
        anterior = new RegistroUsosAutorizados();
        actual = new RegistroUsosAutorizados();
        index = 0;

        String datos[];
        String dato;
        
        String buscar;
        
        String anteriorCultivo = "";
        String anteriorPlaga = "";
        
        tokens = new StringTokenizer(documentoUsos);
        while (tokens.hasMoreTokens()) {
            
            String primero = tokens.nextToken("\n");
            
            // -----------------------------------------------------------------
            // buscar cultivo en linea -----------------------------------------
            // -----------------------------------------------------------------
            String swCultivo = "";
            Iterator iter1 = listaDeCultivos.iterator();
            while (iter1.hasNext()) {
                swCultivo = iter1.next().toString();
                if (primero.toUpperCase().contains(swCultivo.toUpperCase())) {
                    break;
                } else {
                    String nuevoCultivo = anteriorCultivo + primero.trim();
                    if (nuevoCultivo.toUpperCase().contains(swCultivo.toUpperCase())) {
                        primero = nuevoCultivo;
                        break;
                    } else {
                        String[] compuesta = swCultivo.split(" ");
                        if (compuesta.length > 1) {                            
                            for (int i=compuesta.length-1;i>0;i--) {
                                String busqueda = "";
                                for (int j=0;j<=i;j++) {
                                    busqueda += compuesta[j] + " ";
                                }
                            }
                        }
                        swCultivo = "";
                    }
                }
            }
            
            if (swCultivo.equals("")) anteriorCultivo = primero; else anteriorCultivo = "";
            if (!primero.substring(0,swCultivo.length()).toUpperCase().equals(swCultivo.toUpperCase())) swCultivo = "";

            List<String> listaPlagasEncontradas = new ArrayList<String>();
            
            // -----------------------------------------------------------------
            // buscar plaga en linea -------------------------------------------
            // -----------------------------------------------------------------
            String swPlaga = "";
            Iterator iter2 = listaDePlagas.iterator();
            while (iter2.hasNext()) {
                swPlaga = iter2.next().toString();
                if (primero.toUpperCase().contains(swPlaga.toUpperCase())) {
                    listaPlagasEncontradas.add(swPlaga);
                } else {
                    String nuevaPlaga = anteriorPlaga + primero.trim();
                    if (nuevaPlaga.toUpperCase().contains(swPlaga.toUpperCase())) {
                        primero = nuevaPlaga;
                        listaPlagasEncontradas.add(swPlaga);
                    } else {
                        swPlaga = "";
                    }
                }
            }

            // -----------------------------------------------------------------
            // Selecciona la plaga de mayor longitud encontrada ----------------
            // -----------------------------------------------------------------
            if (listaPlagasEncontradas.size()>0) {
                for (String actual : listaPlagasEncontradas) {
                    if (primero.toUpperCase().contains(actual.toUpperCase())) {
                        if (actual.length() > swPlaga.length()) swPlaga = actual;
                    }
                }
            }
            
            if (swPlaga.equals("")) anteriorPlaga = primero; else anteriorPlaga = "";
            
            // -----------------------------------------------------------------
            // Control para cuando hay cultivo y el resto seguido --------------
            // -----------------------------------------------------------------
            if (!swCultivo.equals("")) {
                if (primero.substring(0,swCultivo.length()).toUpperCase().equals(swCultivo.toUpperCase())) {
                    if (primero.length()>swCultivo.length() + 1) {
                        AgregarCultivo(listaUsos, swCultivo);
                        primero = primero.substring(swCultivo.length() + 1);
                    }
                }
            }

            // -----------------------------------------------------------------
            // Control cuando va todo seguido a partir de la plaga -------------
            // -----------------------------------------------------------------
            if (!swPlaga.equals("")) {
                if (primero.length()>=swPlaga.length()) {
                    if (primero.substring(0,swPlaga.length()).toUpperCase().equals(swPlaga.toUpperCase())) {
                        if (primero.length()>swPlaga.length() + 1) {
                            if (actual.plaga.equals("")) {
                                actual.setPlaga(swPlaga);
                            } else {
                                AgregarCultivo(listaUsos, actual.cultivo);
                                actual.setPlaga(swPlaga);
                            }
                            primero = primero.substring(swPlaga.length() + 1);
                            actual.dosificacion = primero;
                            primero = "";
                            swPlaga = "";
                        }
                    } else {
                        // System.out.println(primero);
                    }
                } else {
                    Integer posicion = primero.indexOf(swPlaga);
                    if (posicion>=0) {
                        if (actual.plaga.equals("")) {
                            actual.setPlaga(swPlaga);
                        } else {
                            actual.observaciones = primero.substring(0, posicion-1);
                            AgregarCultivo(listaUsos, actual.cultivo);
                            actual.setPlaga(swPlaga);
                        }
                        primero = primero.substring(posicion + swPlaga.length());
                        actual.dosificacion = primero;
                        primero = "";
                        swPlaga = "";
                    }
                }
            }
            
            datos = primero.split(" ");
            
            for (int i = 0; i < datos.length; i++) {
                dato = datos[i];
                buscar = dato.replace("\t","");

                if (!swCultivo.equals("")) {
                    AgregarCultivo(listaUsos, swCultivo);
                    i=datos.length;
                }
                
                if (!swPlaga.equals("")) {
                    if (!actual.plaga.equals("")) {
                        AgregarCultivo(listaUsos, actual.cultivo);
                        actual.dosificacion = anterior.dosificacion;
                        actual.observaciones = anterior.observaciones;
                    }
                    actual.setPlaga(swPlaga);
                    i=datos.length;
                }
                
                if (swCultivo.equals("") && swPlaga.equals("")) {
                    if ((!actual.cultivo.equals("")) && (!actual.plaga.equals(""))) {
                        if (actual.dosificacion.equals("")) {
                            actual.dosificacion = buscar;
                            for (int j=i+1; j<datos.length; j++) actual.dosificacion = actual.dosificacion + " " + datos[j];
                        } else {
                            actual.observaciones = actual.observaciones + " " + primero;
                        }
                        i=datos.length;
                    }
                }
            }
            
            // System.out.print("");
        }
        
        AgregarCultivo(listaUsos, "");
        // this.ObtenerUsosTabla(listaUsos, documento);
        this.PlazoSeguridad(listaUsos, documento);
        return listaUsos;
        
    }
    
    private void ObtenerUsosTabla(List<RegistroUsosAutorizados> listaUsos, String tabla) {
        
        Integer indiceUsos = 0;
        
        StringTokenizer tokens = new StringTokenizer(tabla);
        while (tokens.hasMoreTokens()) {

            String lineaActual = tokens.nextToken("\n");
            
            String tablaPlaga = lineaActual.substring(0,20);
            
            Iterator iterator = listaDePlagas.iterator();
            while (iterator.hasNext()) {
                String swPlaga = iterator.next().toString();
                if (tablaPlaga.toUpperCase().equals(swPlaga.toUpperCase())) {
                    
                }
            }
            
        
        }
            
    }
    

    private void AgregarCultivo(List<RegistroUsosAutorizados> listaUsos, String cultivo) {
        
        if (!actual.cultivo.equals("")) {
            listaUsos.add(new RegistroUsosAutorizados(actual.cultivo, actual.plaga, actual.plazo, actual.parentesis, (actual.dosificacion + "                    ").substring(0,20).trim(), actual.observaciones.replace("\t","") ));
            index = listaUsos.size();
        }
        
        anterior = actual;
        actual = new RegistroUsosAutorizados();
        actual.cultivo = cultivo;

    }

    List<String> listaCultivos = new ArrayList<>();

    private void PlazoSeguridad(List<RegistroUsosAutorizados> listaUsos, String documento) {
        
        boolean swPlazos = false;
        String documentoPlazos = "";
        listaCultivos.clear();
        
        StringTokenizer tokens = new StringTokenizer(documento);
        while (tokens.hasMoreTokens()) {

            String primero = tokens.nextToken("\n");
            if (primero.contains("Fecha de Liberaci")) break;
            if (primero.contains("Condiciones Generales")) break;
            if (primero.startsWith("Excepciones")) break;
            if (primero.contains("Plazos de Seguridad")) swPlazos = true;
            if (swPlazos) documentoPlazos = documentoPlazos + "\n" + primero;

        }
        
        tokens = new StringTokenizer(documentoPlazos, "\t");
        while (tokens.hasMoreTokens()) {

            String primero = tokens.nextToken("\n");
            Integer dias = this.valor(primero.trim());
            
            if (primero.contains(",")) {
                String datos1[] = primero.split(",");
                for (String dato1 : datos1) {
                    String dato = dato1.trim();
                    if (dato.contains(" ")) {
                        String datos2[] = dato.split(" ");
                        dato = ""; 
                        
                        for (int i=0; i<datos2.length-1; i++) {
                            if (i>0) dato = dato + " ";
                            dato = dato + datos2[i];
                        }
                        dias = valor(datos2[datos2.length-1]);
                    }

                    if (dato.length()>1) {
                        for (RegistroUsosAutorizados registroLeido : listaUsos) {
                            if (registroLeido.cultivo.contains(dato)) {
                                listaCultivos.add(registroLeido.cultivo);
                            }
                        }
                    }
                }
            } else {
                if (primero.trim().contains(" ")) {
                    String datos2[] = primero.split(" ");
                    String dato = "";
                    for (String dato1 : datos2) {
                        dato = dato + " " + dato1.trim();
                        String plaz = datos2[datos2.length-1];
                        if (!dato.equals("")) {
                            for (RegistroUsosAutorizados registroLeido : listaUsos) {
                                if (dato.contains(registroLeido.cultivo)){
                                    registroLeido.setPlazo(plaz);
                                }
                            }
                        }
                    }
                }
            }

            if ((primero.contains("N.P")) || (primero.contains("NP")) || (dias>0)) {
                this.asignarPlazo(listaUsos, dias.toString());
            }
                        
        }
    }

    private Integer valor(String entrada) {
        
        Integer salida;
        
        try {
            salida = new Integer(entrada);
        } catch (NumberFormatException e) {
            salida = new Integer("0");
        }

        return salida;
        
    }
    
    private void asignarPlazo(List<RegistroUsosAutorizados> listaUsos, String plazo) {

        for (String cultivo : listaCultivos ) {
            for (RegistroUsosAutorizados RegistroActual : listaUsos) {
                if (RegistroActual.cultivo.contains(cultivo)) {
                    RegistroActual.setPlazo(plazo);
                }
            }
        }
        listaCultivos.clear();
    }
    
}
