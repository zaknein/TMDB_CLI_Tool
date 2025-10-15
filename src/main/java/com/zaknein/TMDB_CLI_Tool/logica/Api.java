package com.zaknein.TMDB_CLI_Tool.logica;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import com.zaknein.TMDB_CLI_Tool.dominio.Movies;
import com.zaknein.TMDB_CLI_Tool.dominio.MovieResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Api {
    
    public static void sendGet(String type) throws Exception {
            
        //Instanciar objectmapper
        final ObjectMapper objectMapper = new ObjectMapper();  
        
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNzFlM2JhZjQ4MDA2MWFkOTEyNzNkMmFkNjg2NTQxYiIsIm5iZiI6MTc1OTE4MTA4NC41MDcsInN1YiI6IjY4ZGFmOTFjODExN2FlNWUwN2JiMTE4MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AQRdGET_EJ-29hM9za3ti1GJut3sml4NqBUQqLnEXz8";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/movie/" + type + "?language=en-US&page=1"))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());


            if(response.statusCode() == 200){
                String responseBody = response.body();


                MovieResponse movieResponse  = objectMapper.readValue(responseBody, MovieResponse.class);

                for (int i = 0; i < Math.min(10, movieResponse.getResults().size()); i++) {
                Movies movie = movieResponse.getResults().get(i);

                System.out.println("---------------------------------------");
                System.out.println("Movie title: " + movie.getTitle());
                System.out.println("Release date: " + movie.getDate());
                System.out.println("Overview: " + movie.getOverview());
                }

            }else{
                System.out.println("Request failed. Status Code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
