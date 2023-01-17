package com.example.demo.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
         /*       .anonymous(AbstractHttpConfigurer::disable)         // AnonymousAuthenticationFilter
                .csrf(AbstractHttpConfigurer::disable)              // CsrfFilter
                .sessionManagement(AbstractHttpConfigurer::disable) // DisableEncodeUrlFilter, SessionManagementFilter
                .exceptionHandling(AbstractHttpConfigurer::disable) // ExceptionTranslationFilter
                .headers(AbstractHttpConfigurer::disable)           // HeaderWriterFilter
                .logout(AbstractHttpConfigurer::disable)            // LogoutFilter
                .requestCache(AbstractHttpConfigurer::disable)      // RequestCacheAwareFilter
                .servletApi(AbstractHttpConfigurer::disable)        // SecurityContextHolderAwareRequestFilter
                .securityContext(AbstractHttpConfigurer::disable)   // SecurityContextPersistenceFilter*/
                .build();



        /*return http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
*/
    }
}