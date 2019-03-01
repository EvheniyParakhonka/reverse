package by.parakhonka.reverse.controller;

import by.parakhonka.reverse.dao.IUserDao;
import by.parakhonka.reverse.service.IAuthService;
import by.parakhonka.reverse.service.IRegistrationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final IAuthService mIAuthService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    IRegistrationService mRegistrationService;
    @Autowired
    IUserDao users;

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
    public LoginResponse login(@RequestBody final UserLogin login)
            throws ServletException {

        return new LoginResponse(Jwts.builder().setSubject(login.userName)
                .claim("roles", mRegistrationService.checkUserExist(login.userName)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "mySecretKeyToAuthorizateItProbeToHave256AndSomthongMore").compact());
    }

    @SuppressWarnings("unused")
    private static class UserLogin {
        public String userName;
        public String password;
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }


    /**
     * uses to logout from server
     *
     * @param request
     * @param response
     * @return redirect to login page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("logout page");
        mIAuthService.logout(request, response);
        return "redirect:/login?logout";
    }
}
