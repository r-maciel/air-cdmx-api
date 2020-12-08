package com.example.aircdmxscraping.scraper;

/* System packages */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Scraping packages*/
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* Time packages */
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class AirScraper {
    protected final String url = "http://www.aire.cdmx.gob.mx/default.php?opc=%27YqBhnmI=%27";
    protected String temperature;
    protected List<String> dataAS = new ArrayList<>();
    protected List<String> dataCDMX = new ArrayList<>();
    protected String currentDate;

    protected void setCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime cDate = LocalDateTime.now();
        this.currentDate = dtf.format(cDate);
    }

    protected int getStatusConnectionCode(String url) {
        Response response = null;
        
        try {
        response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
        System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    protected Document getHtmlDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
            } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
            }
        return doc;
    }

    protected List<String> getIdDelegacion(Document doc) {
        /* Obtener indices */
        Elements delegaciones = doc.select("#contenedorinformacion02 div form select option");

        List<String> delegacionesArray = new ArrayList<>(); 
        
        for(int i = 0; i < 16; i++){
            Element delegacion = delegaciones.get(1 + i);
            delegacionesArray.add(delegacion.val()); 
        }

        return delegacionesArray;
    }

    protected List<String> indiceAS(Document doc) {
        /* Índice AIRE Y SALUD */
        String allText = doc.selectFirst("#lateral_renglondosdatoscalidadaireahora").text();

        allText = allText.replace("Índice AIRE Y SALUD: ", "");
        allText = allText.replace(" ● Contaminante(s): ", "|-|");
        allText = allText.replace(" Riesgo: ", "|-|");
        allText = allText.replace(" Recomendaciones para: - Grupo Sensibles ", "|-|");
        allText = allText.replace(" - Para toda la población ", "|-|");

        /* Datos separados */
        List<String> indiceAS = new ArrayList<>(Arrays.asList(allText.split("\\|-\\|")));
        return indiceAS;
    }

    protected String temperatura(Document doc) {
        /* Temperatura */
        String temperatura = doc.selectFirst("#lateral_renglonunodatoscalidadaireahora #lateral_calidadairetemperaturaahora").text();
        return temperatura;
    }

    public AirScraper() {
        this.setCurrentDate();
    }

    public String getUrl() {
        return url;
    }

    public String getTemperature() {
        return temperature;
    }

    public List<String> getDataAS() {
        return dataAS;
    }

    public List<String> getDataCDMX() {
        return dataCDMX;
    }

    public String getCurrentDate() {
        return currentDate;
    }
    
}
