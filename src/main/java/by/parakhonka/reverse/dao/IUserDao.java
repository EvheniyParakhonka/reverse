package by.parakhonka.reverse.dao;

import by.parakhonka.reverse.entity.User;

public interface IUserDao {

    void save(User pUser);

    User findById(int pId);

    User findByUserName(String pUserName);

}

