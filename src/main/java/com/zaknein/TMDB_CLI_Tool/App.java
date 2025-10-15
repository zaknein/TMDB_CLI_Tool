package com.zaknein.TMDB_CLI_Tool;

import java.net.http.HttpClient;
import com.beust.jcommander.JCommander;

import com.zaknein.TMDB_CLI_Tool.comandos.TypeCommand;
import com.zaknein.TMDB_CLI_Tool.logica.SendToApi;


public class App {
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public static void main(String[] args) {

        // Instanciamos 
        TypeCommand typecommand = new TypeCommand();
        SendToApi sendToApi = new SendToApi();


        JCommander jc = JCommander.newBuilder()
                .addObject(typecommand)
                .build();

        try {
            jc.parse(args);
            String type = "";
            switch (typecommand.getType()) {
                case "playing":
                    type = "now_playing";
                    break;
                case "popular":
                    type = "popular";
                    break;
                case "top":
                    type = "top_rated";
                    break;
                case "upcoming":
                    type = "upcoming";
                    break;
                default:
                    System.out.println("El tipo no es correcto " + typecommand.getType());
                    return;
            }
            System.out.println(type);
            sendToApi.sendGet(type);

        } catch (Exception e) {
            System.err.println("Error para procesar el tipo");
            jc.usage();
        }


    }
}