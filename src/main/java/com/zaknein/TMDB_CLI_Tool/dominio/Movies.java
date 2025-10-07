

public class Movies{
    String date;
    String title;
    String overview;

    public Movies(){}

    public Movies(String date, String title, String overview){
        this.date = date;
        this.title = title;
        this.overview = overview;
    }

    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
    public void setOverview(String overview){
        this.overview = overview;
    }
    public String getOverview(){
        return overview;
    }
}