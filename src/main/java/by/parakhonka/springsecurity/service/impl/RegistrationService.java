package by.parakhonka.springsecurity.service.impl;

import by.parakhonka.springsecurity.dao.IUserDao;
import by.parakhonka.springsecurity.entity.Role;
import by.parakhonka.springsecurity.entity.User;
import by.parakhonka.springsecurity.service.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RegistrationService implements IRegistrationService {
    private final IUserDao mIUserDao;
    private final PasswordEncoder mPasswordEncoder;

    @Autowired
    public RegistrationService(IUserDao pIUserDao, PasswordEncoder pPasswordEncoder) {
        mIUserDao = pIUserDao;
        mPasswordEncoder = pPasswordEncoder;
    }

    public User checkUserExist(String pName) {
        return mIUserDao.findBySSO(pName);
    }

    public void saveUser(User pUser) {
        pUser.setActive(true);
        pUser.setRoles(Collections.singleton(Role.USER));
        pUser.setPassword(mPasswordEncoder.encode(pUser.getPassword()));
        mIUserDao.save(pUser);
    }
}
