package com.m_motors.mmotors.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Autorise toutes les requêtes vers la console H2
                .requestMatchers(toH2Console()).permitAll()
                // Toutes les autres requêtes nécessitent une authentification
                .anyRequest().authenticated()
            )
            // Active le formulaire de connexion par défaut
            .formLogin(form -> form.permitAll())
            .logout(logout -> logout.permitAll());

        // Requis pour que la console H2 s'affiche correctement
        http.csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()));
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}