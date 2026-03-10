package com.accenture.locationvoitures.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true // @PreAuthorize, @PostAuthorize, @PostFilter, @PreFilter
)


public class SecurityConfig {


    private static final String ADMIN = "ADMIN";
    private static final String CUSTOMER = "CUSTOMER";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/customers").permitAll()
                        .requestMatchers("/customers/**").hasAnyRole(ADMIN, CUSTOMER)
                        .requestMatchers("/vehicles/**").hasRole(ADMIN)
                        .requestMatchers("/vehicles").hasAnyRole(ADMIN, CUSTOMER)
                        .requestMatchers("/admins/**").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST,"/admins").hasAnyRole(ADMIN,CUSTOMER)
                        .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        String getEmailPasswordQuery = """
                SELECT email, password, 1 FROM person WHERE email=?
                """;
        String getEmailRoleQuery = """
                SELECT email, role, 1 FROM person WHERE email=?
                """;
        jdbcUserDetailsManager.setUsersByUsernameQuery(getEmailPasswordQuery);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(getEmailRoleQuery);
        return jdbcUserDetailsManager;
    }
}
