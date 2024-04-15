package t.it.simplespringapp.applications.controllers.graphql.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import t.it.simplespringapp.domains.services.PingService;
import t.it.simplespringapp.applications.models.responses.PongResponse;

@RequiredArgsConstructor
@Controller
public class PingGraphQlController {
    private final PingService pingService;

    @QueryMapping(name = "response")
    public PongResponse ping() {
        return PongResponse.builder().message(pingService.ping()).build();
    }
}
