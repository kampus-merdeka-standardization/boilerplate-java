package t.it.simplespringapp.interfaces.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import t.it.simplespringapp.applications.services.PingService;
import t.it.simplespringapp.interfaces.models.responses.PongResponse;

@RestController
@RequiredArgsConstructor
public class PingRestController {
    private final PingService pingService;

    @GetMapping(path = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PongResponse ping() {
        return pingService.ping();
    }
}