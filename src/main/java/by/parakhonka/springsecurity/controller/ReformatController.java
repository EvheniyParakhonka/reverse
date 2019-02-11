package by.parakhonka.springsecurity.controller;

import by.parakhonka.springsecurity.model.History;
import by.parakhonka.springsecurity.service.IHistoryService;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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


    @RequestMapping(value = "/historyID", method = RequestMethod.GET)
    @ResponseBody
    public History getHistory(@RequestParam(value = "id") int id) {
        History history = mIHistoryService.getByidHistory(id);
        return history;
    }

    @RequestMapping(value = "history/ten", method = RequestMethod.GET)
    @ResponseBody
    public List<History> getTenHistory(@RequestParam(value = "page") int pPage) {
        List<History> list = mIHistoryService.getTenHistoryByPage(pPage);
        return list;
    }


    @RequestMapping(value = "/history/pages", method = RequestMethod.GET)
    @ResponseBody
    public int getPages() {
        int page = mIHistoryService.getAllHistoriUser().size() / 10;
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<History> history() {
        History history = mIHistoryService.getLastHistory();
        return new ResponseEntity<History>(history, HttpStatus.OK);
    }


}
