package com.example.aircdmxscraping.api.partials;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Air {
    
	protected String place;
	protected String temperature;
	protected String date;
	protected IndiceAS indiceAS;
	protected IndiceCDMX indiceCDMX;

	public String getPlace() {
		return place;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getDate() {
		return date;
	}

	@JsonProperty("indiceAireSalud")
	public IndiceAS getIndiceAS() {
		return indiceAS;
	}

	@JsonProperty("indiceCDMX")
	public IndiceCDMX getIndiceCDMX() {
		return indiceCDMX;
	}

}
