package by.parakhonka.reverse.service.impl;

import by.parakhonka.reverse.configuration.AppConfiguration;
import by.parakhonka.reverse.dao.IHistoryDao;
import by.parakhonka.reverse.service.IHistoryService;
import by.parakhonka.reverse.service.IReformatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@ContextConfiguration(classes = AppConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class IReformatServiceTest {

    @Autowired
    private IReformatService mReformatService;
    @MockBean
    private IHistoryService mHistoryService;

    @Test
    public void jsonToXmlRef(){
       assertEquals(mReformatService.jsonToXmlRef("{\"id\":25}"),"<id>25</id>");
    }

    @Test
    public void xmlToJsonRef(){
        assertEquals(mReformatService.xmlToJsonRef("<id>43</id>"),"{\"id\": 43}");
    }

}