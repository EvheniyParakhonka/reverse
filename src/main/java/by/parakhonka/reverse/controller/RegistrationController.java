package by.parakhonka.reverse.controller;


import by.parakhonka.reverse.entity.User;
import by.parakhonka.reverse.service.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "registration")
public class RegistrationController {
    private final IRegistrationService mIRegistrationService;

    @Autowired
    public RegistrationController(IRegistrationService pIRegistrationService) {
        mIRegistrationService = pIRegistrationService;
    }

    /**
     * @return registration.jsp
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationPage() {
        return "registration";
    }

    /**
     * check user exist, if not save user in db
     *
     * @param user object user to save
     * @return if exist return registration else redirect to login page
     */
    @RequestMapping(method = RequestMethod.POST)
    public String registrationUser(User user) {
        User userFromDb = mIRegistrationService.checkUserExist(user.getUsername());
        if (userFromDb != null) {
            return "registration";
        }
        mIRegistrationService.saveUser(user);
        return "redirect:/login";
    }
}
