package com.gloibgroup.urbanswap.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AntPathRequestMatcher[] permittedPathRequestMatchers = getPermittedPathRequestMatchers();
        http.authorizeHttpRequests(requestMatcherRegistry -> requestMatcherRegistry
                        .requestMatchers(permittedPathRequestMatchers).permitAll()
                        .anyRequest().authenticated());

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private AntPathRequestMatcher[] getPermittedPathRequestMatchers() {
        return new AntPathRequestMatcher[] {
                new AntPathRequestMatcher("/auth/**"),
        };
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }
}
