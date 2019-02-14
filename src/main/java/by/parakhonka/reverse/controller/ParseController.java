package by.parakhonka.reverse.controller;

import by.parakhonka.reverse.service.IReformatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
     * @param pJson string json from frontend
     * @return xml string
     */
    @RequestMapping(value = "/to-xml", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String jsonToXml(@RequestBody String pJson) {
        return mIReformatService.jsonToXmlRef(pJson);
    }

    /**
     * request handle to reformat xml to json
     *
     * @param pXmlString xml string
     * @return json string
     */
    @RequestMapping(value = "/to-json", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String xmlToJson(@RequestBody String pXmlString) {
        return mIReformatService.jsonToXmlRef(pXmlString);
    }
}
