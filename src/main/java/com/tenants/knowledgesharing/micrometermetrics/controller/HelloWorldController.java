package com.tenants.knowledgesharing.micrometermetrics.controller;

import com.tenants.knowledgesharing.micrometermetrics.infrastructure.KSMetrics;
import com.tenants.knowledgesharing.micrometermetrics.infrastructure.MetricsLogger;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloWorldController {
	MetricsLogger metricsLogger;
	
	@GetMapping("/")
	public String hello() {
		return "Hello World! Knowledge Sharing Application";
	}
	
	@GetMapping("/record")
	public String recordDistribution(@RequestParam String payload) {
		metricsLogger.register(KSMetrics.CREATE_TENANT_API_REQUEST_GET_DISTRIBUTION);
		
		return "Distribution Summary Recorded";
	}
	
	@PostMapping("/record-summary")
	@ResponseStatus(HttpStatus.OK)
	public String recordDistributionSummary(@RequestBody String payload) {
		metricsLogger.register(KSMetrics.CREATE_TENANT_API_REQUEST_POST_D_SUMMARY);
		
		return "Distribution Summary Recorded for post";
	}
}
