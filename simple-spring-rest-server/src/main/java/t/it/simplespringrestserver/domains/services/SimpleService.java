package t.it.simplespringrestserver.domains.services;


import t.it.simplespringrestserver.applications.models.requests.AddUser;
import t.it.simplespringrestserver.applications.models.requests.UpdateUser;
import t.it.simplespringrestserver.applications.models.responses.CurrentUserState;

public interface SimpleService {
    String getGreetingByName(String name);
    CurrentUserState addUser(AddUser addUser);
    CurrentUserState updateAllFields(UpdateUser updateUser);
    CurrentUserState updateSomeFields(UpdateUser updateUser);
    CurrentUserState deleteUser(String id);
}
