package t.it.simplespringrestserver.applications.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import t.it.simplespringrestserver.domains.services.models.UpdateUser;
import t.it.simplespringrestserver.domains.services.models.CurrentUserState;
import t.it.simplespringrestserver.applications.responses.MetaResponse;
import t.it.simplespringrestserver.applications.responses.WebResponse;
import t.it.simplespringrestserver.domains.services.SimpleService;
import t.it.simplespringrestserver.domains.services.models.AddUser;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SimpleController {
    private final SimpleService simpleService;

    @GetMapping(path = "/hello/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<String>> greeting(@PathVariable("name") String name) {
        return simpleService.getGreetingByName(name).map(greeting -> WebResponse.<String>builder()
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .data(greeting)
                .build());
    }

    @PostMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<WebResponse<CurrentUserState>> addUser(@RequestBody AddUser addUser) {
        return simpleService.addUser(addUser).map(currentUserState -> WebResponse.<CurrentUserState>builder()
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.CREATED.value())).message(HttpStatus.CREATED.getReasonPhrase()).build())
                .data(currentUserState)
                .build());
    }

    @PutMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<CurrentUserState>> updateUser(@RequestBody UpdateUser updateUser) {
        return simpleService.updateAllFields(updateUser).map(currentUserState -> WebResponse.<CurrentUserState>builder()
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .data(currentUserState)
                .build());
    }

    @PatchMapping(path = "/hello/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<CurrentUserState>> updateUserById(@PathVariable("id") String id, @RequestBody UpdateUser updateUser) {
        return simpleService.updateSomeFields(updateUser.withId(id)).map(currentUserState -> WebResponse.<CurrentUserState>builder()
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .data(currentUserState)
                .build());
    }

    @DeleteMapping(path = "/hello/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebResponse<CurrentUserState>> deleteUserById(@PathVariable("id") String id) {
        return simpleService.deleteUser(id).map(currentUserState -> WebResponse.<CurrentUserState>builder()
                .meta(MetaResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message(HttpStatus.OK.getReasonPhrase()).build())
                .data(currentUserState)
                .build());
    }
}
