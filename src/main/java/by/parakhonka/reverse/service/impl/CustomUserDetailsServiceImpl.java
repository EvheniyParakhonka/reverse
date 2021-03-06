package by.parakhonka.reverse.service.impl;

import by.parakhonka.reverse.dao.IUserDao;
import by.parakhonka.reverse.entity.Role;
import by.parakhonka.reverse.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final IUserDao mUserDao;

    @Autowired
    public CustomUserDetailsServiceImpl(IUserDao mIUserDao) {
        this.mUserDao = mIUserDao;
    }

    /**
     * loaded user and say spring security
     *
     * @param pS user name
     * @return user details
     * @throws UsernameNotFoundException if can't find user
     */
    public UserDetails loadUserByUsername(String pS) throws UsernameNotFoundException {
        System.out.println("load By user name");
        User user = mUserDao.findByUserName(pS);

        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(Role.USER.name()));


        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(),
                        user.getPassword(),
                        roles);

        return userDetails;
    }
}
