package gov.idaho.isp.helloboot;

import gov.idaho.isp.helloboot.interceptor.ProfileInterceptor;
import gov.idaho.isp.helloboot.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  private final Environment environment;

  public WebConfig(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new UserInterceptor());
    registry.addInterceptor(new ProfileInterceptor(environment));
  }
}