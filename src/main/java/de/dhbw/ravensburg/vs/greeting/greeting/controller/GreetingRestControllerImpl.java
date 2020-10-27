package de.dhbw.ravensburg.vs.greeting.greeting.controller;


import de.dhbw.ravensburg.vs.greeting.greeting.service.GreetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/v1/api/greetings")
public class GreetingRestControllerImpl implements GreetingRestController {

    private GreetingService greetingService;

    private Logger logger = LoggerFactory.getLogger(GreetingRestControllerImpl.class);

    @Value("${Spring.profiles.active:dev}")
    private String stage;

    public GreetingRestControllerImpl(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    @Override
    @GetMapping
    @Operation(summary = "Returns an adjusted greeting")
    public String getGreeting(@RequestParam(value = "lang",defaultValue = "de") @Parameter(description = "The prefered language for which the wellcome message will be adopted.") String lang,
                              @RequestHeader(value = "username", defaultValue = "") @Parameter(description = "The username of the particular user") String username){
        //Nutzung von Umgebungsvariable zur Vermeidung sensitiver Daten
        if (stage.trim().equals("dev")){
            this.logger.info("Calling user is "+ username);
        }
        return this.greetingService.getGreeting(lang);
    }

    @Override
    @GetMapping("/version")
    @Operation(summary = "Returns the current version of the service")
    public String getVersion(){
        return this.greetingService.getVersion();
    }

}
