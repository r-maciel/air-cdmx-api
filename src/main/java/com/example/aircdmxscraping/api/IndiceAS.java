package com.example.aircdmxscraping.api;

import java.util.List;

public class IndiceAS {
    private final String airQuality;
    private final String pollutants;
    private final String risk;
    private Recommendations recommendations;

    public IndiceAS(List<String> data) {
        this.airQuality = data.get(0);
        this.pollutants = data.get(1);
        this.risk = data.get(2);
        this.recommendations = new Recommendations(data.get(3), data.get(4));
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

    public Recommendations getRecommendations() {
        return recommendations;
    }
    
}
