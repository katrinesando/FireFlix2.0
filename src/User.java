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
    public void addMedie(Medie m){
        try {
        if(!myList.contains(m)){
            myList.add(m);}
        } catch ( MedieAlreadyAddedException e) {
            System.out.println(e.getName()+e.getMessage()); }
    }
    public void removeMedie(Medie m){
        myList.remove(m);
    }
    public void display(){
        for(Medie m:myList){

                System.out.println(m.getTitle()+" "+m.getYear()+" "+m.getGenre()+" "+m.getRating());
                //lave forskel på serier og medier
        }
    }
}

