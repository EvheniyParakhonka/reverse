package by.parakhonka.springsecurity.service;

import by.parakhonka.springsecurity.model.History;

import java.util.List;

public interface IHistoryService {

    void save(History pHistory);
    List<History> getAllHistoriUser(String userName);
    History getLastHistory(String userName);
    History getByidHistory(int id);
}
