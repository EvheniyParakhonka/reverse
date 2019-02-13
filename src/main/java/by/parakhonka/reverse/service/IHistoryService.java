package by.parakhonka.reverse.service;

import by.parakhonka.reverse.entity.History;

import java.util.List;

public interface IHistoryService {

    void saveHistory(String pJson, String pXml);

    History getLastHistory();

    History getByIdHistory(int pId);

    int getNumberOfPageHistory(int pCount);

    List getTenHistoryByPage(int pPage, int pCount);
}
