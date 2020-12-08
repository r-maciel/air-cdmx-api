package com.example.aircdmxscraping.scraper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;/* 
import java.io.IOException; */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* 
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup; */
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;/* 
import org.jsoup.select.Elements; */

public class CdmxScraper extends AirScraper{
    private List<String> indiceCDMX(Document doc) {
        /* Índice CDMX */
        List<String> indiceCDMX = new ArrayList<>();
        String statusIndice = doc.selectFirst("#lateral_renglontresdatoscalidadaireahora  strong:nth-child(3)").text();
        if(statusIndice == "SIN COBERTURA"){
            System.out.println("Text: " + statusIndice);
        }
        else{
            String allText = doc.selectFirst("#lateral_renglontresdatoscalidadaireahora").text();
            allText = allText.replace("Índice anterior: ", "");
            allText = allText.replace(" ● ", "|-|");
            allText = allText.replace(" Contaminante: ", "|-|");
            allText = allText.replace(" Índice : Estación: ", "|-|");
            indiceCDMX = new ArrayList<>(Arrays.asList(allText.split("\\|-\\|")));

            /* Obtener el indice y agregarlo a la lista */
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
            indice = indice.replace("<!-- Solo un ejemplo -->\n<script type=\"text/javascript\">\ndocument.write('", "");
            indice = indice.replace("document.write(unescape('", "");
            indice = indice.replace("')\n</script>\n", "");
            indiceCDMX.add(indice);
        }

        return indiceCDMX;
    }

    public CdmxScraper() {
        if(this.getStatusConnectionCode(this.url) == 200){
            Document doc = this.getHtmlDocument(this.url);

            this.temperature = this.temperatura(doc);
            this.dataAS = this.indiceAS(doc);
            this.dataCDMX = this.indiceCDMX(doc);
            this.checkId(doc);
        }
    }
}
