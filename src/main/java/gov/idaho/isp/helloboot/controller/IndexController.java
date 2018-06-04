package gov.idaho.isp.helloboot.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

  @GetMapping("/")
  public String index(HttpServletRequest req, Model m) {
    logger.debug("Inside index \"/\" get mapping handler method.");

    addJavaDetails(m);
    addServerDetails(req, m);
    addSpringDetails(m);
    m.addAttribute("msg", "Hello!  Spring Boot is up and running.");
    return "index";
  }

  private void addJavaDetails(Model m) {
    m.addAttribute("javaVersion", SystemUtils.JAVA_VERSION);
    m.addAttribute("javaHome", SystemUtils.JAVA_HOME);
    m.addAttribute("javaOs", SystemUtils.OS_NAME + " " + SystemUtils.OS_ARCH + " v." + SystemUtils.OS_VERSION);
    m.addAttribute("javaVendor", SystemUtils.JAVA_VENDOR);
    m.addAttribute("javaVmName", SystemUtils.JAVA_VM_NAME);
    m.addAttribute("javaVendorUrl", SystemUtils.JAVA_VENDOR_URL);
  }

  private void addSpringDetails(Model m) {
    m.addAttribute("springBootVersion", SpringBootVersion.getVersion());
    m.addAttribute("springVersion", SpringVersion.getVersion());
    m.addAttribute("hibernateVersion", org.hibernate.Version.getVersionString());
  }

  private void addServerDetails(HttpServletRequest req, Model m) {
    m.addAttribute("servletServerInfo", req.getServletContext().getServerInfo());
    m.addAttribute("servletVersion", req.getServletContext().getMajorVersion() + "." + req.getServletContext().getMinorVersion());
    m.addAttribute("servletEffectiveVersion", req.getServletContext().getEffectiveMajorVersion() + "." + req.getServletContext().getEffectiveMinorVersion() );
  }
}
