package com.example.greetings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

@Service
public class GreetingsService {

  private final List<Greeting> list = List.of(
      new Greeting(1, Locale.US, "Hello"),
      new Greeting(2, Locale.SIMPLIFIED_CHINESE, "你好"),
      new Greeting(3, new Locale("hi", "IN"), "हैलो"),
      new Greeting(4, new Locale("es", "es"), "Hola"),
      new Greeting(5, Locale.FRANCE, "Bonjour"),
      new Greeting(6, new Locale("ar"), "مرحبا"),
      new Greeting(7, new Locale("bn", "bn"), "হ্যালো"),
      new Greeting(8, new Locale("ru", "ru"), "Привет"),
      new Greeting(9, new Locale("pt"), "Olá"),
      new Greeting(10, new Locale("id", "id"), "Halo"));

  public List<Greeting> getGreetings(String sort) {
    var sortArray = sort.split(",");
    var name = sortArray[0];
    boolean isAscending = sortArray.length == 1 || "asc".equalsIgnoreCase(sortArray[1]);

    if ("rank".equals(name)) {
      if (isAscending) {
        return list.stream().sorted(Comparator.comparing(Greeting::getRank)).collect(Collectors.toList());
      } else {
        return list.stream().sorted(Comparator.comparing(Greeting::getRank).reversed()).collect(Collectors.toList());
      }
    } else if ("locale".equals(name)) {
      if (isAscending) {
        return list.stream().sorted(Comparator.comparing(Greeting::toLanguageTag)).collect(Collectors.toList());
      } else {
        return list.stream().sorted(Comparator.comparing(Greeting::toLanguageTag).reversed()).collect(Collectors.toList());
      }
    } else if ("content".equals(name)) {
      if (isAscending) {
        return list.stream().sorted(Comparator.comparing(Greeting::getContent)).collect(Collectors.toList());
      } else {
        return list.stream().sorted(Comparator.comparing(Greeting::getContent).reversed()).collect(Collectors.toList());
      }
    }

    return List.copyOf(list);
  }

  public Greeting getGreeting(int rank) {
    for (Greeting g : list) {
      if (g.getRank() == rank) {
        return g;
      }
    }
    return null;
  }
}
