package gov.idaho.isp.helloboot;

import gov.idaho.isp.helloboot.security.CsrfExceptionAccessDeniedHandler;
import gov.idaho.isp.helloboot.security.CustomLdapUserDetailsMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@Profile({"test", "production"})
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final String activeDirectoryDomain;
  private final String activeDirectoryLdapUrls;

  public WebSecurityConfig(@Value("${active.directory.domain}") String activeDirectoryDomain, @Value("${spring.ldap.urls}") String activeDirectoryLdapUrls) {
    this.activeDirectoryDomain = activeDirectoryDomain;
    this.activeDirectoryLdapUrls = activeDirectoryLdapUrls;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/webjars/**").permitAll()
      .antMatchers("/assets/**").permitAll()
      .anyRequest().authenticated()
      .and().formLogin().loginPage("/login").permitAll()
      .and().logout().logoutUrl("/logout").permitAll()
      .and().exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(activeDirectoryDomain, activeDirectoryLdapUrls);
    provider.setUserDetailsContextMapper(new CustomLdapUserDetailsMapper());
    auth.authenticationProvider(provider);
  }

  private AccessDeniedHandler getAccessDeniedHandler() {
    return new CsrfExceptionAccessDeniedHandler();
  }
}