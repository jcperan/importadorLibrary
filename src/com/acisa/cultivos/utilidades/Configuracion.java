package com.acisa.cultivos.utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author v3rgu1
 */
public class Configuracion {

    Properties properties = null;

    /**
     * Configuration file name
     */
    public final static String CONFIG_FILE_NAME = "offline.properties";

    private Configuracion() {
        this.properties = new Properties();
        try {
            String path = new File(".").getCanonicalPath();
            properties.load(new FileInputStream(path + "/" + CONFIG_FILE_NAME));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Implementando Singleton
     *
     * @return
     */
    public static Configuracion getInstance() {
        return ConfiguracionHolder.INSTANCE;
    }

    private static class ConfiguracionHolder {

        private static final Configuracion INSTANCE = new Configuracion();
    }

    /**
     * Retorna la propiedad de configuracion solicitada
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

}
