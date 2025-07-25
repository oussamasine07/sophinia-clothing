package com.sophinia.backend.security;

import com.sophinia.backend.exception.CustomAccessDeniedHandler;
import com.sophinia.backend.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(
            final AuthenticationProvider authenticationProvider,
            final JwtFilter jwtFilter,
            final CustomAccessDeniedHandler customAccessDeniedHandler
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtFilter = jwtFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//                .formLogin(form -> form.disable())
//                .httpBasic(basic -> basic.disable());
//        return http.build();
        return http
                //.cors(c -> c.disable())
                .csrf(c -> c.disable())
                .authorizeHttpRequests( req ->
                    req.requestMatchers(
                            "/app/admin/login"
                    )
                    .permitAll()
                    .requestMatchers(
                            "/app/employee/login"
                    )
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                )
                .exceptionHandling(
                        ex -> ex.accessDeniedHandler(customAccessDeniedHandler)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
