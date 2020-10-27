package de.dhbw.ravensburg.vs.greeting.greeting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Value("${demo.version:0.0.1}")
    private String version;

    @Value("${demo.de.overview.file:gliederung_test.txt}")
    private String de_overview_file;

    @Override
    public String getGreeting(String lang){
        String returnSmt;
        switch (lang){
            case "de":
                returnSmt = "Herzlich Willkommen zur Veranstalltung Cloud " +
                             "Computing"+"\n"+getOverview();
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

    //Nutzung von Umgebungsvariablen zur Auswahl unterschiedlicher Endpunkte
    public String getOverview() {
        BufferedReader test = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/"+de_overview_file)));
        return test.lines().collect(Collectors.joining("\n"));
    }

    @Override
    public String getVersion(){
        return this.version;
    }


}
