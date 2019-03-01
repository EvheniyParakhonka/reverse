package by.parakhonka.reverse.auth;

import by.parakhonka.reverse.service.impl.CustomUserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class JwtFilter extends GenericFilterBean {

    public static final String MY_SECRET_KEY = "mySecretKeyToAuthorizateItProbeToHave256AndSomthongMore";
    @Autowired
    CustomUserDetailsServiceImpl customUserDetailsService;

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException, java.io.IOException {
        final HttpServletRequest request = (HttpServletRequest) req;

        final String authHeader = request.getHeader("authorization");
        if (authHeader != null) {

            final String token = authHeader.substring(7); // The part after "Bearer "
            if (!token.equals("null")){
                try {
                    final Claims claims = Jwts.parser().setSigningKey(MY_SECRET_KEY)
                            .parseClaimsJws(token).getBody();
                    request.setAttribute("claims", claims);
                    Authentication auth = getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } catch (final SignatureException e) {
                    throw new ServletException("Invalid token.");
                }
        }}


        chain.doFilter(req, res);
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        String sub = Jwts.parser().setSigningKey(MY_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
        return sub;
    }
}
