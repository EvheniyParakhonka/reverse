package by.parakhonka.reverse.service;

import by.parakhonka.reverse.model.RequestModelJsonXml;

public interface IReformatService {
    /**
     * model have checked boolean to save, or not save xml
     *
     * @param pJson model json
     * @return string xml
     */
    String jsonToXmlRef(RequestModelJsonXml pJson);

    /**
     * transform string xml to string json and save or not
     *
     * @param pXml model have checked boolean to save, or not save xml
     * @return string json
     */
    String xmlToJsonRef(RequestModelJsonXml pXml);
}
