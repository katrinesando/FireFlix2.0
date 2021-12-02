public class Movie extends Medie{
    String title, year, genre,rating;

    public Movie(String title,String year,String genre, String rating) {
        super(title,year,genre,rating);
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }
    public String getGenre(){
        return genre;
    }
    public String getTitle(){
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
