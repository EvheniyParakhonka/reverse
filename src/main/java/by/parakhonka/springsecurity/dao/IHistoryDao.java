package by.parakhonka.springsecurity.dao;

import by.parakhonka.springsecurity.model.History;

import java.util.List;

public interface IHistoryDao {
    void save(History pHistory);

    List<History> getAllHistoriUser(String id);

    History getByidHistory(int id);

    History getLastHistory(String userName);

    List getTenHistory(int pPage, String pUserName);
}
