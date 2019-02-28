package library.management.system.librarymanagement.security;

import library.management.system.librarymanagement.security_constant.JwtGenerator;
import library.management.system.librarymanagement.service.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static library.management.system.librarymanagement.security_constant.SecurityConstant.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private CustomUserDetailsService customUserDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  CustomUserDetailsService customUserDetailsService) {
        super(authenticationManager);
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request, response);
            throw new RuntimeException("token is missing");
        }

        UsernamePasswordAuthenticationToken upaToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(upaToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {

        String token = request.getHeader(HEADER_STRING);

        if (token == null){
            return null;
        }

        String username = JwtGenerator.parseTokenGetUserName(token);
//        System.out.println(JwtGenerator.parseTokenGetBitMask(token));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        return userDetails!= null ? new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities()) : null;
    }
}
