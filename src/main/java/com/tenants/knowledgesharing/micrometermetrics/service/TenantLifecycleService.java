package com.tenants.knowledgesharing.micrometermetrics.service;

import java.util.concurrent.TimeUnit;

import com.tenants.knowledgesharing.micrometermetrics.infrastructure.KSMetrics;
import com.tenants.knowledgesharing.micrometermetrics.infrastructure.MetricsLogger;
import com.tenants.knowledgesharing.micrometermetrics.utils.RandomUtils;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TenantLifecycleService {
	
	private final MetricsLogger metricsLogger;
	private final MeterRegistry meterRegistry;
	
	
	public TenantLifecycleService(MetricsLogger metricsLogger, MeterRegistry meterRegistry) {
		this.metricsLogger = metricsLogger;
		this.meterRegistry = meterRegistry;
	}
	
	
	public void create(String tenantId) {
		this.metricsLogger.increment(KSMetrics.TENANT_CREATED_SUCCESSFULLY_COUNTER);
		log.info("........... tenant created: " + tenantId);
		metricsLogger.recordTime(KSMetrics.TENANT_ACTIVATED_TIMER).record(() -> activate(tenantId));
	}
	
	
	public void activate(String tenantId) {
		// delay processing times randomly, so we can use a timer to track latency
		try {
			Thread.sleep(RandomUtils.getRandomNumberInRange(1, 90) * 1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		log.info("........... tenant activated: " + tenantId);
		
	}
	
	
	public void deactivate(String tenantId) throws InterruptedException {
		// Storing Start State in Timer.Sample
		
		Timer.Sample sample = Timer.start(meterRegistry);
		int randomNumber = RandomUtils.getRandomNumberInRange(1, 90);
		TimeUnit.SECONDS.sleep(randomNumber);
		sample.stop(meterRegistry.timer(KSMetrics.TENANT_DEACTIVATED_TIMER, "randomSleepNumber", String.valueOf(randomNumber)));
		
		log.info("........... tenant deactivated: " + tenantId);
	}
	
	@Timed(value = KSMetrics.TENANT_DELETED_TIMER)
	//percentiles = { 0.5, 0.75, 0.95, 0,98, 0.99 }
	public void delete(String tenantId) {
		try {
			Thread.sleep(RandomUtils.getRandomNumberInRange(1, 90) * 1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		log.info("........... tenant deleted: " + tenantId);
	}
}
