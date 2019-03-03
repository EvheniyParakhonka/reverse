package by.parakhonka.reverse.service.impl;

import by.parakhonka.reverse.model.LoginResponse;
import by.parakhonka.reverse.model.UserLogin;
import by.parakhonka.reverse.service.IAuthService;
import by.parakhonka.reverse.service.IRegistrationService;
import by.parakhonka.reverse.util.Const;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service("authService")
public class AuthServiceImpl implements IAuthService {
    private final IRegistrationService mRegistrationService;

    @Autowired
    public AuthServiceImpl(IRegistrationService mRegistrationService) {
        this.mRegistrationService = mRegistrationService;
    }

    /**
     * @see IAuthService
     */
    public void logout(HttpServletRequest pRequest, HttpServletResponse pResponse) {

        if (getAuth() != null) {
            new SecurityContextLogoutHandler().logout(pRequest, pResponse, getAuth());
        }
    }

    @Override
    public LoginResponse login(UserLogin pLogin) {
        return new LoginResponse(Jwts.builder().setSubject(pLogin.userName)
                .claim("roles", mRegistrationService.checkUserExist(pLogin.userName)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, Const.MyKeyMD5).compact());
    }

    public String getUserName() {
        UserDetails user = (UserDetails) getAuth().getPrincipal();
        return user.getUsername();
    }

    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
