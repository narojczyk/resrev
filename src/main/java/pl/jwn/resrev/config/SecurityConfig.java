package pl.jwn.resrev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.jwn.resrev.domain.service.CustomUserDetailService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailService userDetailService;

    public SecurityConfig(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth
        .userDetailsService(userDetailService)
        .passwordEncoder(passwordEncoder());
    }
/*
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/registration").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/").permitAll()
            .anyRequest().authenticated()
                .and()
            .formLogin()
                .and()
            .csrf().disable();    // włączyć później
    }
}
