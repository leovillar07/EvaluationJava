package com.example.evaluationjava.config;

import com.example.evaluationjava.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/empresa/save").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/empresa/list/**").hasAnyRole(Role.ADMIN.name(), Role.BOSS.name())
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails userA = User.builder()
                .username("leovr")
                .password(passwordEncoder.encode("81195"))
                .roles(Role.ADMIN.name())
                .build();

        UserDetails userB = User.builder()
                .username("andrevr")
                .password(passwordEncoder.encode("251292"))
                .roles(Role.BOSS.name())
                .build();

        return new InMemoryUserDetailsManager(userA, userB);
    }
}
