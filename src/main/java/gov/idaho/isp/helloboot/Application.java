package gov.idaho.isp.helloboot;

import gov.idaho.isp.helloboot.formatter.LocalDateFormatter;
import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.format.Formatter;
import org.springframework.jmx.support.RegistrationPolicy;

@EnableMBeanExport(registration=RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public Formatter<LocalDate> getLocalDateFormatter(@Value("${date.print.format}") String printFormat, @Value("${date.parse.format}") String parseFormat) {
    return new LocalDateFormatter(printFormat, Arrays.asList(parseFormat, printFormat));
  }
}
