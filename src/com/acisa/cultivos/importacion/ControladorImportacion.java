package com.acisa.cultivos.importacion;

import com.acisa.cultivos.modelos.Cultivos;
import com.acisa.cultivos.modelos.Fabricantes;
import com.acisa.cultivos.modelos.Plagas;
import com.acisa.cultivos.modelos.Productos;
import com.acisa.cultivos.modelos.Titulares;
import com.acisa.cultivos.modelos.UsosAutorizados;
import com.acisa.cultivos.objetos.ObjCultivos;
import com.acisa.cultivos.objetos.ObjFabricantes;
import com.acisa.cultivos.objetos.ObjPlagas;
import com.acisa.cultivos.objetos.ObjProductos;
import com.acisa.cultivos.objetos.ObjTitulares;
import com.acisa.cultivos.objetos.ObjUsosAutorizados;
import com.acisa.cultivos.objetos.Persistencia;
import com.acisa.cultivos.utilidades.Rutinas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

public class ControladorImportacion extends Persistencia {

    public ControladorImportacion() {

    }

    private Integer contador = 0;

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    private Integer importados = 0;

    public Integer getImportados() {
        return importados;
    }

    public void setImportados(Integer importados) {
        this.importados = importados;
    }

    private Integer minutos = 0;

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    private Integer segundos = 0;

    public Integer getSegundos() {
        return segundos;
    }

    public void setSegundos(Integer segundos) {
        this.segundos = segundos;
    }

    private Integer porcentaje = 0;
    public Integer getPorcentaje() {
        porcentaje++;
        if (porcentaje>100) porcentaje=0;
        return porcentaje;
    }
    
    public void listenerPoll() {
        
    }

    Integer inicio;

    public void ejecutarImportacion() {

        String[] loggers = {"org.apache.pdfbox.util.PDFStreamEngine",
            "org.apache.pdfbox.pdmodel.font.PDSimpleFont",
            "org.apache.pdfbox.pdmodel.font.PDFont",
            "org.apache.pdfbox.pdmodel.font.FontManager",
            "org.apache.pdfbox.pdfparser.PDFObjectStreamParser"};
        for (String logger : loggers) {
            org.apache.log4j.Logger logpdfengine = org.apache.log4j.Logger.getLogger(logger);
            logpdfengine.setLevel(org.apache.log4j.Level.OFF);
        }

        List<String> listaCultivos = new ArrayList<String>();
        List<String> listaPlagas = new ArrayList<String>();
        
        ObtenerPlagas plagas = new ObtenerPlagas();
        plagas.procesarPlagas(listaPlagas);
        plagas = null;
        System.gc();

        ObtenerCultivos cultivos = new ObtenerCultivos();
        cultivos.procesarCultivos(listaCultivos);
        cultivos = null;
        System.gc();

        ObjProductos producto = new ObjProductos();
        producto.getListaProductos();
        inicio = (new Date().getHours() * 3600) + (new Date().getMinutes() * 60) + new Date().getSeconds();

        System.out.println("---------------------------------------------------------------");
        System.out.println(new Date().toString() + " * Inicio del Proceso ESPECIALES");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < 1999; i++) {
            // for (int i = 0; i < 1; i++) {
            contador++; System.gc();
            DocumentoPDF documentoPDF = new DocumentoPDF();
            if (documentoPDF.obtenerPDF("ES-" + String.format("%1$05d", i))) {
                importados++;
                this.CalcularElapse(i);
                if (documentoPDF.isFolio()) {
                    ProcesarFolio proceso = new ProcesarFolio(i, documentoPDF, listaCultivos, listaPlagas);
                    this.ProcesaRegistro(i, proceso.getRegistro());
                    proceso = null;
                } else {
                    ProcesarRegistro proceso = new ProcesarRegistro(i, documentoPDF.getTextoPDF());
                    this.ProcesaRegistro(i, proceso.getRegistro());
                    proceso = null;
                }
            }
            documentoPDF = null;
            System.gc();
        }

