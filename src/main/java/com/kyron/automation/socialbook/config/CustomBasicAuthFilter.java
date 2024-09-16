package com.kyron.automation.socialbook.config;

import java.io.IOException;
import java.util.Base64;

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

    private static final int BASIC_LENGTH = 6;
    private static final String EXEMPLO_USERNAME = "usuario";
    private static final String EXEMPLO_PASSWORD = "123456";

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Basic dXN1YXJpbzoxMjM0NTY=
        var headerAuthorization = request.getHeader("Authorization");

        if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // dXN1YXJpbzoxMjM0NTY=
        var basicToken = headerAuthorization.substring(BASIC_LENGTH);
        byte[] basicTokenDecoded = Base64.getDecoder().decode(basicToken);

        // usuario:123456
        String basicTokenValue = new String(basicTokenDecoded);

        // ["usuario", "123456"]
        String[] basicAuthsSplit = basicTokenValue.split(":");

        if (basicAuthsSplit[0].equals(EXEMPLO_USERNAME)
                && basicAuthsSplit[1].equals(EXEMPLO_PASSWORD)) {

            var authToken = new UsernamePasswordAuthenticationToken(basicAuthsSplit[0], null, null);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}