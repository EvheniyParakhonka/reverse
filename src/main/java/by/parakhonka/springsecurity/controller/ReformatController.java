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
        saveHistory(xml, json);
        System.out.println(xml);
        System.out.println(json);

        return xml;
    }
    @RequestMapping(value = "/xml", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String xmlToJson(@RequestBody String xmlString) {
        JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        saveHistory(xmlString, jsonPrettyPrintString);
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

    @RequestMapping(value = "history/all")
    @ResponseBody
    public List<History> getAllHistory() {
        List<History> list = mIHistoryService.getAllHistoriUser(getUserName());
        return list;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<History> history() {
        History history = mIHistoryService.getLastHistory(getUserName());
        return new ResponseEntity<History>(history, HttpStatus.OK);
    }


    private String getUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        UserDetails user = (UserDetails) auth.getPrincipal();
        return user.getUsername();
    }

    private void saveHistory(String pXml, String pJson) {
        History history = new History();
        history.setJson(pJson);
        history.setXml(pXml);
        history.setDate(Calendar.getInstance(TimeZone.getTimeZone("GMT+3")).getTimeInMillis());
        history.setName(getUserName());
        mIHistoryService.save(history);
    }
}
