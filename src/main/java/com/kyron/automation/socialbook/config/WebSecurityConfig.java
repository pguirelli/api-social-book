package com.kyron.automation.socialbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(
            HttpSecurity http,
            CustomBasicAuthFilter customBasicAuthFilter) throws Exception {

        http
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/h2-console").permitAll();
                            authorizeConfig.anyRequest().authenticated();
                        })
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(customBasicAuthFilter, BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
