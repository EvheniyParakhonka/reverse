package by.parakhonka.springsecurity.service.impl;

import by.EvheniyParakhonka.JSONObject;
import by.EvheniyParakhonka.XML;
import by.parakhonka.springsecurity.service.IHistoryService;
import by.parakhonka.springsecurity.service.IReformatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class ReformatService implements IReformatService {

    private final IHistoryService mIHistoryService;

    @Autowired
    public ReformatService(IHistoryService mIHistoryService) {
        this.mIHistoryService = mIHistoryService;
    }

    public String jsonToXmlRef(String pJson) {
        JSONObject jsonO = new JSONObject(pJson);
        String xml = XML.toString(jsonO);
        mIHistoryService.saveHistory(xml, pJson);
        return xml;
    }

    public String xmlToJsonRef(String pXml) {
        JSONObject object = XML.toJSONObject(pXml);
        String json = object.toString(4);
        mIHistoryService.saveHistory(pXml, json);
        return json;
    }
}
