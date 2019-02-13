package by.parakhonka.reverse.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthService {
    void logout(HttpServletRequest pRequest, HttpServletResponse pResponse);

    String getUserName();
}
