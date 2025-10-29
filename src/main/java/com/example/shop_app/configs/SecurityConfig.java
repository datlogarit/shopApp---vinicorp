package com.example.shop_app.configs;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.shop_app.filters.AlreadyLoggedInFilter;
import com.example.shop_app.mapper.IUserMapper;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
// security config with spring security
public class SecurityConfig {
        private final IUserMapper iUserMapper;

        // filter chanin config
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpFilter) throws Exception {
                httpFilter
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(
                                                auth -> auth
                                                // allow public pages
                                                .requestMatchers("/", "/login", "/register",
                                                                "/api/v1/user/signUp", "/api/v1/home",
                                                                "/api/v1/detail/**", "/api/v1/search",
                                                                "/api/v1/invoice/**",
                                                                "/api/v1/product/**"
                                                                ).permitAll()
                                                // allow static resources
                                                .requestMatchers("/css/**", "/js/**", "/images/**",
                                                                "/webjars/**", "/static/**").permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(
                                                form -> form
                                                                .defaultSuccessUrl("/api/v1/home", true)
                                                                .loginPage("/login") // custom page
                                                                .loginProcessingUrl("/login")) //login url
                                .logout(
                                                form -> form.logoutUrl("/logout")
                                                                .logoutSuccessUrl("/login?logout")
                                                                .permitAll());
                // add filter
                httpFilter.addFilterBefore(new AlreadyLoggedInFilter(), UsernamePasswordAuthenticationFilter.class);
                return httpFilter.build();
        }

        // Cors config
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://192.168.52.196:8080"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("*"));
                configuration.setAllowCredentials(true);
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        // Config encoder password
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // Config UserDetailService - handle load user from db
        @Bean
        public UserDetailsService userDetailsService() {
                return phoneNumber -> iUserMapper.getUserByPhoneNumber(phoneNumber);
        }
}
