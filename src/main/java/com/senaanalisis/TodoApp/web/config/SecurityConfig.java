package com.senaanalisis.TodoApp.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define your SecurityFilterChain bean providing HttpSecurity
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(customizeRequests -> {
                            customizeRequests
                                    .requestMatchers(HttpMethod.GET, "api/**").permitAll()
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .formLogin(form -> form
                        .loginPage("/login")                     // Define la página de inicio de sesión personalizada
                        .permitAll()                             // Permitir a todos acceso a la página de inicio de sesión
                        .defaultSuccessUrl("/home", true)        // Redireccionar a /home después del inicio de sesión
                        .failureUrl("/login?error=true")         // Redireccionar a /login?error=true en caso de fallo
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")              // Redireccionar a /login después del cierre de sesión
                );

        // Se puede agregar más configuración aquí si es necesario

        return http.build();
    }

    // Bean para encriptar y desencriptar las contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
