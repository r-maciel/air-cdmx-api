package com.example.aircdmxscraping.api.partials;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_ABSENT)
public class IndiceAS {
    private final String airQuality;
    private final String risk;
    private final String pollutants;
    private Recommendations recommendations;
    private String message;

    public IndiceAS(List<String> data) {
        if(data.isEmpty() == true){
            this.airQuality = null;
            this.pollutants = null;
            this.risk = null;
            this.recommendations = null;
            this.message = "SIN DATOS O EN MANTENIMIENTO";
        }
        else{
            this.airQuality = data.get(0);
            this.pollutants = data.get(1);
            this.risk = data.get(2);
            this.recommendations = new Recommendations(data.get(3), data.get(4));
            this.message = null;
        }
    }

    public String getAirQuality() {
        return airQuality;
    }

    public String[] getPollutants() {
        if(pollutants == null){
            return null;
        }
        return pollutants.split(",");
    }

    public String getRisk() {
        return risk;
    }

    public Recommendations getRecommendations() {
        return recommendations;
    }

    public String getMessage() {
        return message;
    }
    
}
