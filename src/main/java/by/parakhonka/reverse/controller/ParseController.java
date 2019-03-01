package by.parakhonka.reverse.controller;

import by.parakhonka.reverse.model.RequestModelJsonXml;
import by.parakhonka.reverse.service.IReformatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value = "parse")
public class ParseController {
    private final IReformatService mIReformatService;

    @Autowired
    public ParseController(IReformatService pIReformatService) {
        mIReformatService = pIReformatService;
    }

    /**
     * request handle to reformat json to xml
     *
     * @return xml string
     */
    @RequestMapping(value = "/to-xml", method = RequestMethod.POST)
    @ResponseBody
    public String jsonToXml(@RequestBody RequestModelJsonXml pPojo) {
        return mIReformatService.jsonToXmlRef(pPojo);
    }

    /**
     * request handle to reformat xml to json
     *
     * @return json string
     */
    @RequestMapping(value = "/to-json", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String xmlToJson(@RequestBody RequestModelJsonXml pModelJsonXml) {
        return mIReformatService.xmlToJsonRef(pModelJsonXml);
    }
}
