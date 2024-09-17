package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.run(args);
	}

	// @Bean
	// public OpenTelemetry openTelemetry() {
	// 	Resource resource = Resource.getDefault().toBuilder()
	// 			.put(ServiceAttributes.SERVICE_NAME, "dice-server")
	// 			.put(ServiceAttributes.SERVICE_VERSION, "0.1.0")
	// 			.build();
	// 	OtlpGrpcSpanExporter jaegerOtlpExporter = OtlpGrpcSpanExporter.builder()
	// 			.setEndpoint("http://localhost:4317")
	// 			.setTimeout(3, TimeUnit.SECONDS)
	// 			.build();
	// 	SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
	// 			.addSpanProcessor(
	// 					BatchSpanProcessor.builder(OtlpGrpcSpanExporter.builder()
	// 							.setEndpoint("http://localhost:4317")
	// 							.setTimeout(3, TimeUnit.SECONDS)
	// 							.build()).build())
	// 			.setResource(resource)
	// 			.build();

	// 	SdkMeterProvider sdkMeterProvider = SdkMeterProvider.builder()
	// 			.registerMetricReader(
	// 					PeriodicMetricReader.builder(OtlpGrpcMetricExporter.builder()
	// 							.setEndpoint("http://localhost:4317")
	// 							.setTimeout(3, TimeUnit.SECONDS)
	// 							.build()).build())
	// 			.setResource(resource)
	// 			.build();

	// 	SdkLoggerProvider sdkLoggerProvider = SdkLoggerProvider.builder()
	// 			.addLogRecordProcessor(
	// 					BatchLogRecordProcessor.builder(OtlpGrpcLogRecordExporter.builder()
	// 							.setEndpoint("http://localhost:4317")
	// 							.setTimeout(3, TimeUnit.SECONDS)
	// 							.build()).build())
	// 			.setResource(resource)
	// 			.build();

	// 	OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
	// 			.setTracerProvider(sdkTracerProvider)
	// 			.setMeterProvider(sdkMeterProvider)
	// 			.setLoggerProvider(sdkLoggerProvider)
	// 			.setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
	// 			.buildAndRegisterGlobal();

	// 	return openTelemetry;
	// }
}
