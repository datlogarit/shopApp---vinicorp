package com.example.shop_app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.shop_app.mapper.IUserMapper;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final IUserMapper iUserMapper;

    // Maybe throw exception when config
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpFilter) throws Exception {
        httpFilter
                .cors().and()
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(

                        auth -> auth
                                // allow public pages
                                .requestMatchers("/", "/login","/register", "/api/v1/user/signUp", "/api/v1/home",
                                        "/api/v1/detail/**", "/api/v1/search", "/api/v1/invoice/**",
                                        "/api/v1/invoice/export/**",
                                        "/api/v1/cart/**")
                                .permitAll()
                                // allow static resources
                                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/static/**")
                                .permitAll()
                                .anyRequest().authenticated())
                .formLogin(
                        form -> form// .loginPage("/login").permitAll()
                                .defaultSuccessUrl("/api/v1/home", true)
                                .loginPage("/login") // trang custom
                                .loginProcessingUrl("/login")) // url để submit form)
                .logout(
                        form -> form.logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll());
        return httpFilter.build();
    }

    // Config encoder password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Config UserDetailService - handle load user task from db
    @Bean
    public UserDetailsService userDetailsService() {
        return phoneNumber -> iUserMapper.getUserByPhoneNumber(phoneNumber);
    }
}
