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

	@GetMapping("/cdmx")
	public AirCdmx cdmx() {
		return new AirCdmx();
	}
}
