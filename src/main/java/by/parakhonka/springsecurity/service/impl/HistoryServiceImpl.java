package by.parakhonka.springsecurity.service.impl;

import by.parakhonka.springsecurity.dao.IHistoryDao;
import by.parakhonka.springsecurity.entity.History;
import by.parakhonka.springsecurity.service.IAuthService;
import by.parakhonka.springsecurity.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional
public class HistoryServiceImpl implements IHistoryService {

    private final IHistoryDao mIHistoryDao;
    private final IAuthService mIAuthService;

    @Autowired
    public HistoryServiceImpl(IHistoryDao mIHistoryDao, IAuthService mIAuthService) {
        this.mIHistoryDao = mIHistoryDao;
        this.mIAuthService = mIAuthService;
    }

    public void saveHistory(String pXml, String pJson) {
        History history = new History();
        history.setJson(pJson);
        history.setXml(pXml);
        history.setDate(Calendar.getInstance(TimeZone.getTimeZone("GMT+3")).getTimeInMillis());
        history.setName(mIAuthService.getUserName());
        mIHistoryDao.save(history);
    }

    public History getLastHistory() {
        return mIHistoryDao.getLastHistory(mIAuthService.getUserName());
    }

    public History getByidHistory(int id) {
        return mIHistoryDao.getByidHistory(id);
    }

    public int getNumberOfPageHistory(int pCount) {
        int page = mIHistoryDao.getAllHistoriUser(mIAuthService.getUserName()).size() / pCount;
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    public List<History> getTenHistoryByPage(int pPage, int pCount) {
        List list = mIHistoryDao.getTenHistory(pPage, pCount, mIAuthService.getUserName());
        return list;
    }
}
