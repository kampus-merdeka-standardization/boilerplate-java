package t.it.simplespringrestserver.domains.services;


import reactor.core.publisher.Mono;
import t.it.simplespringrestserver.domains.services.models.AddUser;
import t.it.simplespringrestserver.domains.services.models.UpdateUser;
import t.it.simplespringrestserver.domains.services.models.CurrentUserState;

public interface SimpleService {
    Mono<String> getGreetingByName(String name);

    Mono<CurrentUserState> addUser(AddUser addUser);

    Mono<CurrentUserState> updateAllFields(UpdateUser updateUser);

    Mono<CurrentUserState> updateSomeFields(UpdateUser updateUser);

    Mono<CurrentUserState> deleteUser(String id);
}
