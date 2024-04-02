package t.it.simplespringapp.interfaces.graphql.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import t.it.simplespringapp.applications.services.PingService;
import t.it.simplespringapp.interfaces.models.responses.PongResponse;

@RequiredArgsConstructor
@Controller
public class PingGraphQlController {
    private final PingService pingService;

    @QueryMapping(name = "response")
    public PongResponse ping() {
        return pingService.ping();
    }
}
