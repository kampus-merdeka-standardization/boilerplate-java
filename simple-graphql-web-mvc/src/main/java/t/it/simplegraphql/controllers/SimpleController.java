package t.it.simplegraphql.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import t.it.simplegraphql.dto.PongResponse;
import t.it.simplegraphql.services.SimpleService;

@RequiredArgsConstructor
@Controller
public class SimpleController {
    private final SimpleService simpleService;

    @QueryMapping(name = "response")
    public PongResponse ping() {
        return simpleService.ping();
    }
}
