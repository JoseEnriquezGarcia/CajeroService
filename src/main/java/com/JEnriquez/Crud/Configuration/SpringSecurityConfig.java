package com.JEnriquez.Crud.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/cajero/deposito/**")
                .hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers("/cajero/usuario/**")
                .hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers("/cajero/cantidadTotal")
                .hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers("/cajero/retirar/**")
                .hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers("/cajero/**")
                .hasAuthority("Administrador")
                )
                .httpBasic(withDefaults())
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
