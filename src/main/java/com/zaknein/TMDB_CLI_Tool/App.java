package com.zaknein.TMDB_CLI_Tool;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.beust.jcommander.JCommander;
import com.zaknein.srcTMDB_CLI_Tool.comandos.TypeCommand;

public class App {
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    
    public static void main(String[] args) {
        
        // Instanciamos comando
        TypeCommand typecommand = new TypeCommand();


        JCommander jc = JCommander.newBuilder()
            .addCommand("type", typecommand)
            .build()
            .parse(args);
        

        switch(typecommand.getType()){
            case "playing":
                break;
            
            case "popular":
                break;

            case "top":
                break;

            case "upcoming":
                break;

        }
    }
}