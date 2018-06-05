package gov.idaho.isp.helloboot;

import gov.idaho.isp.helloboot.security.CsrfExceptionAccessDeniedHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.access.AccessDeniedHandler;

@Profile("dev")
@EnableWebSecurity
public class WebSecurityDevConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/webjars/**").permitAll()
      .antMatchers("/assets/**").permitAll()
      .anyRequest().authenticated()
      .and().formLogin().loginPage("/login").permitAll()
      .and().logout().logoutUrl("/logout").permitAll()
      .and().exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());

    http.headers().frameOptions().sameOrigin();
    http.csrf().ignoringAntMatchers("/h2-console/**");
    http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // could also set ActiveDirectoryLdapAuthenticationProvider as an auth
    // provider to allow both LDAP and in memorty authentication

    auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username("user").password("u").roles("USER"));
  }

  private AccessDeniedHandler getAccessDeniedHandler() {
    return new CsrfExceptionAccessDeniedHandler();
  }
}
