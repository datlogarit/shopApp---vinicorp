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
    public SecurityFilterChain filterChain(HttpSecurity httpFilter) throws Exception{
            httpFilter
            .csrf(csrf->csrf.disable())
            .authorizeHttpRequests(
                auth -> auth.requestMatchers("/api/v1/user/signUp").permitAll()
                .anyRequest().authenticated())
                .formLogin(
                    form -> form//.loginPage("/login")
                    .defaultSuccessUrl("/api/v1/home", true) 
                )
                .logout(
                    form -> form.logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                );
                return httpFilter.build();
    }
    
    // Config encoder password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Config UserDetailService - handle load user task from db
    @Bean
    public UserDetailsService userDetailsService(){
        return phoneNumber -> iUserMapper.getUserByPhoneNumber(phoneNumber); 
    }
}
