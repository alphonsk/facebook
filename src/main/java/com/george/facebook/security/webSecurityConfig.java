package com.george.facebook.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebSecurity
////public class webSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
//public class webSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("john")
//                .password("john")
//                .roles("USER")
//                .and().withUser("jane")
//                .password("jane")
//                .roles("USER");
////                .passwordEncoder(bCryptPasswordEncoder);
//
//
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/signup")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login")
//                .defaultSuccessUrl("/posts")
//                .permitAll();
////                 .anyRequest().authenticated();
////                 .permitAll();
//    }
//
//
//
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }
//
////    @Bean
////    public PasswordEncoder getPasswordEncoder(){
////        return NoOpPasswordEncoder.getInstance();
////    }
//
//
//
//
//
//
//
//}
@Configuration
@EnableWebSecurity
public class webSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
 //               this line for inmemory database
 //               .withDefaultSchema()
                .withUser("user1")
                .password("root")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("root")
                .roles("ADMIN");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/signup", "/logout")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/posts", true)
                .failureUrl("/login?error=true")
                .permitAll();
//                 .anyRequest().authenticated();
//                 .permitAll();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    //
}