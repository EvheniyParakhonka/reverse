package by.parakhonka.springsecurity.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthService {
    void logout(HttpServletRequest request, HttpServletResponse response);

    String getUserName();
}
