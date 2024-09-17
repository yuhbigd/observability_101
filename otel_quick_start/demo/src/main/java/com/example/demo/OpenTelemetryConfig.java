package com.example.demo;

import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.semconv.UrlAttributes;
import java.util.Collections;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryConfig {

  @Bean
  public AutoConfigurationCustomizerProvider otelCustomizer() {
    return p -> p.addSpanExporterCustomizer(this::configureSpanExporter);
  }

  /**
   * Configuration for the OTLP exporter. This configuration will replace the
   * default OTLP exporter,
   * and will add a custom header to the requests.
   */
  private SpanExporter configureSpanExporter(SpanExporter exporter, ConfigProperties config) {
    if (exporter instanceof OtlpHttpSpanExporter) {
      return ((OtlpHttpSpanExporter) exporter).toBuilder().setHeaders(this::headers).build();
    }
    return exporter;
  }

  private Map<String, String> headers() {
    return Collections.singletonMap("Authorization", "Bearer " + refreshToken());
  }

  private String refreshToken() {
    // e.g. read the token from a kubernetes secret
    return "token";
  }
}