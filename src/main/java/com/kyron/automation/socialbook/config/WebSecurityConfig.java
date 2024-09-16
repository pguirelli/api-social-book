package com.kyron.automation.socialbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    protected SecurityFilterChain filterChain(
            HttpSecurity http, CustomBasicAuthFilter customBasicAuthFilter) throws Exception {

        http.authorizeHttpRequests(requests -> {
            requests
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();
            requests.anyRequest().authenticated();
        })
                .addFilterBefore(customBasicAuthFilter, BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().sameOrigin())
                .formLogin(login -> login.permitAll());

        return http.build();
    }

}
