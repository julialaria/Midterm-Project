package com.ironhack.bankingSystem.security;

import com.ironhack.bankingSystem.service.impl.utils.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .mvcMatchers("/accounts/by-username").hasAnyAuthority("ROLE_ACCOUNTHOLDER", "ROLE_ADMIN")
                .mvcMatchers("/transaction").hasAuthority("ROLE_ACCOUNTHOLDER")
                .mvcMatchers("/create/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/savings/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/checking/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/creditCard/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/studentChecking/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/updateBalance/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/updateStatus/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/accountHolder/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/thirdParty/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/transactions/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/account/change-status/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();
        http.csrf().disable();
    }
}


