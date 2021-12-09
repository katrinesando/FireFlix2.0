public class Serie extends Medie {
    String startEnd;

    public Serie(String title, String year, String genre, String rating,String startEnd) {
        super(title,year, genre, rating);
        this.startEnd =  startEnd;
    }

    //getters unikt for Serie
    public String getStartEnd(){
        return startEnd;
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
