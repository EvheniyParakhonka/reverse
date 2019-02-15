package by.parakhonka.reverse.service.impl;

import by.EvheniyParakhonka.JSONObject;
import by.EvheniyParakhonka.XML;
import by.parakhonka.reverse.model.RequestModelJsonXml;
import by.parakhonka.reverse.service.IHistoryService;
import by.parakhonka.reverse.service.IReformatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReformatServiceImpl implements IReformatService {
    private static final Logger logger = LoggerFactory.getLogger(ReformatServiceImpl.class);

    private final IHistoryService mHistoryService;

    @Autowired
    public ReformatServiceImpl(IHistoryService pHistoryService) {
        this.mHistoryService = pHistoryService;
    }

    /**
     * @see IReformatService
     */
    public String jsonToXmlRef(RequestModelJsonXml pJson) {
        logger.info("json to xml method");
        try {
            JSONObject jsonO = new JSONObject(pJson.getReformatValue());
            String xml = XML.toString(jsonO);
            logger.info("xml in json to xml method: " + xml);
            if (pJson.isCheckedToSave()) {
                mHistoryService.saveHistory(xml, pJson.getReformatValue());
            }
            return xml;
        } catch (Exception pE) {
            logger.error(pE.getMessage());
            return pE.getMessage();
        }
    }

    public String xmlToJsonRef(RequestModelJsonXml pXml) {
        logger.info("xml to json method");
        try {
            JSONObject object = XML.toJSONObject(pXml.getReformatValue());
            String json = object.toString(4);
            if (pXml.isCheckedToSave()) {
                mHistoryService.saveHistory(pXml.getReformatValue(), json);
            }
            return json;
        } catch (Exception pE) {
            return pE.getMessage();
        }

    }
}
