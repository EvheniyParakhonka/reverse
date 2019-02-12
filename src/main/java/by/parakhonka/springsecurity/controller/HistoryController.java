package by.parakhonka.springsecurity.controller;

import by.parakhonka.springsecurity.entity.History;
import by.parakhonka.springsecurity.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HistoryController {

    @Autowired
    IHistoryService mIHistoryService;

    @RequestMapping(value = "/historyID", method = RequestMethod.GET)
    @ResponseBody
    public History getHistory(@RequestParam(value = "id") int id) {
        History history = mIHistoryService.getByidHistory(id);
        return history;
    }

    @RequestMapping(value = "history/ten", method = RequestMethod.GET)
    @ResponseBody
    public List<History> getTenHistory(@RequestParam(value = "page") int pPage,
                                       @RequestParam(value = "count") int pCount) {
        List<History> list = mIHistoryService.getTenHistoryByPage(pPage, pCount);
        return list;
    }


    @RequestMapping(value = "/history/pages", method = RequestMethod.GET)
    @ResponseBody
    public int getPages(@RequestParam(value = "count") int pCount) {
        int page = mIHistoryService.getAllHistoriUser().size() / pCount;
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    @RequestMapping(value = "/history/last", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<History> history() {
        History history = mIHistoryService.getLastHistory();
        return new ResponseEntity<History>(history, HttpStatus.OK);
    }
}
