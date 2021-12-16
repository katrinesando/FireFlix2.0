public class Movie extends Medie{

    public Movie(String title,String year,String genre, String rating) {
        super(title,year,genre,rating);

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
