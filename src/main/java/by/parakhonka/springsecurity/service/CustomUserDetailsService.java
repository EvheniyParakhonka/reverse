package by.parakhonka.springsecurity.service;

import by.parakhonka.springsecurity.model.User;
import by.parakhonka.springsecurity.model.Role;
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
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String pS) throws UsernameNotFoundException {
        System.out.println("loadByusername");
        // с помощью нашего сервиса UserService получаем User
        User user = userService.findBySso(pS);

        System.out.println(user.getUsername());
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        // указываем роли для этого пользователя
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(Role.USER.name()));

        // на основании полученных данных формируем объект UserDetails
        // который позволит проверить введенный пользователем логин и пароль
        // и уже потом аутентифицировать пользователя
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(),
                        user.getPassword(),
                        roles);

        return userDetails;
    }
    }

//    @Transactional(readOnly = true)
//
//    public UserDetails loadUserByUsername(String ssoId)
//            throws UsernameNotFoundException {
//        User user = userService.findBySso(ssoId);
//        System.out.println("User : " + user);
//        if (user == null) {
//            System.out.println("User not found");
//            throw new UsernameNotFoundException("Username not found");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                true, true, true, true, getGrantedAuthorities(user));
//    }
//
//
//    private List<GrantedAuthority> getGrantedAuthorities(User user) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//
//
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoles()));
//
//        System.out.print("authorities :" + authorities);
//        return authorities;
//    }
//}
