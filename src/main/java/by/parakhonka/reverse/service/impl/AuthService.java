package by.parakhonka.reverse.service.impl;

import by.parakhonka.reverse.service.IAuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("authService")
public class AuthService implements IAuthService {

    public void logout(HttpServletRequest pRequest, HttpServletResponse pResponse) {

        if (getAuth() != null) {
            new SecurityContextLogoutHandler().logout(pRequest, pResponse, getAuth());
        }
    }

    public String getUserName() {
        UserDetails user = (UserDetails) getAuth().getPrincipal();
        return user.getUsername();
    }

    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
