// package com.example.demo;

// import java.io.IOException;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import io.opentelemetry.api.OpenTelemetry;
// import io.opentelemetry.api.trace.Span;
// import io.opentelemetry.api.trace.SpanKind;
// import io.opentelemetry.context.Context;
// import io.opentelemetry.context.Scope;
// import io.opentelemetry.context.propagation.TextMapGetter;
// import io.opentelemetry.semconv.SemanticAttributes;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// import java.util.Enumeration;
// import java.util.HashMap;
// import java.util.Map;
// import java.net.MalformedURLException;
// import java.net.URL;

// @Component
// @RequiredArgsConstructor
// @Slf4j
// public class Filterr extends OncePerRequestFilter {
//   private final OpenTelemetry openTelemetry;
//   private static final TextMapGetter<Map<String, String>> getter = new TextMapGetter<>() {
//     @Override
//     public String get(Map<String, String> carrier, String key) {
//       return carrier.get(key);
//     }

//     @Override
//     public Iterable<String> keys(Map<String, String> carrier) {
//       return carrier.values();
//     }
//   };

//   private static URL getFullUrl(HttpServletRequest request) {
//     StringBuffer url = request.getRequestURL();
//     String queryString = request.getQueryString();
//     if (queryString != null) {
//       url.append('?').append(queryString);
//     }

//     try {
//       return new URL(url.toString());
//     } catch (MalformedURLException e) {
//       log.error("failed to parse request url", e);
//       return null;
//     }
//   }

//   @Override
//   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//       throws ServletException, IOException {
//     var map = getHeadersMap(request);
//     log.info("map {}", map);
//     URL url = getFullUrl(request);
//     Context extractedContext = openTelemetry.getPropagators().getTextMapPropagator().extract(Context.current(),
//         map,
//         getter);
//     var tracer = openTelemetry.getTracer("application");

//     var currentContext = Context.current();
//     try (Scope scope = extractedContext.makeCurrent()) {
//       Span serverSpan = tracer.spanBuilder("CALLING PROCESS")
//           .setSpanKind(SpanKind.SERVER)
//           .addLink(Span.current().getSpanContext())
//           .setParent(currentContext)
//           .startSpan();
//       serverSpan.makeCurrent();
//       try {
//         chain.doFilter(request, response);
//         // Add the attributes defined in the Semantic Conventions
//         serverSpan.setAttribute(SemanticAttributes.HTTP_METHOD, "GET");
//         serverSpan.setAttribute(SemanticAttributes.HTTP_SCHEME, "http");
//         serverSpan.setAttribute(SemanticAttributes.HTTP_HOST, "localhost:8080");
//         serverSpan.setAttribute(SemanticAttributes.HTTP_TARGET, "/resource");

//       } finally {
//         serverSpan.end();
//       }
//     }

//   }

//   private Map<String, String> getHeadersMap(HttpServletRequest request) {
//     Map<String, String> headersMap = new HashMap<>();
//     Enumeration<String> headerNames = request.getHeaderNames();

//     while (headerNames.hasMoreElements()) {
//       String headerName = headerNames.nextElement();
//       String headerValue = request.getHeader(headerName);
//       headersMap.put(headerName, headerValue);
//     }

//     return headersMap;
//   }
// }
