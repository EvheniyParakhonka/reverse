package by.parakhonka.reverse.dao;

import by.parakhonka.reverse.entity.User;

public interface IUserDao {
    /**
     * save user to db
     *
     * @param pUser object users
     */
    void save(User pUser);

    /**
     * find user by id in db
     *
     * @param pId id user
     * @return user how have id
     */
    User findById(int pId);

    /**
     * find user by name
     *
     * @param pUserName user name to find user in db
     * @return user how have name
     */
    User findByUserName(String pUserName);

}

