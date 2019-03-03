package by.parakhonka.reverse.controller;

import by.parakhonka.reverse.model.LoginResponse;
import by.parakhonka.reverse.model.UserLogin;
import by.parakhonka.reverse.service.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final IAuthService mIAuthService;

    @Autowired
    public AuthController(IAuthService mIAuthService) {
        this.mIAuthService = mIAuthService;
    }

    /**
     * handle request to get login page
     *
     * @return login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResponse login(@RequestBody final UserLogin pLogin) {
        logger.info("login with name and password: " + pLogin.userName + " " + pLogin.password);
        return mIAuthService.login(pLogin);
    }

    /**
     * uses to logout from server
     *
     * @return redirect to login page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("logout page");
        mIAuthService.logout(request, response);
        return "redirect:/login?logout";
    }
}
