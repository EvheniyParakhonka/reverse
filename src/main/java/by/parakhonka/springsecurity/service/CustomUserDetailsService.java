package by.parakhonka.springsecurity.service;

import by.parakhonka.springsecurity.entity.User;
import by.parakhonka.springsecurity.entity.Role;
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
    private IUserService mIUserService;

    public UserDetails loadUserByUsername(String pS) throws UsernameNotFoundException {
        System.out.println("load By user name");
        // с помощью нашего сервиса IUserService получаем User
        User user = mIUserService.findBySso(pS);

        System.out.println(user.getUsername());
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }

        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(Role.USER.name()));


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
//        User user = mIUserService.findBySso(ssoId);
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
