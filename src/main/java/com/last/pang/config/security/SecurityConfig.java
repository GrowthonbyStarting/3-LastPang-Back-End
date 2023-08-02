package com.last.pang.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final CorsConfig corsConfig;


    @Bean
    SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .apply(new MyCustomDsl())
                .and()
                .authorizeRequests(authorize -> {
                    try {
                        authorize
//                                .antMatchers("/api/**").authenticated()
                                .anyRequest().permitAll()
                                .and()
                                .formLogin().loginPage("/auth/signin").loginProcessingUrl("/auth/signin").defaultSuccessUrl("/");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            builder.addFilter(corsConfig.corsFilter());
        }
    }
}
