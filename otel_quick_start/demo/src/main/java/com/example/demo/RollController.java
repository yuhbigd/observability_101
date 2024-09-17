package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.context.Context;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Random;

@RestController
@Slf4j
public class RollController {
  @Autowired
  private Repo repo;

  @GetMapping("/test")
  public String getMethodName() {
    // SpanContext.createFromRemoteParent(getMethodName(), getMethodName(), null,
    // null)
    Random random = new Random();

    for (int i = 1; i <= 20; i++) {

      // Generate a random session ID between 0 and 99 and add it as an MDC log as
      // well as the iteration count
      String session = String.valueOf(random.nextInt(99));
      MDC.put("session", session);
      MDC.put("loop", String.valueOf(i));

      // Trace information for the loop run
      log.trace("Iteration '{}' and session '{}'", i, session);

      // Log some errors, warns, infos, and debugs
      if (i % 15 == 0) {
        try {
          throw new RuntimeException(
              "Bad runtime... Lorem ipsum dolor sit amet");
        } catch (RuntimeException e) {
          MDC.put("user_experience", "\uD83E\uDD2C");
          log.error("Wake me up at night", e);
        }
      } else if (i % 5 == 0) {
        log.warn("Investigate tomorrow");
      } else if (i % 3 == 0) {
        log.info("Collect in production");
      } else {
        log.debug("Collect in development");
      }

      MDC.clear();
    }
    return "OKE";
  }

  // private static final Logger logger =
  // LoggerFactory.getLogger(RollController.class);
  // private final Tracer tracer;
  // private final OpenTelemetry openTelemetry;
  // private final Meter meter;

  // RollController(OpenTelemetry openTelemetry) {
  // tracer = openTelemetry.getTracer(RollController.class.getName(), "0.1.0");
  // this.openTelemetry = openTelemetry;
  // this.meter = openTelemetry.getMeter("application");
  // }

  // @GetMapping("/rolldice")
  // public List<Integer> index(@RequestParam("player") Optional<String> player,
  // @RequestParam("rolls") Optional<Integer> rolls) {
  // Span span = tracer.spanBuilder("rollTheDice").startSpan();

  // // Make the span the current span
  // try (Scope scope = span.makeCurrent()) {

  // if (!rolls.isPresent()) {
  // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing rolls
  // parameter", null);
  // }

  // List<Integer> result = new Dice(1, 6,
  // openTelemetry).rollTheDice(rolls.get());
  // new RestTemplate().getForObject("http://localhost:8080/resource",
  // String.class);
  // if (player.isPresent()) {
  // logger.info("{} is rolling the dice: {}", player.get(), result);
  // } else {
  // logger.info("Anonymous player is rolling the dice: {}", result);
  // }
  // return result;
  // } catch (Throwable t) {
  // span.recordException(t);
  // throw t;
  // } finally {
  // span.end();
  // }
  // }

  @GetMapping("/resource")
  public String ssss() {
    repo.save(new Test());
    log.info("SDASDASDASDASD");
    return new String("1231443");
  }
  // }

  // class Dice {

  // private int min;
  // private int max;
  // private Tracer tracer;

  // public Dice(int min, int max, OpenTelemetry openTelemetry) {
  // this.min = min;
  // this.max = max;
  // this.tracer = openTelemetry.getTracer(Dice.class.getName(), "0.1.0");
  // }

  // public Dice(int min, int max) {
  // this(min, max, OpenTelemetry.noop());
  // }

  // public List<Integer> rollTheDice(int rolls) {
  // Span parentSpan = tracer.spanBuilder("parent").startSpan();
  // List<Integer> results = new ArrayList<Integer>();
  // try {
  // for (int i = 0; i < rolls; i++) {
  // results.add(this.rollOnce(parentSpan));
  // }
  // return results;
  // } finally {
  // parentSpan.end();
  // }
  // }

  // private int rollOnce(Span parentSpan) {
  // Span childSpan = tracer.spanBuilder("child")
  // .setParent(Context.current().with(parentSpan))
  // .startSpan();
  // try {
  // return ThreadLocalRandom.current().nextInt(this.min, this.max + 1);
  // } finally {
  // childSpan.end();
  // }
  // }
}
