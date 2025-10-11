package com.zaknein.TMDB_CLI_Tool;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.beust.jcommander.JCommander;

import com.zaknein.TMDB_CLI_Tool.comandos.TypeCommand;
import com.zaknein.TMDB_CLI_Tool.dominio.Movies;
import com.zaknein.TMDB_CLI_Tool.dominio.MovieResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    //Instanciar objectmapper
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) {

        // Instanciamos 
        TypeCommand typecommand = new TypeCommand();
        


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
            sendGet(type);

        } catch (Exception e) {
            System.err.println("Error para procesar el tipo");
            jc.usage();
        }


    }

    private static void sendGet(String type) throws Exception {
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

            //Instanciamos clase
            // Movies movie = new Movies();

            if(response.statusCode() == 200){
                String responseBody = response.body();

                JSONObject json = new JSONObject(responseBody);
                JSONArray events = json.getJSONArray("results");

                MovieResponse movieResponse  = objectMapper.readValue(responseBody, MovieResponse.class);

                for (int i = 0; i < Math.min(10, movieResponse.getResults().size()); i++) {
                Movies movie = movieResponse.getResults().get(i);

                System.out.println("---------------------------------------");
                System.out.println("Movie title: " + movie.getTitle());
                System.out.println("Release date: " + movie.getDate());
                System.out.println("Overview: " + movie.getOverview());
                }

                // if(events.length() == 0){
                //     System.out.println("No movies where found");
                // }else{
                //     System.out.println("Movies");

                //     for (int i = 0; i < 10; i++) {

                //         JSONObject event = events.getJSONObject(i);

                //         String date = event.getString("release_date");
                //         String title = event.getString("title");
                //         String overview = event.getString("overview");

                //         movie.setDate(date);
                //         movie.setTitle(title);
                //         movie.setOverview(overview);

                //         System.out.println("    ---------------------------------------");
                //         System.out.println("Movie title " + movie.getTitle());
                //         System.out.println("Release date " + movie.getDate());
                //         System.out.println("Overview:");
                //         System.out.println(movie.getOverview());
                //     }
                // }
            }else{
                System.out.println("Request failed. Status Code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}