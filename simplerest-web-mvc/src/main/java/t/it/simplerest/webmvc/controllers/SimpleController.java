package t.it.simplerest.webmvc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import t.it.simplerest.webmvc.dto.PongResponse;
import t.it.simplerest.webmvc.services.SimpleService;

@RestController
@RequiredArgsConstructor
public class SimpleController {
    private final SimpleService simpleService;

    @GetMapping(path = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PongResponse> ping() {
        return ResponseEntity.ok(simpleService.ping());
    }
}
