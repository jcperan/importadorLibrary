package com.acisa.cultivos.utilidades;

/*
 * PDFTextParser.java
 * Author: S.Prasanna
 *
 */
import com.acisa.cultivos.importacion.Constantes;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfToText {

    public PdfToText() {

    }

    public String ConvertPdfToText(String ArchivoPDF) {

        String text = "";

        try {
            URL url = new URL("http://www.mapama.gob.es/agricultura/pags/fitos/registro/productos/pdf/" + ArchivoPDF + ".pdf");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            PDDocument pdd = PDDocument.load(is);
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            stripper.setWordSeparator("_");
            stripper.setArticleStart(Constantes.ARTICULO_START);
            stripper.setArticleEnd(Constantes.ARTICULO_END);
            stripper.setParagraphStart(Constantes.PARRAFO_START);
            stripper.setPageEnd(Constantes.PARRAFO_END);
            stripper.setLineSeparator(Constantes.SEPARADOR_LINEA);
            // stripper.setWordSeparator(" ");
            text = stripper.getText(pdd);
            pdd.close();
            is.close();
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }

        return text;

    }

}
