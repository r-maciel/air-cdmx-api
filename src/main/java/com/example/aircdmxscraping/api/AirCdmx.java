package com.example.aircdmxscraping.api;
import com.example.aircdmxscraping.scraper.CdmxScraper;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AirCdmx {
	private final String temperature;
	private final String date;
	private IndiceAS indiceAS;
	private IndiceCDMX indiceCDMX;

	public AirCdmx() {
		CdmxScraper cdmx = new CdmxScraper();
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
