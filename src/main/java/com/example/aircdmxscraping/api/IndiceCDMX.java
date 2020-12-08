package com.example.aircdmxscraping.api;

import java.util.List;

public class IndiceCDMX {
    private final String airQuality;
    private final String pollutants;
    private final String risk;
    private final String station;
    private final String rating;

    public IndiceCDMX(List<String> data) {
        this.airQuality = data.get(0);
        this.pollutants = data.get(2);
        this.risk = data.get(1);
        this.station = data.get(3);
        this.rating = data.get(4);
    }

    public String getAirQuality() {
        return airQuality;
    }

    public String[] getPollutants() {
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

    
    
}
