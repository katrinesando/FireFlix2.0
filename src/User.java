import java.util.*;

public class User {
    String name;
    String age;
    //Have en dropdown menu hvor man kan vælge farve ens "avatar" skal have
    ArrayList<Medie> myList;

    public User(String name, String age){
        this.name=name;
        this.age=age;
        myList= new ArrayList<>();
    }

    public String getName(){
        return name;
    }
    public void removeMedie(Medie m){
        myList.remove(m);
    }
    public void displayMyList(){
        for(Medie m:myList){
            if(m instanceof Movie a) {
                System.out.println(a.getTitle() + " " + a.getYear() + " " + a.getGenre() + " " + a.getRating());
            } else if (m instanceof Serie b){
                System.out.println(b.getTitle() + " " + b.getYear() + " " + b.getGenre() + " " + b.getRating()+" "+b.getEpisode());
            }
        }
    }

}

