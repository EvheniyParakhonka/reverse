package by.parakhonka.reverse.service;

import by.parakhonka.reverse.entity.User;

import java.util.Optional;

public interface IRegistrationService {
    /**
     * check in db of user exist or not, for not duplicate
     *
     * @param pName userName user
     * @return user if exesist
     */
    User checkUserExist(String pName);

    /**
     * save user in db
     *
     * @param pUser object user
     */
    void saveUser(User pUser);
}
