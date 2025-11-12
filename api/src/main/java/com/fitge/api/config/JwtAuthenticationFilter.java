package com.fitge.api.config;

import com.fitge.api.util.constraint.ControllerConstraint;
import com.fitge.api.util.externalService.CookieService;
import com.fitge.api.util.externalService.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CookieService cookieService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, CookieService cookieService) {
        this.jwtService = jwtService;
        this.cookieService = cookieService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String path = request.getRequestURI();

        if (ControllerConstraint.PUBLIC_CONTROLLERS.contains(path)) {

            filterChain.doFilter(request, response);

            return;
        }
        
        String token = cookieService.getAccessToken(request);

        jwtService.isAccessTokenValid(token);

        String role = jwtService.extractUserRole(token);

        var authorities = Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + role)
        );

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            jwtService.extractUserId(token),
            null,
            authorities
        );

        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }

}
