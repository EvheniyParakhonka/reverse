package by.parakhonka.springsecurity.controller;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReformatController {

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    public String jsonToXml(@RequestBody String json){
        JSONObject jsonO = new JSONObject(json);
        String xml = XML.toString(jsonO);


        System.out.println(xml);
        System.out.println(json);

        return xml;
    }
    @RequestMapping(value = "/xml", method = RequestMethod.POST)
    @ResponseBody
    public String xmlToJson(@RequestBody String xmlString){
        JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);


        System.out.println(xmlString);
        System.out.println(jsonPrettyPrintString);

        return jsonPrettyPrintString;
    }
}
