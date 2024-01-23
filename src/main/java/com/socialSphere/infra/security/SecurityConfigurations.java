package com.socialSphere.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    // O método securityFilterChain é responsável por configurar o Spring Security
    // São mapeadas as rotas que precisam de autenticação e as que não precisam
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users/friendship").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/users/friendship").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/post").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/post/{id}").permitAll()
                        .anyRequest().authenticated());
        return httpSecurity.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    // O método authenticationManager é responsável por criar o AuthenticationManager
    // O AuthenticationManager é responsável por autenticar o usuário
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // O método passwordEncoder é responsável por criar o PasswordEncoder
    // O PasswordEncoder é responsável por criptografar a senha do usuário
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
