package by.parakhonka.reverse.dao;

import by.parakhonka.reverse.entity.History;

import java.util.List;

public interface IHistoryDao {
    void save(History pHistory);

    List<History> getAllHistoryUser(String pUserName);

    History getByIdHistory(int pId);

    History getLastHistory(String pUserName);

    List getTenHistory(int pPage, int pCount, String pUserName);
}
