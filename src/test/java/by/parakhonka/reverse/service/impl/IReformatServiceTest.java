package by.parakhonka.reverse.service.impl;

import by.parakhonka.reverse.configuration.AppConfiguration;
import by.parakhonka.reverse.model.RequestModelJsonXml;
import by.parakhonka.reverse.service.IHistoryService;
import by.parakhonka.reverse.service.IReformatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = AppConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class IReformatServiceTest {

    @Autowired
    private IReformatService mReformatService;
    @MockBean
    private IHistoryService mHistoryService;

    @Test
    public void jsonToXmlRef() {
        RequestModelJsonXml requestModelJsonXml = new RequestModelJsonXml();
        requestModelJsonXml.setReformatValue("{\"id\":25}");
        requestModelJsonXml.setCheckedToSave(false);
        assertEquals(mReformatService.jsonToXmlRef(requestModelJsonXml), "<id>25</id>");
    }

    @Test
    public void xmlToJsonRef() {
        RequestModelJsonXml requestModelJsonXml = new RequestModelJsonXml();
        requestModelJsonXml.setReformatValue("<id>43</id>");
        requestModelJsonXml.setCheckedToSave(false);
        assertEquals(mReformatService.xmlToJsonRef(requestModelJsonXml), "{\"id\": 43}");
    }

}