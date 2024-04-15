package t.it.simplespringrestserver.applications.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import t.it.simplespringrestserver.applications.models.requests.UpdateUser;
import t.it.simplespringrestserver.applications.models.responses.CurrentUserState;
import t.it.simplespringrestserver.applications.models.responses.WebResponse;
import t.it.simplespringrestserver.domains.services.SimpleService;
import t.it.simplespringrestserver.applications.models.requests.AddUser;

@RestController
@RequiredArgsConstructor
public class SimpleController {
    private final SimpleService simpleService;

    @GetMapping(path = "/hello/{name}")
    public WebResponse<String> greeting(@PathVariable("name") String name) {
        return WebResponse.<String>builder()
                .data(simpleService.getGreetingByName(name))
                .build();
    }

    @PostMapping(path = "/hello")
    public WebResponse<CurrentUserState> addUser(@RequestBody AddUser addUser) {
        return WebResponse.<CurrentUserState>builder()
                .data(simpleService.addUser(addUser))
                .build();
    }

    @PutMapping(path = "/hello")
    public WebResponse<CurrentUserState> updateUser(@RequestBody UpdateUser updateUser) {
        return WebResponse.<CurrentUserState>builder()
                .data(simpleService.updateAllFields(updateUser))
                .build();
    }

    @PatchMapping(path = "/hello/{id}")
    public WebResponse<CurrentUserState> updateUserById(@PathVariable("id") String id, @RequestBody UpdateUser updateUser) {
        return WebResponse.<CurrentUserState>builder()
                .data(simpleService.updateSomeFields(updateUser.withId(id)))
                .build();
    }

    @DeleteMapping(path = "/hello/{id}")
    public WebResponse<CurrentUserState> deleteUserById(@PathVariable("id") String id) {
        return WebResponse.<CurrentUserState>builder()
                .data(simpleService.deleteUser(id))
                .build();
    }
}
