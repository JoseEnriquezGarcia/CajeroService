package com.JEnriquez.Crud.Configuration;

//import javax.sql.DataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;

public class SpringSecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/logout")
//                .hasAnyAuthority("Administrador", "Cliente")
//                .requestMatchers("/error")
//                .hasAnyAuthority("Administrador", "Cliente")
//                .requestMatchers("/home")
//                .hasAnyAuthority("Administrador", "Cliente")
//                .requestMatchers("/home/**")
//                .hasAuthority("Administrador")
//        )
//                .formLogin(login -> {
//                    login
//                            .loginPage("/login")
//                            .loginProcessingUrl("/authenticateTheUser")
//                            .permitAll()
//                            .defaultSuccessUrl("/home", true);
//                })
//                .logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll(true))
//                .exceptionHandling(exceptionHandling
//                        -> exceptionHandling
//                        .accessDeniedPage("/error"));
//        
//        return httpSecurity.build();
//    }
//
//    @Bean
//    public UserDetailsService jdbcUserDetails(DataSource dataSource) {
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
//        manager.setDataSource(dataSource);
//        manager.setUsersByUsernameQuery("select Username, Password, Status from Usuario where Username = ?");
//        manager.setAuthoritiesByUsernameQuery("select Username, NombreRol from RolManager where Username = ?");
//
//        return manager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
