package by.parakhonka.reverse.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthService {
    /**
     * @param pRequest  information from a computer client to server
     * @param pResponse answer from server
     */
    void logout(HttpServletRequest pRequest, HttpServletResponse pResponse);

    /**
     * get user name from auth spring security
     *
     * @return user name
     */
    String getUserName();
}
