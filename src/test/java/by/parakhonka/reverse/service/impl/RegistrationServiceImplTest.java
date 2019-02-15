package by.parakhonka.reverse.service.impl;

import by.parakhonka.reverse.configuration.AppConfiguration;
import by.parakhonka.reverse.dao.IUserDao;
import by.parakhonka.reverse.entity.Role;
import by.parakhonka.reverse.entity.User;
import by.parakhonka.reverse.service.IRegistrationService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class RegistrationServiceImplTest {

    @Autowired
    IRegistrationService mRegistrationService;
    @MockBean
    IUserDao mUserDao;
    @MockBean
    PasswordEncoder mCryptPasswordEncoder;

    @Test
    public void checkUserExist() {
        mRegistrationService.checkUserExist("John");
        Mockito.verify(mUserDao, Mockito.times(1)).findByUserName("John");
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("john");
        mRegistrationService.saveUser(user);

        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(mUserDao, Mockito.times(1)).save(user);
    }
}