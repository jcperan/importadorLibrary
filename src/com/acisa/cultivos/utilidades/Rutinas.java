/**
 * Libreria con utilidades
 */
package com.acisa.cultivos.utilidades;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("deprecation")
public class Rutinas {

    /**
     * Devuelve el valor numerico de un objeto
     *
     * @param pObjeto
     * @return
     */
    public static Double Valor(Object pObjeto) {
        Double resultado = 0D;

        try {
            if (pObjeto != null) {
                String proceso = pObjeto.toString();
                proceso = proceso.replace(",", ".");
                resultado = Double.parseDouble(proceso);
            }
        } catch (Exception e) {
            resultado = 0D;
        }

        return resultado;
    }

    /**
     * Convierte un objeto Date en un String con format dd/MM/yyyy
     *
     * @param pFecha
     * @return
     */
    public static String FechaATexto(Date pFecha) {
        return FechaATexto(pFecha, "dd/MM/yyyy");
    }

    public static String FechaATexto(Date pFecha, String pFormato) {
        String resultado = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pFormato);
            resultado = sdf.format(pFecha);

        } catch (Exception e) {
        }

        return resultado;
    }

    /**
     * Genere un objeto Date a partir de los valores del Dia, mes y año
     *
     * @param pDia
     * @param pMes
     * @param pAno
     * @return
     */
    public static Date Fecha(Integer pDia, Integer pMes, Integer pAno) {
        Date resultado = null;
        Calendar cal = Calendar.getInstance();

        try {
            if (pDia != null && pDia != 0) {
                cal.set(pAno, pMes - 1, pDia);

                resultado = cal.getTime();
            }
        } catch (Exception e) {
        }

        return resultado;
    }

    /**
     * Obtiene un objeto Date a partir de un String en formato dd/MM/yyyy
     *
     * @param pFecha
     * @return
     */
    public static Date Fecha(String pFecha) {
        return Fecha(pFecha, "dd/MM/yyyy");
    }

    /**
     * Obtiene un objeto Date a partir de un String con formato
     *
     * @param pFecha
     * @param pFormato
     * @return
     */
    public static Date Fecha(String pFecha, String pFormato) {
        Date resultado = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pFormato);
            resultado = sdf.parse(pFecha);

        } catch (Exception e) {
        }

        return resultado;
    }

    /**
     * Obtiene un String a partir de cualquier objeto
     *
     * @param pObjeto
     * @return
     */
    public static String CStr(Object pObjeto) {
        String resultado = "";

        try {
            if (pObjeto != null) {
                resultado = pObjeto.toString();
            }
        } catch (Exception e) {
            LogExcepcion(e);
            resultado = "";
        }

        return resultado;
    }

    /**
     * Formatea un objeto ENTRADA seg��n el FORMATO especificado
     *
     * @param ENTRADA - El objeto a formatear
     * @param FORMATO - El formato de salida
     * @return
     */
    public static String FORMATO_NUMERO(Object ENTRADA, String FORMATO) {
        DecimalFormat fn = new DecimalFormat(FORMATO);

        return fn.format(Double.valueOf(ENTRADA.toString()));
    }

    /**
     * Formatea un objeto ENTRADA en un FORMATO de fecha
     *
     * @param ENTRADA
     * @param FORMATO
     * @return
     */
    public static String FORMATO_FECHA(Object ENTRADA, String FORMATO) {

        if (ENTRADA == null) {
            return "";
        }

        SimpleDateFormat fn = new SimpleDateFormat(FORMATO, Locale.getDefault());
        String FFECHA;

        if (ENTRADA.toString().length() > 6) {
            if (ENTRADA.toString().substring(2, 3).equals("-")) {
                FFECHA = ENTRADA.toString();
            } else {
                FFECHA = fn.format(ENTRADA);
            }
        } else {
            FFECHA = ENTRADA.toString();
        }

        Date AFECHA = new Date();
        fn = new SimpleDateFormat(FORMATO, Locale.getDefault());

        try {
            if (FFECHA.length() == 2) {
                FFECHA = FFECHA + FORMATO_FECHA(AFECHA, "dd-MM-yyyy").substring(2);
            }
            if (FFECHA.length() == 4) {
                FFECHA = FFECHA.substring(0, 2) + "-" + FFECHA.substring(2)
                        + FORMATO_FECHA(AFECHA, "dd-MM-yyyy").substring(5);
            }
            if (FFECHA.length() == 6) {
                FFECHA = FFECHA.substring(0, 2) + "-" + FFECHA.substring(2) + "-" + FFECHA.substring(4);
            }

            String SFECHA = fn.format(FFECHA);

            return SFECHA;
        } catch (Exception e) {
            return FFECHA;
        }
    }

    public static void LogExcepcion(Exception pExcepcion) {
        if (pExcepcion != null) {
            try {
                String mensaje;

                mensaje = "ERROR: "
                        + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

                // Metodo
                StackTraceElement[] pila = Thread.currentThread().getStackTrace();
                for (int i = 0; i < pila.length; ++i) {
                    if (pila[i].getMethodName() == "LogExcepcion" && i + 1 < pila.length) {
                        mensaje += "  en " + pila[i + 1].getClassName() + "." + pila[i + 1].getMethodName() + "():";

                        break;
                    }
                }

                // Causa
                if (pExcepcion.getCause() != null) {
                    mensaje += "  [CAUSA: " + pExcepcion.getCause().toString() + "]";
                }

                // Mensaje
                mensaje += "  " + pExcepcion.getMessage();

                System.out.println(mensaje);
            } catch (Exception ex) {
                System.out.println("ERROR: " + pExcepcion.getMessage());
            }
        } else {
            System.out.println("ERROR desconocido  (Exception es null)");
        }
    }
    
    public static void MuestraMensaje(String pMensaje, Exception e) {
        System.out.println(new Date() + " - " + pMensaje);
    }

}
