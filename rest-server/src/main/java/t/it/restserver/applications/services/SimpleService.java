package t.it.restserver.applications.services;

import t.it.restserver.interfaces.models.requests.AddUser;
import t.it.restserver.interfaces.models.requests.UpdateUser;
import t.it.restserver.interfaces.models.responses.CurrentUserState;

public interface SimpleService {
    String getGreetingByName(String name);
    CurrentUserState addUser(AddUser addUser);
    CurrentUserState updateAllFields(UpdateUser updateUser);
    CurrentUserState updateSomeFields(UpdateUser updateUser);
    CurrentUserState deleteUser(String id);
}
