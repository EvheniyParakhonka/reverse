package by.parakhonka.reverse.service.impl;

import by.parakhonka.reverse.configuration.AppConfiguration;
import by.parakhonka.reverse.dao.IHistoryDao;
import by.parakhonka.reverse.entity.History;
import by.parakhonka.reverse.service.IAuthService;
import by.parakhonka.reverse.service.IHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = AppConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class IHistoryServiceTest {
    @Autowired
    private IHistoryService mHistoryService;
    @MockBean
    private IHistoryDao mHistoryDao;
    @MockBean
    private IAuthService mAuthService;
    @Captor
    ArgumentCaptor<History> mHistory;


    @Test
    public void saveHistory() {

        mHistoryService.saveHistory("ss", "ss");
        Mockito.verify(mHistoryDao).save(mHistory.capture());
        assertEquals("ss", mHistory.getValue().getJson());
    }

    @Test
    public void getNumberOfPage() {
        List<History> histories = new ArrayList<>();
        int page = mHistoryService.getNumberOfPageHistory(20);
        Mockito.when(mHistoryDao.getAllHistoryUser(mAuthService.getUserName())).thenReturn(histories);
        assertEquals(page, 1);
    }


}