package by.parakhonka.springsecurity.controller;


import by.parakhonka.springsecurity.dao.UserDao;
import by.parakhonka.springsecurity.model.Role;
import by.parakhonka.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserDao mUserRepo;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(  User user, Map<String, Object> model) {
        System.out.println(user.getUsername());
        User userFromDb = mUserRepo.findBySSO(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        mUserRepo.save(user);

        return "redirect:/login";
    }
}
