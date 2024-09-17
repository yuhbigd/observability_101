package com.example.demo;

import io.opentelemetry.context.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.context.propagation.TextMapSetter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import io.opentelemetry.api.trace.Tracer;

@Component
@Slf4j
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

  private Tracer tracer;

  @Autowired
  private OpenTelemetry openTelemetry;
  private static final TextMapSetter<HttpRequest> setter = new TextMapSetter<HttpRequest>() {
    @Override
    public void set(HttpRequest carrier, String key, String value) {
      // Insert the context as Header
      carrier.getHeaders().set(key, value);
    }
  };

  @Override
  @SneakyThrows
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
      ClientHttpRequestExecution execution) {
    // tracer = openTelemetry.getTracer("application");
    // String spanName = request.getURI().toString();
    // Span currentSpan =
    // tracer.spanBuilder(spanName).setSpanKind(SpanKind.CLIENT).startSpan();
    // try (Scope scope = currentSpan.makeCurrent()) {
    openTelemetry.getPropagators().getTextMapPropagator().inject(Context.current(), request, setter);
    ClientHttpResponse response = execution.execute(request, body);
    log.info("Request sent from RestTemplateInterceptor");

    return response;
    // } finally {
    // currentSpan.end();
    // }
  }
}