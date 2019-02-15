package by.parakhonka.reverse.service;

import by.parakhonka.reverse.entity.User;

public interface IRegistrationService {
    /**
     * check in db of user exist or not, for not duplicate
     *
     * @param pName name user
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
