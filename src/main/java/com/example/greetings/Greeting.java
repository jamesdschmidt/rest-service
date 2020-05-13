package com.example.greetings;

import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Greeting {

  private int rank;
  private Locale locale;
  private String content;

  public String toLanguageTag() {
    return locale.toLanguageTag();
  }
}
