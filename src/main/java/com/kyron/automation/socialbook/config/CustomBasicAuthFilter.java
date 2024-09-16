package com.kyron.automation.socialbook.config;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomBasicAuthFilter extends OncePerRequestFilter {

    @Value("${spring.security.user.name}")
    private String user;

    @Value("${spring.security.user.password}")
    private String password;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var headerAuthorization = request.getHeader("Authorization");

        if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
            filterChain.doFilter(request, response);
            return;
        }

        byte[] basicTokenDecoded = Base64.getDecoder().decode(headerAuthorization.substring(password.length()));

        String[] basicAuthsSplit = new String(basicTokenDecoded).split(":");

        if (basicAuthsSplit[0].equals(user)
                && basicAuthsSplit[1].equals(password)) {

            var authToken = new UsernamePasswordAuthenticationToken(basicAuthsSplit[0], basicAuthsSplit[1], null);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }

}