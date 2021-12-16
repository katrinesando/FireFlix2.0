public class Serie extends Medie {
    String episode;

    public Serie(String title, String year, String genre, String rating,String episode) {
        super(title,year, genre, rating);
        this.episode =  episode;

    }
    //getters unikt for Serie
    public String getEpisode(){
        return episode;
    }
    //getters fra Medie
    @Override
    public String getGenre() {
        return genre;
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public String getYear() {
        return year;
    }
    @Override
    public String getRating() {
        return rating;
    }
}
