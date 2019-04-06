/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acisa.cultivos.importacion;

import java.io.InputStream;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author jcperan
 */
public class DocumentoPDF {

    static final String ARTICULO_START = ""; // "<<\r\n";
    static final String ARTICULO_END = ""; // ">>\r\n";

    static final String PARRAFO_START = ""; // "((\r\n";
    static final String PARRAFO_END = ""; // "))\r\n";

    static final String SEPARADOR_LINEA = "\n";
    static final String SEPARADOR_PALABRA = "\n";

    // <editor-fold defaultstate="collapsed" desc="Propiedades">
    private String textoPDF = "";
    private String folioPDF = "";
    private String nuevoPDF = "";
    private String tablePDF = "";
    
    private boolean folio = false;

    public String getTextoPDF() {
        return textoPDF;
    }

    public void setTextoPDF(String textoPDF) {
        this.textoPDF = textoPDF;
    }

    public String getFolioPDF() {
        return folioPDF;
    }

    public void setFolioPDF(String folioPDF) {
        this.folioPDF = folioPDF;
    }

    public String getNuevoPDF() {
        return nuevoPDF;
    }

    public void setNuevoPDF(String nuevoPDF) {
        this.nuevoPDF = nuevoPDF;
    }

    public String getTablePDF() {
        return tablePDF;
    }

    public void setTablePDF(String tablePDF) {
        this.tablePDF = tablePDF;
    }
    
    public boolean isFolio() {
        if (textoPDF.contains("SECRETARIA GENERAL DE AGRICULTURA")) {
            this.folio = true;
        } else {
            this.folio = false;
        }
        return folio;
    }

    public void setFolio(boolean folio) {
        this.folio = folio;
    }
// </editor-fold>

    /*
        Constructor de la Clase
     */
    public DocumentoPDF() {

    }

    public boolean obtenerPDF(String ArchivoPDF) {

        try {

            PermitirHttps permitir = new PermitirHttps();
            permitir.fixHttps();

            URL url = new URL("https://www.mapa.gob.es/agricultura/pags/fitos/registro/productos/pdf/" + ArchivoPDF + ".pdf");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            // InputStream is = url.openStream();

            PDDocument pdd = PDDocument.load(is);
            PDFTextStripper stripper = new PDFTextStripper();

            // genera formato para lectura datos folio
            stripper.setAddMoreFormatting(true);
            stripper.setShouldSeparateByBeads(true);
            stripper.setSortByPosition(false);
            
            stripper.setAverageCharTolerance(0.1f);

            stripper.setWordSeparator(SEPARADOR_PALABRA);
            stripper.setArticleStart(ARTICULO_START);
            stripper.setArticleEnd(ARTICULO_END);
            stripper.setParagraphStart(PARRAFO_START);
            stripper.setParagraphEnd("\t");
            stripper.setLineSeparator("\n");
            stripper.setWordSeparator("_");
            folioPDF = stripper.getText(pdd);

            // genera formato para lectura datos folio
            stripper.setAddMoreFormatting(true);
            stripper.setShouldSeparateByBeads(true);
            stripper.setSortByPosition(false);

            stripper.setWordSeparator("+++++++++");
            stripper.setArticleStart("ççççççççç");
            stripper.setArticleEnd("");
            stripper.setParagraphStart("\n");
            stripper.setParagraphEnd("");
            stripper.setLineSeparator("");
            nuevoPDF = stripper.getText(pdd);

            // genera formato para lectura datos texto anterior
            stripper.setAddMoreFormatting(false);
            stripper.setShouldSeparateByBeads(false);
            stripper.setSortByPosition(true);

            stripper.setWordSeparator(SEPARADOR_PALABRA);
            stripper.setArticleStart(ARTICULO_START);
            stripper.setArticleEnd(ARTICULO_END);
            stripper.setParagraphStart(PARRAFO_START);
            stripper.setParagraphEnd(PARRAFO_END);
            stripper.setLineSeparator(SEPARADOR_LINEA);
            stripper.setWordSeparator("_");
            textoPDF = stripper.getText(pdd);
            
            // PDFTextStripperByArea sts = new PDFTextStripperByArea();
            // sts.extractRegions(pdd.getPage(4));
            // List<String> listaRegiones = sts.getRegions();
            


            
            
            
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            pdfTextStripper.setAddMoreFormatting(true);
            pdfTextStripper.setShouldSeparateByBeads(true);
            pdfTextStripper.setSortByPosition(true);
            
            pdfTextStripper.setAverageCharTolerance(0.1f);

            pdfTextStripper.setWordSeparator(SEPARADOR_PALABRA);
            pdfTextStripper.setArticleStart("******");
            pdfTextStripper.setArticleEnd("******");
            pdfTextStripper.setParagraphStart("***");
            pdfTextStripper.setParagraphEnd("***");
            pdfTextStripper.setLineSeparator("\n");
            pdfTextStripper.setWordSeparator("_");
            // tablePDF = pdfTextStripper.getText(pdd);            

            pdd.close();
            is.close();
            return true;
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            return false;
        }

    }

}
