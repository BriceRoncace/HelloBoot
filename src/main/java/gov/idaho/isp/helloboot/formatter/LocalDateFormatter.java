package gov.idaho.isp.helloboot.formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.springframework.format.Formatter;

public class LocalDateFormatter implements Formatter<LocalDate> {
  private final String printFormat;
  private final List<String> parseFormats;

  public LocalDateFormatter(String format) {
    this(format, Arrays.asList(format));
  }

  public LocalDateFormatter(String printFormat, List<String> parseFormats) {
    this.printFormat = printFormat;
    this.parseFormats = parseFormats;
  }

  @Override
  public String print(LocalDate date, Locale locale) {
    return DateTimeFormatter.ofPattern(printFormat, locale).format(date);
  }

  @Override
  public LocalDate parse(String string, Locale locale) throws ParseException {
    LocalDate parsed = null;
    for (String format : parseFormats) {
      try {
        parsed = LocalDate.parse(string, DateTimeFormatter.ofPattern(format, locale));
      }
      catch (Exception ignore) {
      }
    }

    if (parsed == null) {
      throw new NullPointerException(String.format("Could not parse string \"%s\" using formats %s", string, parseFormats));
    }
    return parsed;
  }
}