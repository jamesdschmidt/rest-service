package com.example.greetings;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/greetings")
public class GreetingsController {

  private GreetingsService service;

  @GetMapping
  public List<Greeting> index(@RequestParam(value = "sort", defaultValue = "rank,asc") String sort) {
    return service.getGreetings(sort);
  }

  @GetMapping("{rank}")
  public ResponseEntity<Greeting> get(@PathVariable int rank) {
    var greeting = service.getGreeting(rank);
    if (greeting == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(greeting);
  }
}
