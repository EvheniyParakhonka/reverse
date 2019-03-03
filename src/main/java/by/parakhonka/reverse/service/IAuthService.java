package by.parakhonka.reverse.service;

import by.parakhonka.reverse.model.LoginResponse;
import by.parakhonka.reverse.model.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthService {
    /**
     * @param pRequest  information from a computer client to server
     * @param pResponse answer from server
     */
    void logout(HttpServletRequest pRequest, HttpServletResponse pResponse);

    /**
     * get login and password check if exist create token and return codding token
     *
     * @param pLogin login and password
     * @return token
     */
    LoginResponse login(final UserLogin pLogin);

    /**
     * get user userName from auth spring security
     *
     * @return user userName
     */
    String getUserName();
}
