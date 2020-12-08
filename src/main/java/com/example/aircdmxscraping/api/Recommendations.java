package com.example.aircdmxscraping.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_ABSENT)
public class Recommendations {
    private final String sensitiveGroups;
    private final String wholePopulation;
    public Recommendations(String r1, String r2) {
        this.sensitiveGroups = r1;
        this.wholePopulation = r2;
    }

    public String getSensitiveGroups() {
        return sensitiveGroups;
    }

    public String getWholePopulation() {
        return wholePopulation;
    }

    
}
