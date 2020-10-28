package de.dhbw.ravensburg.vs.greeting.greeting.controller;


import de.dhbw.ravensburg.vs.greeting.greeting.service.GreetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/v1/api/greetings")
public class GreetingRestControllerImpl implements GreetingRestController {

    private GreetingService greetingService;

    private Logger logger = LoggerFactory.getLogger(GreetingRestControllerImpl.class);

    @Value("${Spring.profiles.active:dev}")
    private String stage;

    @Value("${api_keys}")
    private String[] api_keys;


    public GreetingRestControllerImpl(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    @Override
    @GetMapping
    @Operation(summary = "Returns an adjusted greeting")
    public String getGreeting(@RequestParam(value = "lang",defaultValue = "de") @Parameter(description = "The prefered language for which the wellcome message will be adopted.") String lang,
                              @RequestHeader(value = "username", defaultValue = "") @Parameter(description = "The username of the particular user") String username,
                              @RequestHeader(value = "apikey", defaultValue = "") @Parameter(description = "The apikey of the particular user") String apikey){
        //Nutzung von Umgebungsvariable zur Vermeidung sensitiver Daten
        if (stage.trim().equals("dev")){
            this.logger.info("Calling user is "+ username);
        }

        //Auslesen der erlaubten apikeys und Überprüfung ob der übergebene API-Key ein Bestandteil dieser ist. Falls ja
        //wird der Aufruf weitergeleitet, falls nicht ein leerer String zurückgegeben
        if (Arrays.asList(api_keys).contains(apikey)) {
            return this.greetingService.getGreeting(lang);
        }else{
            return "";
        }

    }

    @Override
    @GetMapping("/version")
    @Operation(summary = "Returns the current version of the service")
    public String getVersion(){
        return this.greetingService.getVersion();
    }

}
