package de.dhbw.ravensburg.vs.greeting.greeting.controller;


import de.dhbw.ravensburg.vs.greeting.greeting.service.GreetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/api/greetings")
public class GreetingRestControllerImpl implements GreetingRestController {

    private GreetingService greetingService;


    public GreetingRestControllerImpl(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    @Override
    @GetMapping
    @Operation(summary = "Returns an adjusted greeting")
    public String getGreeting(@RequestParam(value = "lang",defaultValue = "es") @Parameter(description = "The prefered language for which the wellcome message will be adopted.") String lang){
        return this.greetingService.getGreeting(lang);
    }

    @Override
    @GetMapping("/version")
    @Operation(summary = "Returns the current version of the service")
    public String getVersion(){
        return this.greetingService.getVersion();
    }

}

