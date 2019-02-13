package by.parakhonka.reverse.controller;

import by.parakhonka.reverse.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    private final IAuthService mIAuthService;

    @Autowired
    public AuthController(IAuthService mIAuthService) {
        this.mIAuthService = mIAuthService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        mIAuthService.logout(request, response);
        return "redirect:/login?logout";
    }
}
