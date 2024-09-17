package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Slf4j
public class RollController {
  @Autowired
  private RestTemplate restTemplate;
  @Value("${app.service-url}")
  private String url;

  @GetMapping("/test")
  public String getMethodName() {
    log.info("getMethodName()");
    restTemplate.getForObject(url + "/resource", String.class);
    restTemplate.getForObject(url + "/test", String.class);
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
  // https://github.com/XiaoMi/mone/blob/3f0241fbae4624331bce4faf62673a0430937e34/opentelemetry-java-instrumentation/instrumentation/spring/README.md?plain=1#L541
  // https://github.com/open-telemetry/opentelemetry-java/discussions/4668
  // https://opentelemetry.io/docs/languages/java/instrumentation/#context-propagation
  // todo
  @GetMapping("/resource")
  public String ssss() {
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