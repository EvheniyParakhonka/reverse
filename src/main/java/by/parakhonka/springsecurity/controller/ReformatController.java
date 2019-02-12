package by.parakhonka.springsecurity.controller;

import by.parakhonka.springsecurity.service.IHistoryService;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReformatController {
    @Autowired
    IHistoryService mIHistoryService;

    @RequestMapping(value = "/json", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String jsonToXml(@RequestBody String json) {
        JSONObject jsonO = new JSONObject(json);
        String xml = XML.toString(jsonO);
        mIHistoryService.saveHistory(xml, json);
        System.out.println(xml);
        System.out.println(json);

        return xml;
    }

    @RequestMapping(value = "/xml", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String xmlToJson(@RequestBody String xmlString) {
        JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        mIHistoryService.saveHistory(xmlString, jsonPrettyPrintString);
        System.out.println(xmlString);
        System.out.println(jsonPrettyPrintString);

        return jsonPrettyPrintString;
    }
}
