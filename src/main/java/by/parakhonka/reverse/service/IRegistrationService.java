package by.parakhonka.reverse.service;

import by.parakhonka.reverse.entity.User;

public interface IRegistrationService {

    User checkUserExist(String pName);

    void saveUser(User pUser);
}
