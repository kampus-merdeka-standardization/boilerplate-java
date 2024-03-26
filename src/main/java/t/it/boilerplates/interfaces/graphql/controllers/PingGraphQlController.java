package t.it.boilerplates.interfaces.graphql.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import t.it.boilerplates.interfaces.models.responses.PongResponse;
import t.it.boilerplates.applications.services.PingService;

@RequiredArgsConstructor
@Controller
public class PingGraphQlController {
    private final PingService pingService;

    @QueryMapping(name = "response")
    public PongResponse ping() {
        return pingService.ping();
    }
}
