package by.parakhonka.springsecurity.service;

import by.parakhonka.springsecurity.entity.User;

public interface IRegistrationService {

    User checkUserExist(String pName);

    void saveUser(User pUser);
}
