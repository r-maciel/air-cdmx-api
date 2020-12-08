package com.example.aircdmxscraping.api;

import com.example.aircdmxscraping.scraper.DelegacionesScraper;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_ABSENT)
public class AirDelegaciones{
	private String temperature;
	private String date;
	private IndiceAS indiceAS;
	private IndiceCDMX indiceCDMX;

	public AirDelegaciones(int del) {
		DelegacionesScraper cdmx = new DelegacionesScraper(del);
		this.temperature = cdmx.getTemperature();
		this.date = cdmx.getCurrentDate();
		this.indiceAS = new IndiceAS(cdmx.getDataAS());
		this.indiceCDMX = new IndiceCDMX(cdmx.getDataCDMX());
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
