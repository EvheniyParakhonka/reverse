package by.parakhonka.springsecurity.service;

import by.parakhonka.springsecurity.dao.IHistoryDao;
import by.parakhonka.springsecurity.entity.History;
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

    @Autowired
    IHistoryDao mIHistoryDao;

    public void saveHistory(String pXml, String pJson) {
        History history = new History();
        history.setJson(pJson);
        history.setXml(pXml);
        history.setDate(Calendar.getInstance(TimeZone.getTimeZone("GMT+3")).getTimeInMillis());
        history.setName(getUserName());
        mIHistoryDao.save(history);
    }

    public List<History> getAllHistoriUser() {
        System.out.println(getUserName());
        List<History> list = mIHistoryDao.getAllHistoriUser(getUserName());
        System.out.println(list.get(0));
        return list;
    }

    public History getLastHistory() {
        History history = mIHistoryDao.getLastHistory(getUserName());
        return history;
    }

    public History getByidHistory(int id) {
        History history = mIHistoryDao.getByidHistory(id);
        return history;
    }

    public List<History> getTenHistoryByPage(int pPage, int pCount) {
        List<History> list = mIHistoryDao.getTenHistory(pPage,pCount, getUserName());
        return list;
    }

    public String getUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        UserDetails user = (UserDetails) auth.getPrincipal();
        return user.getUsername();
    }
}
