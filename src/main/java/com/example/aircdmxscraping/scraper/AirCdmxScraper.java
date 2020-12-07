package com.example.aircdmxscraping.scraper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AirCdmxScraper {
    private final String url = "http://www.aire.cdmx.gob.mx/default.php?opc=%27YqBhnmI=%27";

    private int getStatusConnectionCode(String url) {
        Response response = null;
        
        try {
        response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
        System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    private Document getHtmlDocumentDeleg(String url, String delegacion) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").data("delegaciones", delegacion).post();
            } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
            }
        return doc;
    }

    private Document getHtmlDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
            } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
            }
        return doc;
    }

    public AirCdmxScraper() {
        if(this.getStatusConnectionCode(this.url) == 200){
            /* CDMX */
            Document doc = getHtmlDocument(this.url);
            String temperatura = doc.selectFirst("#lateral_renglonunodatoscalidadaireahora #lateral_calidadairetemperaturaahora").text();
            
            /* Índice AIRE Y SALUD */
            String allText = doc.selectFirst("#lateral_renglondosdatoscalidadaireahora").text();

            allText = allText.replace("Índice AIRE Y SALUD: ", "");
            allText = allText.replace(" ● Contaminante(s): ", "|-|");
            allText = allText.replace(" Riesgo: ", "|-|");
            allText = allText.replace(" Recomendaciones para: - Grupo Sensibles ", "|-|");
            allText = allText.replace(" - Para toda la población ", "|-|");

            /* Datos separados */
            String data1[] = allText.split("\\|-\\|");

            /* Índice CDMX */
            String statusIndice = doc.selectFirst("#lateral_renglontresdatoscalidadaireahora  strong:nth-child(3)").text();
            if(statusIndice == "SIN COBERTURA"){
                System.out.println("Text: " + statusIndice);
            }
            else{
                allText = doc.selectFirst("#lateral_renglontresdatoscalidadaireahora").text();
                allText = allText.replace("Índice anterior: ", "");
                allText = allText.replace(" ● ", "|-|");
                allText = allText.replace("Contaminante: ", "|-|");
                allText = allText.replace(" Índice : Estación: ", "|-|");
                List<String> data2 = new ArrayList<>(Arrays.asList(allText.split("\\|-\\|")));

                Element element = doc.selectFirst("#lateral_renglontresdatoscalidadaireahora script"); 
                String indice = "";     
                for (DataNode node : element.dataNodes()) {
                    indice = node.getWholeData();
                }  
                indice = indice.replace("document.write(unescape('", "");
                indice = indice.replace("'))", "");
                try {
                    indice = URLDecoder.decode(indice, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    System.out.println("Excepción al obtener indice" + e.getMessage());
                }
                indice = indice.substring(73, 75);
                data2.add(indice);
            }

            /* Obtener indices */
            Elements delegaciones = doc.select("#contenedorinformacion02 div form select option");

            String delegacionesArray[] = new String [16];
            
            for(int i = 0; i < 16; i++){
                Element delegacion = delegaciones.get(1 + i);
                delegacionesArray[i] = delegacion.val(); 
            }

            /* Prueba para una delegación */

        }
    }


}
