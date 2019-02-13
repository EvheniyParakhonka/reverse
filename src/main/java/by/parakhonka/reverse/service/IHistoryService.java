package by.parakhonka.reverse.service;

import by.parakhonka.reverse.entity.History;

import java.util.List;

public interface IHistoryService {
    /**
     * gives history to save in dao
     *
     * @param pJson string json to save
     * @param pXml  string xml to save
     */
    void saveHistory(String pJson, String pXml);

    /**
     * get last added history
     *
     * @return last added history
     */
    History getLastHistory();

    /**
     * get history with id
     *
     * @param pId to get history
     * @return one history where id = pId
     */
    History getByIdHistory(int pId);

    /**
     * get how many page with history
     *
     * @param pCount how many story in one page
     * @return how many pages
     */
    int getNumberOfPageHistory(int pCount);

    /**
     * get history to be seen in page
     *
     * @param pPage  number page
     * @param pCount how many history in one page
     * @return list of history to be see
     */
    List getHistoryOnOnePage(int pPage, int pCount);
}
