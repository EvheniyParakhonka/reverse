package by.parakhonka.reverse.service.impl;

import by.parakhonka.reverse.dao.IUserDao;
import by.parakhonka.reverse.entity.Role;
import by.parakhonka.reverse.entity.User;
import by.parakhonka.reverse.service.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RegistrationService implements IRegistrationService {
    private final IUserDao mUserDao;
    private final PasswordEncoder mPasswordEncoder;

    @Autowired
    public RegistrationService(IUserDao pUserDao, PasswordEncoder pPasswordEncoder) {
        mUserDao = pUserDao;
        mPasswordEncoder = pPasswordEncoder;
    }

    public User checkUserExist(String pName) {
        return mUserDao.findByUserName(pName);
    }

    public void saveUser(User pUser) {
        pUser.setActive(true);
        pUser.setRoles(Collections.singleton(Role.USER));
        pUser.setPassword(mPasswordEncoder.encode(pUser.getPassword()));
        mUserDao.save(pUser);
    }
}
