package com.linoer.app.test.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin().
//                loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll().and().
//                httpBasic().and().csrf().disable().authorizeRequests()
////                .antMatchers("/login", "logout").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
//                .anyRequest().authenticated();
//        http.formLogin().loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable();
        http.antMatcher("/security/**")
                .httpBasic().and()
                .csrf().disable()
                .headers().cacheControl().disable().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET).hasRole("USER")
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().
                passwordEncoder(new BCryptPasswordEncoder()).
                withUser("user").password(new BCryptPasswordEncoder().encode("user")).roles("USER").and().
                withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("USER", "ADMIN");
    }
}
