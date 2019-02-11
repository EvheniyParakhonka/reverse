package by.parakhonka.springsecurity.service;

import by.parakhonka.springsecurity.model.History;

import java.util.List;

public interface IHistoryService {

    void saveHistory(String pJson, String pXml);
    List<History> getAllHistoriUser();
    History getLastHistory();
    History getByidHistory(int id);
String getUserName();
    List<History> getTenHistoryByPage(int pPage);
}
