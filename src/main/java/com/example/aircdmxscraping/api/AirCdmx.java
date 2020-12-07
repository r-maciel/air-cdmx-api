package com.example.aircdmxscraping.api;

import com.example.aircdmxscraping.scraper.AirCdmxScraper;

public class AirCdmx {
	private final long id;
	private final String content;

	public AirCdmx(long id, String content) {
        AirCdmxScraper scraper = new AirCdmxScraper();
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
