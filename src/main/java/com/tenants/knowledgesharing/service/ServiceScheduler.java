package com.tenants.knowledgesharing.service;

import java.util.UUID;

import com.tenants.knowledgesharing.infrastructure.MetricsLogger;
import com.tenants.knowledgesharing.infrastructure.KSMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.tenants.knowledgesharing.utils.RandomUtils.getRandomNumberInRange;

@Component
@Slf4j
public class ServiceScheduler {
	
	private final TenantLifecycleService tenantLifecycleService;
	private final MetricsLogger metricsLogger;
	
	
	public ServiceScheduler(TenantLifecycleService tenantLifecycleService, MeterRegistry meterRegistry,
			MetricsLogger metricsLogger) {
		this.tenantLifecycleService = tenantLifecycleService;
		this.metricsLogger = metricsLogger;
	}
	
	
	@Scheduled(fixedRateString = "100000", initialDelayString = "0")
	public void scheduleRandomMetricsGeneration() {
		log.info("-------running scheduled task--------");
		metricsLogger.setGauge(KSMetrics.KS_RANDOM_NUMBER_METRICS_GAUGE,
				getRandomNumberInRange(228, 99292929));
		metricsLogger.increment(KSMetrics.KS_RANDOM_CUSTOM_METRIC_COUNTER);
	}
	
	
	@Scheduled(fixedRateString = "50000", initialDelayString = "1000")
	public void createTenant() {
		String tenantId = UUID.randomUUID().toString();
		this.tenantLifecycleService.create(tenantId);
	}
	
	@Scheduled(fixedRateString = "80000", initialDelayString = "1000")
	public void deactivateTenant() throws InterruptedException {
		String tenantId = UUID.randomUUID().toString();
		this.tenantLifecycleService.deactivate(tenantId);
	}
	
	@Scheduled(fixedRateString = "99999", initialDelayString = "1000")
	public void deleteTenant() {
		String tenantId = UUID.randomUUID().toString();
		this.tenantLifecycleService.delete(tenantId);
	}
}
