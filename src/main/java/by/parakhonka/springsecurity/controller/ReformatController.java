package by.parakhonka.springsecurity.controller;

import by.parakhonka.springsecurity.service.IReformatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReformatController {
    private final IReformatService mIReformatService;

    @Autowired
    public ReformatController(IReformatService pIReformatService) {
        mIReformatService = pIReformatService;
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String jsonToXml(@RequestBody String pJson) {
        return mIReformatService.jsonToXmlRef(pJson);
    }

    @RequestMapping(value = "/xml", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String xmlToJson(@RequestBody String pXmlString) {
        return mIReformatService.jsonToXmlRef(pXmlString);
    }
}
