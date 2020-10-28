package de.dhbw.ravensburg.vs.greeting.greeting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Value("${demo.version:0.0.1}")
    private String version;

    @Value("${de_overview_file:gliederung_test.txt}")
    private String de_overview_file;

    @Value("${supported_languages}")
    private String[] supported_lang;

    @Value("${Spring.profiles.active:dev}")
    private String active_profile;

    @Override
    public String getGreeting(String lang){
        //Überprüfung ob es sich um die Entwicklungsumgebung handelt, falls ja keine Einschränkung
        if (active_profile != "dev") {
            //Überprüfung ob die übergebene Sprache eine der Unterstützten ist, falls nein Verwendung des Sprachcodes
            // 'xx' um in den Defaultblock zu kommen
            if (!Arrays.asList(supported_lang).contains(lang)) {
                lang = "xx";
            }
        }
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
