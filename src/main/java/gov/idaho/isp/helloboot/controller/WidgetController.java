package gov.idaho.isp.helloboot.controller;

import gov.idaho.isp.helloboot.domain.Widget;
import gov.idaho.isp.helloboot.domain.WidgetRepository;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WidgetController {
  private final WidgetRepository widgetRepository;

  public WidgetController(WidgetRepository widgetRepository) {
    this.widgetRepository = widgetRepository;
  }

  @ModelAttribute
  public Widget prepareWidget(Optional<Long> id) {
    return id.map(widgetRepository::getOne).orElse(new Widget());
  }

  @GetMapping("/widgets")
  public String viewWidgets(Model m) {
    m.addAttribute("widgets", widgetRepository.findAll());
    return "widgets";
  }

  @PostMapping("/saveWidget")
  public String saveWidgets(Widget widget, RedirectAttributes ra) throws UnsupportedEncodingException {
    widgetRepository.save(widget);
    ra.addFlashAttribute("messages", "Widget saved.");
    return "redirect:/widgets";
  }

  @PostMapping("/deleteWidget")
  public String deleteWidget(Widget widget, RedirectAttributes ra) {
    widgetRepository.delete(widget);
    ra.addFlashAttribute("messages", "Widget deleted.");
    return "redirect:/widgets";
  }
}
