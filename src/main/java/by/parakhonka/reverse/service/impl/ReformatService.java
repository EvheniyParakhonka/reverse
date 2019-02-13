package by.parakhonka.reverse.service.impl;

import by.EvheniyParakhonka.JSONObject;
import by.EvheniyParakhonka.XML;
import by.parakhonka.reverse.service.IHistoryService;
import by.parakhonka.reverse.service.IReformatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class ReformatService implements IReformatService {

    private final IHistoryService mHistoryService;

    @Autowired
    public ReformatService(IHistoryService pHistoryService) {
        this.mHistoryService = pHistoryService;
    }

    public String jsonToXmlRef(String pJson) {
        JSONObject jsonO = new JSONObject(pJson);
        String xml = XML.toString(jsonO);
        mHistoryService.saveHistory(xml, pJson);
        return xml;
    }

    public String xmlToJsonRef(String pXml) {
        JSONObject object = XML.toJSONObject(pXml);
        String json = object.toString(4);
        mHistoryService.saveHistory(pXml, json);
        return json;
    }
}
