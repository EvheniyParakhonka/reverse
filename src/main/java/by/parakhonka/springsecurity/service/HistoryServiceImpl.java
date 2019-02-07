package by.parakhonka.springsecurity.service;

import by.parakhonka.springsecurity.dao.IHistoryDao;
import by.parakhonka.springsecurity.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    IHistoryDao mIHistoryDao;

    public void save(History pHistory) {
        System.out.println(pHistory.getJson());
        mIHistoryDao.save(pHistory);
    }

    public List<History> getAllHistoriUser(String userName) {
        System.out.println(userName);
        List<History> list = mIHistoryDao.getAllHistoriUser(userName);
        System.out.println(list.get(0));
        return list;
    }

    public History getLastHistory(String userName) {
        History history = mIHistoryDao.getLastHistory(userName);
        return history;
    }

    public History getByidHistory(int id) {
        History history = mIHistoryDao.getByidHistory(id);
        return history;
    }
}
