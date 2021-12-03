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
    public void addMedie(){
        // try {
        // if(!myList.contains(Medie)){
        //    myList.add(Medie);}
        //} catch ( MedieAlreadyAddedException e) {
        // System.out.println(e.getName()+e.getMessage()); }
    }
    public void removeMedie(){
        //myList.remove(Medie);
    }

    public void displayMyList(){
        //for(Medie m:medie){
        //m.display;}
    }

}
