package com.example.aircdmxscraping.api.partials;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_ABSENT)
public class IndiceCDMX {
    private final String airQuality;
    private final String pollutants;
    private final String risk;
    private final String station;
    private final String rating;
    private final String message;

    public IndiceCDMX(List<String> data) {
        if(data.isEmpty() == true){
            this.airQuality = null;
            this.pollutants = null;
            this.risk = null;
            this.station = null;
            this.rating = null;
            this.message = "SIN COBERTURA";
        }
        else{
            this.airQuality = data.get(0);
            this.pollutants = data.get(2);
            this.risk = data.get(1);
            this.station = data.get(3);
            this.rating = data.get(4);
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

    public String getStation() {
        return station;
    }

    public String getRating() {
        return rating;
    }

    public String getMessage() {
        return message;
    } 
}
