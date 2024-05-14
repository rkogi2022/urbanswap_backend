package com.gloibgroup.urbanswap.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        contentSecurityConfig(http);
//        AntPathRequestMatcher[] requestMatchers = getAntPathRequestMatchers();
//        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
//                .requestMatchers(requestMatchers).permitAll()
//                    .anyRequest().authenticated())
//                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
//                .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()))).csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        contentSecurityConfig(http);
        AntPathRequestMatcher[] requestMatchers = getAntPathRequestMatchers();
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(requestMatchers).permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
                        .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()))).csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    // Define your AntPathRequestMatchers here
    private AntPathRequestMatcher[] getAntPathRequestMatchers() {
        // Example: permit access to "/public/**" and "/resources/**" paths
        return new AntPathRequestMatcher[] {
                new AntPathRequestMatcher("/api/**"),
//                new AntPathRequestMatcher("/resources/**")
        };
    }

    // Define your custom JWT Authentication Converter here
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }
//
//    public FirebaseAuthenticationFilter firebaseAuthenticationFilter() {
//
//    }
}
