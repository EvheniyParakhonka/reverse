package by.parakhonka.reverse.controller;

import by.parakhonka.reverse.entity.History;
import by.parakhonka.reverse.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/history")
public class HistoryController {

    private final IHistoryService mHistoryService;

    @Autowired
    public HistoryController(IHistoryService pHistoryService) {
        this.mHistoryService = pHistoryService;
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseBody
    public History getByIdHistory(@RequestParam(value = "id") int id) {
        return mHistoryService.getByIdHistory(id);
    }

    //    return Stories on one page
    @RequestMapping(value = "/stories", method = RequestMethod.GET)
    @ResponseBody
    public List<History> getStories(@RequestParam(value = "page") int pPage,
                                    @RequestParam(value = "count") int pCount) {
        return mHistoryService.getTenHistoryByPage(pPage, pCount);
    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    @ResponseBody
    public int getPages(@RequestParam(value = "count") int pCount) {
        return mHistoryService.getNumberOfPageHistory(pCount);
    }

    @RequestMapping(value = "/last", method = RequestMethod.GET)
    @ResponseBody
    public History getLastHistory() {
        return mHistoryService.getLastHistory();
    }
}
