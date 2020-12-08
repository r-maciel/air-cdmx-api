package com.example.aircdmxscraping.scraper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.aircdmxscraping.repositories.DelegacionesID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DelegacionesScraper extends AirScraper{
    private List<String> indiceCDMX(Document doc) {
        /* Índice CDMX */
        List<String> indiceCDMX = new ArrayList<>();
        String statusIndice = doc.selectFirst("#lateral_renglontresdatoscalidadaireahora  strong:nth-child(3)").text();
        if(statusIndice.equals("SIN COBERTURA") == true){
            return indiceCDMX;
        }
        else{
            String allText = doc.selectFirst("#lateral_renglontresdatoscalidadaireahora").text();
            allText = allText.replace("Índice de Calidad del Aire CDMX: ", "");
            allText = allText.replace(" ● ", "|-|");
            allText = allText.replace(" Contaminante: ", "|-|");
            allText = allText.replace(" Índice :", "");
            indiceCDMX = new ArrayList<>(Arrays.asList(allText.split("\\|-\\|")));

            /* Obtener el indice y agregarlo a la lista */
            Element element = doc.selectFirst("#lateral_renglontresdatoscalidadaireahora script"); 
            if(element != null){
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
                indice = indice.replace("<!-- Solo un ejemplo -->\n<script type=\"text/javascript\">\ndocument.write('", "");
                indice = indice.replace("document.write(unescape('", "");
                indice = indice.replace("')\n</script>\n", "");
                indiceCDMX.add(null);
                indiceCDMX.add(indice);
            }

            if(indiceCDMX.size() < 4){
                indiceCDMX.clear();
            }
        }

        return indiceCDMX;
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

    public DelegacionesScraper(int delegacion) {
        if(this.getStatusConnectionCode(this.url) == 200){
            Document doc = this.getHtmlDocument(this.url);
            this.checkId(doc);
            
            String delegacionID = DelegacionesID.ids.get(delegacion);
            String delegacionName = DelegacionesID.places.get(delegacion);

            doc = this.getHtmlDocumentDeleg(this.url, delegacionID);

            this.temperature = this.temperatura(doc);
            this.place = delegacionName;
            this.dataAS = this.indiceAS(doc);
            this.dataCDMX = this.indiceCDMX(doc);
        }
    }
}
