package com.fsocial.configurations;

import com.fsocial.filrers.JwtTokenFilter;
import com.fsocial.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers(
                            String.format("%s/users/register",apiPrefix),
                            String.format("%s/users/login",apiPrefix),
                            String.format("%s/users/validator-email/**",apiPrefix),
                            ("/v3/api-docs/**"),
                            ("/swagger-ui/**"),
                            ("/swagger-ui.html")
                    ).permitAll()
                            .requestMatchers(HttpMethod.POST,String.format("%s/users/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.PUT,String.format("%s/users/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,String.format("%s/users/**",apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(HttpMethod.GET,String.format("%s/majors/**",apiPrefix)).permitAll()
                            .requestMatchers(HttpMethod.POST,String.format("%s/majors/**",apiPrefix)).hasRole(Role.ADMIN)
                            .requestMatchers(HttpMethod.PUT,String.format("%s/majors/**",apiPrefix)).hasRole(Role.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,String.format("%s/majors/**",apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(HttpMethod.GET,String.format("%s/posts/**",apiPrefix)).permitAll()
                            .requestMatchers(HttpMethod.POST,String.format("%s/posts/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.PUT,String.format("%s/posts/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,String.format("%s/posts/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)

                            .requestMatchers(HttpMethod.GET,String.format("%s/chat_rooms/**",apiPrefix)).permitAll()
                            .requestMatchers(HttpMethod.POST,String.format("%s/chat_rooms/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.PUT,String.format("%s/chat_rooms/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,String.format("%s/chat_rooms/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)

                            .requestMatchers(HttpMethod.GET,String.format("%s/follows/**",apiPrefix)).permitAll()
                            .requestMatchers(HttpMethod.POST,String.format("%s/follows/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.PUT,String.format("%s/follows/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,String.format("%s/follows/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)

                            .requestMatchers(HttpMethod.GET,String.format("%s/likes/**",apiPrefix)).permitAll()
                            .requestMatchers(HttpMethod.POST,String.format("%s/likes/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.PUT,String.format("%s/likes/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,String.format("%s/likes/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)

                            .requestMatchers(HttpMethod.GET,String.format("%s/notifications/**",apiPrefix)).permitAll()
                            .requestMatchers(HttpMethod.POST,String.format("%s/notifications/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.PUT,String.format("%s/notifications/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,String.format("%s/notifications/**",apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN)

                            .requestMatchers(HttpMethod.GET,String.format("%s/roles/**",apiPrefix)).hasRole(Role.ADMIN)
                            .requestMatchers(HttpMethod.POST,String.format("%s/roles/**",apiPrefix)).hasRole(Role.ADMIN)
                            .requestMatchers(HttpMethod.PUT,String.format("%s/roles/**",apiPrefix)).hasRole(Role.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,String.format("%s/roles/**",apiPrefix)).hasRole(Role.ADMIN)
                            .anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(List.of("*"));
                        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
                        configuration.setExposedHeaders(List.of("x-auth-token"));
                        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                        source.registerCorsConfiguration("/**", configuration);
                        httpSecurityCorsConfigurer.configurationSource(source);
                    }
                });
        return http.build();


    }
}
