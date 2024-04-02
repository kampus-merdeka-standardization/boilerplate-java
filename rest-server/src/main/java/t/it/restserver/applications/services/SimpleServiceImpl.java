package t.it.restserver.applications.services;

import org.springframework.stereotype.Service;
import t.it.restserver.interfaces.models.requests.AddUser;
import t.it.restserver.interfaces.models.requests.UpdateUser;
import t.it.restserver.interfaces.models.responses.CurrentUserState;

import java.time.Instant;
import java.util.UUID;

@Service
public class SimpleServiceImpl implements SimpleService {
    private static final String id = UUID.randomUUID().toString();

    @Override
    public String getGreetingByName(String name) {
        return "Hello, " + name + "!";
    }

    @Override
    public CurrentUserState addUser(AddUser addUser) {
        return CurrentUserState.builder()
                .id(id)
                .message("Hello, " + addUser.name() + " you are " + addUser.age() + " years old")
                .timestamp(Instant.now().getEpochSecond())
                .build();
    }

    @Override
    public CurrentUserState updateAllFields(UpdateUser updateUser) {
        return CurrentUserState.builder()
                .id(id)
                .message("Your name is replaced from " + updateUser.currentName() + " to " + updateUser.newName())
                .timestamp(Instant.now().getEpochSecond())
                .build();
    }

    @Override
    public CurrentUserState updateSomeFields(UpdateUser updateUser) {
        return CurrentUserState.builder()
                .id(id)
                .message("Your name is replaced to " + updateUser.newName())
                .timestamp(Instant.now().getEpochSecond())
                .build();
    }

    @Override
    public CurrentUserState deleteUser(String id) {
        return CurrentUserState.builder()
                .id(id)
                .message("Your data is deleted")
                .timestamp(Instant.now().getEpochSecond())
                .build();
    }
}
