package t.it.simplespringrestserver.domains.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import t.it.simplespringrestserver.applications.models.requests.AddUser;
import t.it.simplespringrestserver.applications.models.requests.UpdateUser;
import t.it.simplespringrestserver.applications.models.responses.CurrentUserState;

import java.time.Instant;
import java.util.UUID;

@Service
public class SimpleServiceImpl implements SimpleService {
    private static final String id = UUID.randomUUID().toString();

    @Override
    public Mono<String> getGreetingByName(String name) {
        return Mono.just("Hello, " + name + "!");
    }

    @Override
    public Mono<CurrentUserState> addUser(AddUser addUser) {
        return Mono.just(CurrentUserState.builder()
                .id(id)
                .message("Hello, " + addUser.name() + " you are " + addUser.age() + " years old")
                .timestamp(Instant.now().getEpochSecond())
                .build());
    }

    @Override
    public Mono<CurrentUserState> updateAllFields(UpdateUser updateUser) {
        return Mono.just(CurrentUserState.builder()
                .id(id)
                .message("Your name is replaced from " + updateUser.currentName() + " to " + updateUser.newName())
                .timestamp(Instant.now().getEpochSecond())
                .build());
    }

    @Override
    public Mono<CurrentUserState> updateSomeFields(UpdateUser updateUser) {
        return Mono.just(CurrentUserState.builder()
                .id(id)
                .message("Your name is replaced to " + updateUser.newName())
                .timestamp(Instant.now().getEpochSecond())
                .build());
    }

    @Override
    public Mono<CurrentUserState> deleteUser(String id) {
        return Mono.just(CurrentUserState.builder()
                .id(id)
                .message("Your data is deleted")
                .timestamp(Instant.now().getEpochSecond())
                .build());
    }
}
