package by.parakhonka.reverse.service;

public interface IReformatService {
    /**
     * transform string json to string xml
     *
     * @param pJson string json
     * @return string xml
     */
    String jsonToXmlRef(String pJson);

    /**
     * transform string xml to string json
     *
     * @param pXml string xml
     * @return string json
     */
    String xmlToJsonRef(String pXml);
}