        System.out.println("---------------------------------------------------------------");
        System.out.println(new Date().toString() + " * Inicio del Proceso NORMALES");
        System.out.println("---------------------------------------------------------------");
        for (int i = 11000; i < 40000; i++) {
            contador++; System.gc();
            DocumentoPDF documentoPDF = new DocumentoPDF();
            if (documentoPDF.obtenerPDF(String.format("%1$05d", i))) {
                importados++;
                this.CalcularElapse(i);
                if (documentoPDF.isFolio()) {
                    ProcesarFolio proceso = new ProcesarFolio(i, documentoPDF, listaCultivos, listaPlagas);
                    this.ProcesaRegistro(i, proceso.getRegistro());
                    proceso = null;
                } else {
                    ProcesarRegistro proceso = new ProcesarRegistro(i, documentoPDF.getTextoPDF());
                    this.ProcesaRegistro(i, proceso.getRegistro());
                    proceso = null;
                }
            }
            documentoPDF = null;
            System.gc();
        }

    }

    public void CalcularElapse(Integer i) {

        Integer elapse = ((new Date().getHours() * 3600) + (new Date().getMinutes() * 60) + new Date().getSeconds()) - inicio;
        minutos = elapse / 60;
        segundos = elapse - (minutos * 60);

        System.out.println(new Date().toString() + " - " + Rutinas.FORMATO_NUMERO(contador, "00000") + " "
                + Rutinas.FORMATO_NUMERO(minutos, "000") + ":" + Rutinas.FORMATO_NUMERO(segundos, "00")
                + " - NUMERO REGISTRO  = " + Integer.toString(i));

    }

    private void ProcesaRegistro(Integer numeroRegistro, RegistroFitosanitario registro) {

        em = entityManager();
        try {
            Query consulta = em.createQuery("SELECT p FROM Productos p where p.numeroRegistro = :numero ");
            consulta.setParameter("numero", numeroRegistro);
            consulta.setMaxResults(1);
            Productos producto = new Productos();
            try {
                producto = (Productos) consulta.getSingleResult();
            } catch (Exception e) {
                producto = new Productos();
                producto.setNumeroRegistro(numeroRegistro);
                em.getTransaction().begin();
                em.persist(producto);
                em.flush();
                em.getTransaction().commit();
            }

            ObjUsosAutorizados usos = new ObjUsosAutorizados();
            usos.EliminarUsos(producto.getCodigo());

            em.getTransaction().begin();

            producto.setNombre(registro.getNombreComercial());
            producto.setMateriaActiva(registro.getComposicion());
            producto.setPresentacion(registro.getPresentacion());
            producto.setAmbitos(registro.getTipoFuncion());
            producto.setOrden(registro.getOrdenacion());

            producto.setFechaInscripcion(registro.getFechaInscripcion());
            producto.setFechaCaducidad(registro.getFechaCaducidad());

            ObjTitulares titular = new ObjTitulares();
            Titulares registroTitular = titular.buscarTitular(registro.getTitular());
            if (registroTitular == null) {
                registroTitular = new Titulares();
                registroTitular.setNombre(registro.getTitular());
                titular.GrabarTitular(registroTitular);
                registroTitular = titular.buscarTitular(registro.getTitular());
            }
            if (registroTitular != null) producto.setTitular(registroTitular.getCodigo());

            ObjFabricantes fabricante = new ObjFabricantes();
            Fabricantes registroFabricante = fabricante.buscarFabricante(registro.getFabricante());
            if (registroFabricante == null) {
                registroFabricante = new Fabricantes();
                registroFabricante.setNombre(registro.getFabricante());
                fabricante.GrabarFabricante(registroFabricante);
                registroFabricante = fabricante.buscarFabricante(registro.getFabricante());
            }
            if (registroFabricante != null) producto.setFabricante(registroFabricante.getCodigo());

            producto.setRegMagrama(registro.getRegMagrama().replace(".", ""));
            producto.setFechaActualizacion(new Date());

            this.IntegraUsos(producto, registro);

            em.merge(producto);
            em.flush();
            
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR al procesar producto " + numeroRegistro.toString() + "\n" + e.getMessage());
        } finally {
            em.close();
        }

    }

    private void IntegraUsos(Productos producto, RegistroFitosanitario registro) {

        for (RegistroUsosAutorizados uso : (List<RegistroUsosAutorizados>) registro.getListaUsos()) {

            ObjUsosAutorizados objUsosAutorizados = new ObjUsosAutorizados();

            ObjCultivos objCultivos = new ObjCultivos();
            Cultivos registroCultivo = objCultivos.buscarCultivos(uso.cultivo);

            /*
             * Graba nuevo cultivo si no existe
             */
            if (registroCultivo == null) {
                registroCultivo = new Cultivos();
                registroCultivo.setNombre(uso.cultivo);
                objCultivos.GrabarCultivo(registroCultivo);
                registroCultivo = objCultivos.buscarCultivos(uso.cultivo);
            }

            /*
             *  Graba Nueva Plaga si no existe
             */
            ObjPlagas objPlagas = new ObjPlagas();
            Plagas registroPlaga = objPlagas.buscarPlaga(uso.plaga);
            if (registroPlaga == null) {
                registroPlaga = new Plagas();
                registroPlaga.setNombre(uso.plaga);
                objCultivos.GrabarPlaga(registroPlaga);
                registroPlaga = objPlagas.buscarPlaga(uso.plaga);
            }

            if (registroCultivo != null) {
                if (registroPlaga != null) {
                    if (!objCultivos.ObtenerCultivoPlaga(registroCultivo.getCodigo(),
                            registroPlaga.getCodigo())) {
                        objCultivos.GrabarCultivoPlaga(registroCultivo.getCodigo(),
                                registroPlaga.getCodigo());
                    }
                    boolean nuevoUsoAutorizado = false;
                    UsosAutorizados registroUsos = new UsosAutorizados();
                    registroUsos = objUsosAutorizados.buscarUsos(producto.getCodigo(), registroCultivo.getCodigo(), registroPlaga.getCodigo());
                    if (registroUsos == null) {
                        registroUsos = new UsosAutorizados();
                        nuevoUsoAutorizado = true;
                    }
                    registroUsos.setProducto(producto.getCodigo());
                    registroUsos.setCultivo(registroCultivo.getCodigo());
                    registroUsos.setPlaga(registroPlaga.getCodigo());
                    if (!uso.dosificacion.equals("")) {
                        if (!uso.dosificacion.substring(0, 1).equals("(")) {
                            if (uso.dosificacion.contains("-")) {
                                String dato1 = uso.dosificacion.substring(0, uso.dosificacion.indexOf("-")).replace(",", ".");
                                if (Rutinas.Valor(dato1) != 0) registroUsos.setDosis(new Double(dato1));
                            } else if (uso.dosificacion.contains(" ")) {
                                String dato2 = uso.dosificacion.substring(0, uso.dosificacion.indexOf(" ")).replace(",", ".");
                                if (Rutinas.Valor(dato2) != 0) registroUsos.setDosis(new Double(dato2));
                            } else {
                                String dato2 = uso.dosificacion.replace(",", ".");
                                if (Rutinas.Valor(dato2) != 0) registroUsos.setDosis(new Double(dato2));
                            }
                        } else {
                            String dato3 = uso.dosificacion.replace(",", ".");
                            if (Rutinas.Valor(dato3) != 0) registroUsos.setDosis(new Double(dato3));
                        }
                    }
                    if (Rutinas.Valor(uso.plazo) == 0) {
                        registroUsos.setPlazoSeguridad(new Integer("0"));
                    } else {
                        registroUsos.setPlazoSeguridad((Rutinas.Valor(uso.plazo.trim())).intValue());
                    }
                    registroUsos.setAutorizado(true);
                    registroUsos.setDosificacion(uso.dosificacion);
                    registroUsos.setObservaciones(uso.observaciones);
                    if (!registroPlaga.getNombre().equals("")) {
                        if (nuevoUsoAutorizado) {
                            objUsosAutorizados.GrabarUsoAutorizado(registroUsos);
                        } else {
                            objUsosAutorizados.ActualizarUsoAutorizado(registroUsos);
                        }
                    }
                } else {
                    System.out.println("Error al localizar Plaga [" + uso.plaga + "]");
                }
            } else {
                System.out.println("Error al localizar cultivo [" + uso.cultivo + "]");
            }
        }
    }

}
