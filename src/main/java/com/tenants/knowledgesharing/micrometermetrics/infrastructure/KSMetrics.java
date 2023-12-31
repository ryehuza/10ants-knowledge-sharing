package com.tenants.knowledgesharing.micrometermetrics.infrastructure;

public class KSMetrics {
	
	// Counters
	public static final String KS_RANDOM_CUSTOM_METRIC_COUNTER
			= "ks.random.custom.metrics.counter";
	public static final String TENANT_CREATED_SUCCESSFULLY_COUNTER
			= "tenant.created.success.counter";
	
	// Gauges
	public static final String KS_RANDOM_NUMBER_METRICS_GAUGE = "ks.random.custom.metrics.gauge";
	
	// Timers
	public static final String TENANT_ACTIVATED_TIMER
			= "tenant.activated.timer";
	public static final String TENANT_DEACTIVATED_TIMER
			= "tenant.deactivated.sample.timer";
	
	public static final String TENANT_DELETED_TIMER
			= "tenant.deleted.timed.timer";
	
	// Distribution Summary
	public static final String CREATE_TENANT_API_REQUEST_GET_DISTRIBUTION
			= "tenant.api.create.request.size.get.summary";
	public static final String CREATE_TENANT_API_REQUEST_POST_D_SUMMARY
			= "tenant.api.create.request.size.post.summary";
	
}
