package com.george.facebook.security;

//import com.george.facebook.repository.UserRepository;
//import com.george.facebook.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////



import com.george.facebook.service.UserService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] resources = new String[]{
                "/", "/home","/pictureCheckCode","/include/**","/img/**",
                "/css/**","/icons/**","/images/**","/js/**","/layer/**"
        };

        http.authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/user/**").access("hasAnyRole('USER', 'ADMIN')")
//                .antMatchers("/", "/signup", "/login").permitAll()
//                .antMatchers(HttpMethod.GET, "/profile/{^[\\d]$}").permitAll()
//                .antMatchers("addpost","posts","pix", "profile", "profile/delete_user/{^[\\d]$}").authenticated()
//                .anyRequest().authenticated()
////                .anyRequest().denyAll()
                .anyRequest().permitAll()
                .and().formLogin()  //login configuration
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/posts", true)
                .and().logout()  //logout configuration
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and().exceptionHandling()//exception handling configuration
                .accessDeniedPage("/error");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}







////@Configuration
////@EnableWebSecurity
//////public class webSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
////public class webSecurityConfig extends WebSecurityConfigurerAdapter {
////
////    @Autowired
////    BCryptPasswordEncoder bCryptPasswordEncoder;
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("john")
////                .password("john")
////                .roles("USER")
////                .and().withUser("jane")
////                .password("jane")
////                .roles("USER");
//////                .passwordEncoder(bCryptPasswordEncoder);
////
////
////    }
////
////    @Override
////    public void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/", "/signup")
////                .permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin().loginPage("/login")
////                .defaultSuccessUrl("/posts")
////                .permitAll();
//////                 .anyRequest().authenticated();
//////                 .permitAll();
////    }
////
////
////
////
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder(){
////        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        return bCryptPasswordEncoder;
////    }
////
//////    @Bean
//////    public PasswordEncoder getPasswordEncoder(){
//////        return NoOpPasswordEncoder.getInstance();
//////    }
////
////
////
////
////
////
////
////}
//@Configuration
//@EnableWebSecurity
//public class webSecurityConfig  extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
//// //               this line for inmemory database
//// //               .withDefaultSchema()
////                .withUser("user1")
////                .password("root")
////                .roles("USER")
////                .and()
////                .withUser("admin")
////                .password("root")
////                .roles("ADMIN");
////    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/signup", "/logout")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/posts", true)
//                .failureUrl("/login?error=true")
//                .permitAll();
////                 .anyRequest().authenticated();
////                 .permitAll();
//    }
//
//
//
//
////    @Bean
////    public PasswordEncoder getPasswordEncoder(){
////        return NoOpPasswordEncoder.getInstance();
////    }
////
//
//
////    // not used yet
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////        return passwordEncoder;
////    }
//
//    //
//}