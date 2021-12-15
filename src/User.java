import java.util.*;

public class User {
    String name;
    String age;
    ArrayList<Medie> myList;

    public User(String name, String age){
        this.name=name;
        this.age=age;
        myList= new ArrayList<>();
    }
    public String getName(){
        return name;
    }
}

