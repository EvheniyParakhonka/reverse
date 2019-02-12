package by.parakhonka.springsecurity.service;

import by.parakhonka.springsecurity.entity.History;

import java.util.List;

public interface IHistoryService {

    void saveHistory(String pJson, String pXml);

    History getLastHistory();

    History getByidHistory(int id);

    int getNumberOfPageHistory(int pCount);

    List getTenHistoryByPage(int pPage, int pCount);
}
