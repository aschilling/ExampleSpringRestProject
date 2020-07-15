package de.dhbw.ravensburg.vs.greeting.greeting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface GreetingRestController {
    @GetMapping
    String getGreeting(@RequestParam(defaultValue = "en") String lang);

    @GetMapping("/version")
    String getVersion();
}
