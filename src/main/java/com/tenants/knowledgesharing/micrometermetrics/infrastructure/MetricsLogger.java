package com.tenants.knowledgesharing.micrometermetrics.infrastructure;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

@Component("MetricsLogger")
public class MetricsLogger {
	
	private final MeterRegistry meterRegistry;
	
	
	public MetricsLogger(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}
	
	public void increment(String metricName) {
		meterRegistry.counter(metricName).increment();
	}
	
	public void setGauge(String metricName, int randomNumberInRange) {
		meterRegistry.gauge(metricName, randomNumberInRange);
	}
	
	public Timer recordTime(String metricName) {
		return meterRegistry.timer(metricName);
	}
	
	// Distribution Summary
	public void register(String metricName) {
		meterRegistry.summary(metricName);
	}
	
}
