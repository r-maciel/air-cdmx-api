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
