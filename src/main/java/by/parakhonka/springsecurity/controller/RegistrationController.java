package by.parakhonka.springsecurity.controller;


import by.parakhonka.springsecurity.entity.User;
import by.parakhonka.springsecurity.service.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
    private final IRegistrationService mIRegistrationService;

    @Autowired
    public RegistrationController(IRegistrationService pIRegistrationService) {
        mIRegistrationService = pIRegistrationService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(User user) {

        User userFromDb = mIRegistrationService.checkUserExist(user.getUsername());
        if (userFromDb != null) {
            return "registration";
        }
        mIRegistrationService.saveUser(user);
        return "redirect:/login";
    }
}
