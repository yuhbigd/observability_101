package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		log.info("printing left mask - maskedID: {}", "1234");
		log.info("printing left mask - maskedID: {}", "12345");
		log.info("printing left mask - maskedID: {}", "123");
		log.info("printing some stuff {}", "A234");
		log.info("printing some stuff {}", "ABCD");
		log.info("printing left mask - maskedID: {}", "ABC1234");
		log.info("Id that should be masked: 1234");
		app.run(args);
	}
	// @Bean
	// public OpenTelemetry openTelemetry() {
	// Resource resource = Resource.getDefault().toBuilder()
	// .put(ServiceAttributes.SERVICE_NAME, "dice-server")
	// .put(ServiceAttributes.SERVICE_VERSION, "0.1.0")
	// .build();
	// OtlpGrpcSpanExporter jaegerOtlpExporter = OtlpGrpcSpanExporter.builder()
	// .setEndpoint("http://localhost:4317")
	// .setTimeout(3, TimeUnit.SECONDS)
	// .build();
	// SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
	// .addSpanProcessor(
	// BatchSpanProcessor.builder(OtlpGrpcSpanExporter.builder()
	// .setEndpoint("http://localhost:4317")
	// .setTimeout(3, TimeUnit.SECONDS)
	// .build()).build())
	// .setResource(resource)
	// .build();

	// SdkMeterProvider sdkMeterProvider = SdkMeterProvider.builder()
	// .registerMetricReader(
	// PeriodicMetricReader.builder(OtlpGrpcMetricExporter.builder()
	// .setEndpoint("http://localhost:4317")
	// .setTimeout(3, TimeUnit.SECONDS)
	// .build()).build())
	// .setResource(resource)
	// .build();

	// SdkLoggerProvider sdkLoggerProvider = SdkLoggerProvider.builder()
	// .addLogRecordProcessor(
	// BatchLogRecordProcessor.builder(OtlpGrpcLogRecordExporter.builder()
	// .setEndpoint("http://localhost:4317")
	// .setTimeout(3, TimeUnit.SECONDS)
	// .build()).build())
	// .setResource(resource)
	// .build();

	// OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
	// .setTracerProvider(sdkTracerProvider)
	// .setMeterProvider(sdkMeterProvider)
	// .setLoggerProvider(sdkLoggerProvider)
	// .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
	// .buildAndRegisterGlobal();

	// return openTelemetry;
	// }
}
