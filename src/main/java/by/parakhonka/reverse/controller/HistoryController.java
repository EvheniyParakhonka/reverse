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

    /**
     * get one history where id is id
     *
     * @param id get id from request
     * @return return one history
     */
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseBody
    public History getByIdHistory(@RequestParam(value = "id") int id) {
        return mHistoryService.getByIdHistory(id);
    }

    /**
     * @param pPage  number page
     * @param pCount how many history on 1 page
     * @return result list history on one page
     */
    @RequestMapping(value = "/stories", method = RequestMethod.GET)
    @ResponseBody
    public List<History> getStories(@RequestParam(value = "page") int pPage,
                                    @RequestParam(value = "count") int pCount) {
        return mHistoryService.getHistoryOnOnePage(pPage, pCount);
    }

    /**
     * Method return number how many page, by record count in bd
     *
     * @param pCount how many on 1 page
     * @return how many page
     */
    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    @ResponseBody
    public int getPages(@RequestParam(value = "count") int pCount) {
        return mHistoryService.getNumberOfPageHistory(pCount);
    }

    /**
     * get and return lasted added record in db
     *
     * @return last added record of history
     */
    @RequestMapping(value = "/last", method = RequestMethod.GET)
    @ResponseBody
    public History getLastHistory() {
        return mHistoryService.getLastHistory();
    }
}
