public abstract class Medie {
    String title, year, genre, rating;
    public abstract String getGenre();
    public abstract String getTitle();
    public abstract String getYear();
    public abstract String getRating();

    public Medie(String title, String year, String genre, String rating){
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }


}
