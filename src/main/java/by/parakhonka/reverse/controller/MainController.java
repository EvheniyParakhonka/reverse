package by.parakhonka.reverse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    /**
     * handle request main
     *
     * @return main.jsp
     */
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage() {
        return "main";
    }

    @RequestMapping(value = "/*")
    public ModelAndView handleControllerException(HttpServletRequest req, Throwable ex) {
        logger.error("Handle error " + "page not found");
        ModelAndView mav = new ModelAndView("error");
        mav.getModelMap().addAttribute("message", "404 not found");
        return mav;
    }
}