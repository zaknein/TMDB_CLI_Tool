package com.zaknein.TMDB_CLI_Tool;

import java.net.http.HttpClient;
import com.beust.jcommander.JCommander;

import com.zaknein.TMDB_CLI_Tool.comandos.TypeCommand;
import com.zaknein.TMDB_CLI_Tool.logica.Api;

import com.zaknein.TMDB_CLI_Tool.dominio.Movies;
import com.zaknein.TMDB_CLI_Tool.dominio.MovieResponse;


public class App {
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public static void main(String[] args) {

        // Instanciamos 
        TypeCommand typecommand = new TypeCommand();
        Api Api = new Api();


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
            MovieResponse movieResponse = Api.sendGet(type);

            for (int i = 0; i < Math.min(10, movieResponse.getResults().size()); i++) {
            Movies movie = movieResponse.getResults().get(i);

            System.out.println("---------------------------------------");
            System.out.println("Movie title: " + movie.getTitle());
            System.out.println("Release date: " + movie.getDate());
            System.out.println("Overview: " + movie.getOverview());
            }

        } catch (Exception e) {
            System.err.println("Error para procesar el tipo");
            jc.usage();
        }


    }
}