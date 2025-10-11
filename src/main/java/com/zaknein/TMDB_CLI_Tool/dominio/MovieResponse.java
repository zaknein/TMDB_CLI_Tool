package com.zaknein.TMDB_CLI_Tool.dominio;

import java.util.List;

public class MovieResponse {
    private int page;
    private List<Movies> results;
    private int total_pages;
    private int total_results;
    private Dates dates;  // 👈 Nuevo campo

    // Getters y Setters...

    public Dates getDates() {
         return dates;
    }
    public void setDates(Dates dates) { 
        this.dates = dates;
    }

    public static class Dates {
        private String minimum;
        private String maximum;

        public String getMinimum() { return minimum; }
        public void setMinimum(String minimum) { this.minimum = minimum; }

        public String getMaximum() { return maximum; }
        public void setMaximum(String maximum) { this.maximum = maximum; }
    }

    public int getPage(){
         return page; 
    }
    public void setPage(int page){ 
        this.page = page; 
    }
    public List<Movies> getResults() { 
        return results; 
    }
    public void setResults(List<Movies> results) {
         this.results = results; 
    }

    public int getTotal_pages() {
         return total_pages; 
    }
    public void setTotal_pages(int total_pages) { 
        this.total_pages = total_pages; 
    }

    public int getTotal_results() { 
        return total_results; 
    }
    public void setTotal_results(int total_results) { 
        this.total_results = total_results;
    }
}
