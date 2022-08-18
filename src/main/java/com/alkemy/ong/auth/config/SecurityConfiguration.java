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
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
    public void configure(WebSecurity web) throws Exception {
        //SWAGGER
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html**",
                "/swagger-ui/**",
                "/webjars/**",
                "favicon.ico");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/*").permitAll()

                ////////////////////
                //  Admin Routes  //
                ///////////////////

                .antMatchers(HttpMethod.GET, "/users").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/slides/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/activities").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/users").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/categories/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, "/categories/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/news/detail").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/categories").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/slides").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/categories").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/testimonials").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/contacts").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/news/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/slides/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/activities/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/members/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, "/testimonials/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, "/slides/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/comments").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/comments/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/comments/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/comments").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, "/comments/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/organization/public").hasRole(ROLE_ADMIN)




                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
