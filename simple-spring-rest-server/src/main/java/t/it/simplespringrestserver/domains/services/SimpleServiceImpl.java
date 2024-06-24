package t.it.simplespringrestserver.domains.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import t.it.simplespringrestserver.commons.utils.StringUtils;
import t.it.simplespringrestserver.domains.services.models.AddUser;
import t.it.simplespringrestserver.domains.services.models.UpdateUser;
import t.it.simplespringrestserver.domains.services.models.CurrentUserState;

import java.time.Instant;
import java.util.UUID;

@Service
public class SimpleServiceImpl implements SimpleService {
    private static final String id = UUID.randomUUID().toString();

    @Override
    public Mono<String> getGreetingByName(String name) {
        return Mono.just(StringUtils.helloGreeter(name) + "!");
    }

    @Override
    public Mono<CurrentUserState> addUser(AddUser addUser) {
        return Mono.just(CurrentUserState.builder()
                .id(id)
                .message(StringUtils.helloGreeter(addUser.name()) + " you are " + addUser.age() + " years old")
                .timestamp(Instant.now().getEpochSecond())
                .build());
    }

    @Override
    public Mono<CurrentUserState> updateAllFields(UpdateUser updateUser) {
        return Mono.just(CurrentUserState.builder()
                .id(id)
                .message(StringUtils.COMMON_GREETER_UPDATE_MESSAGE + " from " + updateUser.currentName() + " to " + updateUser.newName())
                .timestamp(Instant.now().getEpochSecond())
                .build());
    }

    @Override
    public Mono<CurrentUserState> updateSomeFields(UpdateUser updateUser) {
        return Mono.just(CurrentUserState.builder()
                .id(id)
                .message(StringUtils.COMMON_GREETER_UPDATE_MESSAGE + " to " + updateUser.newName())
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
