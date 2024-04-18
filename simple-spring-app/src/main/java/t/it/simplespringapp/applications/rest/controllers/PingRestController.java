package t.it.simplespringapp.applications.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import t.it.simplespringapp.domains.services.PingService;
import t.it.simplespringapp.applications.models.responses.PongResponse;

@RestController
@RequiredArgsConstructor
public class PingRestController {
    private final PingService pingService;

    @GetMapping(path = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<PongResponse> ping() {
        return pingService.ping().map(response -> PongResponse.builder()
                .message(response)
                .build());
    }
}