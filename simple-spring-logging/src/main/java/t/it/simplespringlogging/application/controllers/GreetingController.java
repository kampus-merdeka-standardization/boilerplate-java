package t.it.simplespringlogging.application.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import t.it.simplespringlogging.domain.services.GreetingService;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GreetingController {
    private final GreetingService greetingService;

    @GetMapping(path = "/greeting/{name}")
    public Mono<String> greeting(@PathVariable("name") String name) {
        MDC.put("traceId", UUID.randomUUID().toString());
        log.info("greeting {}", name);
        Mono<String> greeting = greetingService.doGreeting(name);
        MDC.remove("traceId");
        return greeting;
    }
}
