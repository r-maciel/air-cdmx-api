package com.example.aircdmxscraping.controller;

import com.example.aircdmxscraping.api.AirCdmx;
import com.example.aircdmxscraping.api.AirDelegaciones;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirController {

	@GetMapping("/cdmx")
	public AirCdmx cdmx() {
		return new AirCdmx();
	}

	@GetMapping("/delegaciones/{id}")
	public AirDelegaciones delegaciones(@PathVariable int id) {
		return new AirDelegaciones(id);
	}
}
