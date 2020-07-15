package de.dhbw.ravensburg.vs.greeting.greeting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Value("${demo.version:0.0.1}")
    private String version;


    @Override
    public String getGreeting(String lang){
        String returnSmt;
        switch (lang){
            case "de":
                returnSmt = "Herzlich Willkommen zur Veranstalltung Cloud Computing";
                break;
            case "en":
                returnSmt = "Wellcome to the course on cloud computing";
                break;
            case "fr":
                returnSmt = "Bienvenue au cours sur le cloud computing";
                break;
            case "es":
                returnSmt = "Bienvenido al curso de cloud computing";
                break;
            default:
                returnSmt = "Wellcome to the cloud computing course";
                break;
        }
        return returnSmt;
    }

    @Override
    public String getVersion(){
        return this.version;
    }


}
