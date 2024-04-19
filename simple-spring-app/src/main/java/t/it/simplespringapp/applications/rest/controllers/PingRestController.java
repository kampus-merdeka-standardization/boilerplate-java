package t.it.simplespringapp.applications.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import t.it.simplespringapp.applications.responses.MetaResponse;
import t.it.simplespringapp.applications.responses.WebResponse;
import t.it.simplespringapp.domains.services.PingService;

@RestController
@RequiredArgsConstructor
public class PingRestController {
    private final PingService pingService;

    @GetMapping(path = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<WebResponse<String>> ping() {
        return pingService.ping().map(response -> WebResponse.<String>builder()
                .data(response).meta(MetaResponse.builder()
                        .code(String.valueOf(HttpStatus.OK.value())).message(
                                HttpStatus.OK.getReasonPhrase()
                        ).build()).build());
    }
}