package t.it.simplespringapp.applications.graphql.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import t.it.simplespringapp.domains.services.PingService;
import t.it.simplespringapp.applications.models.responses.PongResponse;

@RequiredArgsConstructor
@Controller
public class PingGraphQlController {
    private final PingService pingService;

    @QueryMapping(name = "message")
    public Mono<String> ping() {
        return pingService.ping();
    }
}
