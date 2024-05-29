package t.it.simplespringmonitoring.application.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import t.it.simplespringmonitoring.application.responses.MetaResponse;
import t.it.simplespringmonitoring.application.responses.WebResponse;
import t.it.simplespringmonitoring.domain.services.GreetingService;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GreetingController {
    private final GreetingService greetingService;

    @GetMapping(path = "/greeting/{name}")
    public Mono<WebResponse<String>> greeting(@PathVariable("name") String name) {
        MDC.put("traceId", UUID.randomUUID().toString());

        log.info("greeting {}", name);
        Mono<String> greeting = greetingService.doGreeting(name);
        Mono<WebResponse<String>> webResponse = greeting.map(response -> WebResponse.<String>builder().data(response).meta(MetaResponse.builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(HttpStatus.OK.getReasonPhrase()).build()).build());

        MDC.remove("traceId");
        return webResponse;
    }
}
