package com.sophinia.backend.security;

import com.sophinia.backend.exception.CustomAccessDeniedHandler;
import com.sophinia.backend.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {


    private final JwtFilter jwtFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(
            final JwtFilter jwtFilter,
            final CustomAccessDeniedHandler customAccessDeniedHandler
    ) {
        this.jwtFilter = jwtFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationProvider authenticationProvider)
            throws Exception {

        CookieCsrfTokenRepository tokenRepository = new CookieCsrfTokenRepository();
        tokenRepository.setCookieName("XSRF-TOKEN");
        tokenRepository.setCookiePath("/");

        return http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf
                        .csrfTokenRepository(tokenRepository)
                )
                .authorizeHttpRequests( req ->
                    req.requestMatchers(
                            "/app/login",
                            "/api/v1/order/place-order"
                    )
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/clothing-model")
                    .permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/clothing-model")
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/clothing-type")
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/decoration")
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/design")
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/fabric")
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/product")
                            .permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/v1/client/update-client-after-order")
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
