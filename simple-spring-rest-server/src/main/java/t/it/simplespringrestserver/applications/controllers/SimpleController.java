package t.it.simplespringrestserver.applications.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import t.it.simplespringrestserver.applications.models.requests.UpdateUser;
import t.it.simplespringrestserver.applications.models.responses.CurrentUserState;
import t.it.simplespringrestserver.applications.models.responses.WebResponse;
import t.it.simplespringrestserver.domains.services.SimpleService;
import t.it.simplespringrestserver.applications.models.requests.AddUser;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SimpleController {
    private final SimpleService simpleService;

    @GetMapping(path = "/hello/{name}")
    public Mono<WebResponse<String>> greeting(@PathVariable("name") String name) {
        return simpleService.getGreetingByName(name).map(greeting -> WebResponse.<String>builder()
                .data(greeting)
                .build());
    }

    @PostMapping(path = "/hello")
    public Mono<WebResponse<CurrentUserState>> addUser(@RequestBody AddUser addUser) {
        return simpleService.addUser(addUser).map(currentUserState -> WebResponse.<CurrentUserState>builder()
                .data(currentUserState)
                .build());
    }

    @PutMapping(path = "/hello")
    public Mono<WebResponse<CurrentUserState>> updateUser(@RequestBody UpdateUser updateUser) {
        return simpleService.updateAllFields(updateUser).map(currentUserState -> WebResponse.<CurrentUserState>builder()
                .data(currentUserState)
                .build());
    }

    @PatchMapping(path = "/hello/{id}")
    public Mono<WebResponse<CurrentUserState>> updateUserById(@PathVariable("id") String id, @RequestBody UpdateUser updateUser) {
        return simpleService.updateSomeFields(updateUser.withId(id)).map(currentUserState -> WebResponse.<CurrentUserState>builder()
                .data(currentUserState)
                .build());
    }

    @DeleteMapping(path = "/hello/{id}")
    public Mono<WebResponse<CurrentUserState>> deleteUserById(@PathVariable("id") String id) {
        return simpleService.deleteUser(id).map(currentUserState -> WebResponse.<CurrentUserState>builder()
                .data(currentUserState)
                .build());
    }
}
