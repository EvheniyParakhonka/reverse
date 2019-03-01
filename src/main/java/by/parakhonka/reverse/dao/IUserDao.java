package by.parakhonka.reverse.dao;

import by.parakhonka.reverse.entity.User;

import java.util.Optional;

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
     * find user by userName
     *
     * @param pUserName user userName to find user in db
     * @return user how have userName
     */
    User findByUserName(String pUserName);

}

