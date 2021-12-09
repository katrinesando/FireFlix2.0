import java.util.*;

public class User {
    String name;
    int age;
    //Have en dropdown menu hvor man kan vælge farve ens "avatar" skal have
    ArrayList<Medie> myList;

    public User(String name, int age){
        this.name=name;
        this.age=age;
        myList= new ArrayList<>();
    }

    public void addMedie(Medie m) throws MedieAlreadyAddedException{
        if(!myList.contains(m)){
            myList.add(m);}
        else {
            if(m instanceof Movie a) {
                throw new MedieAlreadyAddedException(a.getTitle() +" has already been added to your list");
            } else if (m instanceof Serie b){
                throw new MedieAlreadyAddedException(b.getTitle() +" has already been added to your list");
            }
        }
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

