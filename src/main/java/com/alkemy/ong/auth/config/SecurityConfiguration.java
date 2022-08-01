package com.alkemy.ong.auth.config;

import com.alkemy.ong.auth.filter.JwtRequestFilter;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsCustomService).passwordEncoder(encoder());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/*").permitAll()

                ////////////////////
                //  Admin Routes  //
                ///////////////////


                .antMatchers(HttpMethod.GET, "/users").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/activities").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/users").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/news/detail").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/categories").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/testimonials").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/contacts").hasRole(ROLE_ADMIN)

                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
