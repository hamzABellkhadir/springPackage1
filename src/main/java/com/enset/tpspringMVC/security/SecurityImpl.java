package com.enset.tpspringMVC.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityImpl extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // we have 3 method to do the authentication => inmemoryauthentication , jdbc authentocation , and using the UserDetailService
        /* in memory authentication => only for testing
        BCryptPasswordEncoder cryptPasswordEncoder = bCryptPasswordEncoder();
        auth.inMemoryAuthentication().withUser("user1").password(cryptPasswordEncoder.encode("1234")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(cryptPasswordEncoder.encode("1234")).roles("USER","ADMIN");*/

//        jdbc authentication => connection to the db and make shure that the users exist
       /* auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal , password as credentials, active from users where username =?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(new BCryptPasswordEncoder());*/
        // using UserDetailsService

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.authorizeRequests().antMatchers("/login/**","/createAcount/**","/saveTheNewAcount/**").permitAll();
        http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/form**/**").hasRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/notAutorized");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
