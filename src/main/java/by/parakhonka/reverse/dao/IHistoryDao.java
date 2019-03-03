package by.parakhonka.reverse.dao;

import by.parakhonka.reverse.entity.History;

import java.util.List;

public interface IHistoryDao {
    /**
     * saved history in db
     *
     * @param pHistory history to save
     */
    void save(History pHistory);

    /**
     * takes all the records by userName user
     *
     * @param pUserName to get only user history
     * @return list history user
     */
    List<History> getAllHistoryUser(String pUserName);

    /**
     * takes only one record by id
     *
     * @param pId to get only one history
     * @return one history
     */
    History getByIdHistory(int pId);

    /**
     * sort by id and get last added history
     *
     * @param pUserName user userName
     * @return return only one lastet added history
     */
    History getLastHistory(String pUserName);

    /**
     * get a certain number of stories for one page
     *
     * @param pPage     number of page
     * @param pCount    how many records at one page
     * @param pUserName to get only user records
     * @return list history users
     */
    List getHistoryForOnePage(int pPage, int pCount, String pUserName);
}
