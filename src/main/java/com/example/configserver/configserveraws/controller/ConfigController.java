package com.example.configserver.configserveraws.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {
	
	@RequestMapping
	public String hello() {
		return "hello";
	}
}
