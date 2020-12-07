package com.example.aircdmxscraping.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.example.aircdmxscraping.api.AirCdmx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirController {
    
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/air-cdmx-scraper")
	public AirCdmx greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new AirCdmx(counter.incrementAndGet(), String.format(template, name));
	}
}
