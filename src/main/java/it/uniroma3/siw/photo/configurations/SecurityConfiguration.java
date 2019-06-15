package it.uniroma3.siw.photo.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/admin*")
          .authorizeRequests()
          .anyRequest()
          .hasRole("ADMIN")
           
          .and()
          .formLogin()
          .loginPage("/loginAdmin")
          .loginProcessingUrl("/admin_login")
          .failureUrl("/loginAdmin?error=loginError")
          .defaultSuccessUrl("/adminPage")
           
          .and()
          .logout()
          .logoutUrl("/admin_logout")
          .logoutSuccessUrl("/protectedLinks")
          .deleteCookies("JSESSIONID")
           
          .and()
          .exceptionHandling()
          .accessDeniedPage("/403")
           
          .and()
          .csrf().disable();
    }

}