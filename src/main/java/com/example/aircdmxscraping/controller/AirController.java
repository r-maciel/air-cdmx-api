package com.example.aircdmxscraping.controller;

import java.util.concurrent.atomic.AtomicLong;

import javax.websocket.server.PathParam;

import com.example.aircdmxscraping.api.AirCdmx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirController {

	@GetMapping("/cdmx")
	public AirCdmx cdmx() {
		return new AirCdmx();
	}
}
