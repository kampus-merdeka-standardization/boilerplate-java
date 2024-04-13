package t.it.simplespringrestserver.applications.services;


import t.it.simplespringrestserver.interfaces.models.requests.AddUser;
import t.it.simplespringrestserver.interfaces.models.requests.UpdateUser;
import t.it.simplespringrestserver.interfaces.models.responses.CurrentUserState;

public interface SimpleService {
    String getGreetingByName(String name);
    CurrentUserState addUser(AddUser addUser);
    CurrentUserState updateAllFields(UpdateUser updateUser);
    CurrentUserState updateSomeFields(UpdateUser updateUser);
    CurrentUserState deleteUser(String id);
}
