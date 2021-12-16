import java.io.InputStream;
import java.util.ArrayList;

public class StreamingsTest {

    //skal printe true, Star wars, Game of Thrones, true, true, true
    public static void main (String[] args){
        testUser();
        testMyList();
        searchTest();
        searchGenreTest();
        loadTest();
    }
    //skal printe true
    public static void testUser(){
        User u1 = new User("superDan","42");
        User u2 = new User("Claus","37");
        User u3 = new User("Rasmus","21");
        User u4 = new User("Katrine","22");
        ArrayList<User> users = new ArrayList<>();
        users.add(u1); users.add(u2); users.add(u3); users.add(u4);
        if(users.size()==4){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    // skal printe Star Wars, Game of Thrones
    public static void testMyList(){
        Movie SW = new Movie("Star Wars", "1977","Action, Adventure, Family","8,6");
        Movie TG = new Movie("The Godfather", "1972","Crime, Drama","9,2");
        Serie GOT = new Serie("Game Of Thrones","2011-","Action, Adventure, Drama","9,5","1-10, 2-10, 3-10, 4-10, 5-10, 6-10, 7-7");
        ArrayList<Medie> myList = new ArrayList<Medie>();
        myList.add(SW); myList.add(TG); myList.add(GOT);
        myList.remove(TG);
        for(Medie m:myList){
            System.out.println(m.getTitle());
        }
    }

    //skal printe true
    public static void searchTest() {
        Movie SW = new Movie("Star Wars", "1977", "Action, Adventure, Family", "8,6");
        Movie TG = new Movie("The Godfather", "1972", "Crime, Drama", "9,2");
        Serie GOT = new Serie("Game Of Thrones", "2011-", "Action, Adventure, Drama", "9,5", "1-10, 2-10, 3-10, 4-10, 5-10, 6-10, 7-7");
        ArrayList<Medie> media = new ArrayList<Medie>();
        ArrayList<Medie> sl = new ArrayList<Medie>();
        media.add(SW); media.add(TG); media.add(GOT);
        String searchWord="Star";

        for(Medie m:media){
            if(m.getTitle().toLowerCase().contains(searchWord.toLowerCase())){
                sl.add(m);
            }
        }

        if(sl.contains(SW)){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }

    //skal printe true
    public static void searchGenreTest() {
        Movie SW = new Movie("Star Wars", "1977", "Action, Adventure, Family", "8,6");
        Movie TG = new Movie("The Godfather", "1972", "Crime, Drama", "9,2");
        Serie GOT = new Serie("Game Of Thrones", "2011-", "Action, Adventure, Drama", "9,5", "1-10, 2-10, 3-10, 4-10, 5-10, 6-10, 7-7");
        ArrayList<Medie> media = new ArrayList<Medie>();
        ArrayList<Medie> sl = new ArrayList<Medie>();
        media.add(SW); media.add(TG); media.add(GOT);
        String genre="Action";
        for(Medie m:media){
            if(m.getGenre().toLowerCase().contains(genre.toLowerCase())){
                sl.add(m);
            }
        }
        if(sl.contains(SW)&& sl.contains(GOT)){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }
    public static void loadTest(){
        FileManagement fl=new FileManagement();
        int amountOfSeries=100;
        int amountOfMovies=100;
        if(fl.loadFile().size()==amountOfSeries+amountOfMovies){
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}

