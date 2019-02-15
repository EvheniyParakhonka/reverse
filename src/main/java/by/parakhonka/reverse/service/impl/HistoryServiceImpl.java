package by.parakhonka.reverse.service.impl;

import by.parakhonka.reverse.dao.IHistoryDao;
import by.parakhonka.reverse.entity.History;
import by.parakhonka.reverse.service.IAuthService;
import by.parakhonka.reverse.service.IHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional
public class HistoryServiceImpl implements IHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(HistoryServiceImpl.class);
    private final IHistoryDao mHistoryDao;
    private final IAuthService mAuthService;

    @Autowired
    public HistoryServiceImpl(IHistoryDao pHistoryDao, IAuthService pAuthService) {
        this.mHistoryDao = pHistoryDao;
        this.mAuthService = pAuthService;
    }

    /**
     * @see IHistoryService
     */
    public void saveHistory(String pXml, String pJson) {
        logger.debug("save history method");
        History history = new History();
        history.setJson(pJson);
        history.setXml(pXml);
        logger.debug("set json and xml");
        history.setDate(Calendar.getInstance(TimeZone.getTimeZone("GMT+3")).getTimeInMillis());
        history.setName(mAuthService.getUserName());
        mHistoryDao.save(history);
    }

    public History getLastHistory() {
        return mHistoryDao.getLastHistory(mAuthService.getUserName());
    }

    public History getByIdHistory(int pId) {
        return mHistoryDao.getByIdHistory(pId);
    }

    public int getNumberOfPageHistory(int pCount) {
        int page = mHistoryDao.getAllHistoryUser(mAuthService.getUserName()).size() / pCount;
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    public List<History> getHistoryOnOnePage(int pPage, int pCount) {
        return mHistoryDao.getHistoryForOnePage(pPage, pCount, mAuthService.getUserName());
    }
}
