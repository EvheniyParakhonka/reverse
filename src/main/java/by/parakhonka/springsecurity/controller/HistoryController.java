package by.parakhonka.springsecurity.controller;

import by.parakhonka.springsecurity.entity.History;
import by.parakhonka.springsecurity.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return mIHistoryService.getByidHistory(id);
    }

    @RequestMapping(value = "history/ten", method = RequestMethod.GET)
    @ResponseBody
    public List<History> getTenHistory(@RequestParam(value = "page") int pPage,
                                       @RequestParam(value = "count") int pCount) {
        return mIHistoryService.getTenHistoryByPage(pPage, pCount);
    }


    @RequestMapping(value = "/history/pages", method = RequestMethod.GET)
    @ResponseBody
    public int getPages(@RequestParam(value = "count") int pCount) {
        return mIHistoryService.getNumberOfPageHistory(pCount);
    }

    @RequestMapping(value = "/history/last", method = RequestMethod.GET)
    @ResponseBody
    public History history() {
        return mIHistoryService.getLastHistory();
    }
}
