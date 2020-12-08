package com.example.aircdmxscraping.api;

import com.example.aircdmxscraping.api.partials.Air;
import com.example.aircdmxscraping.api.partials.IndiceAS;
import com.example.aircdmxscraping.api.partials.IndiceCDMX;
import com.example.aircdmxscraping.scraper.CdmxScraper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_ABSENT)
public class AirCdmx extends Air{
	public AirCdmx() {
		CdmxScraper cdmx = new CdmxScraper();
		this.place = cdmx.getPlace();
		this.temperature = cdmx.getTemperature();
		this.date = cdmx.getCurrentDate();
		this.indiceAS = new IndiceAS(cdmx.getDataAS());
		this.indiceCDMX = new IndiceCDMX(cdmx.getDataCDMX());
	}
}
