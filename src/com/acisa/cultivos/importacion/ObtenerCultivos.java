/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.importacion;

import com.acisa.cultivos.modelos.Cultivos;
import com.acisa.cultivos.objetos.ObjCultivos;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author jcperan
 */
public class ObtenerCultivos {

    public ObtenerCultivos() {

    }

    public void procesarCultivos(List<String> listaCultivos) {

        System.out.println("-------------------------------------------------------------");
        System.out.println(new Date() + " - Integracion Datos de Cultivos");
        System.out.println("-------------------------------------------------------------");
        
        Integer cuenta = 0;
        Integer proces = 0;
        
        ObjCultivos objCultivos = new ObjCultivos();
        listaCultivos.clear();
        
        List<Cultivos> lista = objCultivos.getConsultaCultivos();
        Iterator ic = lista.iterator();
        while (ic.hasNext()) {
            Cultivos cultivo = (Cultivos) ic.next();
            listaCultivos.add(cultivo.getNombre());
        }

        try {
            
            PermitirHttps permitir = new PermitirHttps();
            permitir.fixHttps();
            
            URL url = new URL("https://www.mapa.gob.es/es/agricultura/temas/sanidad-vegetal/productos-fitosanitarios/registro/productos/conaplipla.asp?ambUti=01");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "iso-8859-15"));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.toUpperCase().contains("<OPTION")) {
                    Integer desde = inputLine.indexOf(">") + 1;
                    Integer hasta = inputLine.substring(desde).indexOf("<");
                    if (hasta>0) {
                        cuenta++;
                        String buscar = inputLine.substring(desde, desde + hasta).trim();
                        Cultivos registroCultivos = objCultivos.buscarCultivos(buscar);
                        if (registroCultivos == null) {
                            registroCultivos = new Cultivos();
                            registroCultivos.setNombre(buscar);
                            objCultivos.GrabarCultivo(registroCultivos);
                            proces++;
                        }
                        listaCultivos.add(buscar);
                    }
                }
            }
            in.close();                        
        } catch (Exception e) {
            System.out.println(new Date() + " - " + e.getMessage());
            System.out.println("NO SE HAN PODIDO OBTENER LOS CULTIVOS");
        }

        System.out.println(new Date() + " - Procesados " + cuenta + " registros. Integrados " + proces);
        System.out.println(new Date() + " - Fin del Proceso");
        
    }

}
