import java.util.*;

public class Controller {
    ArrayList<User> users;

    public Controller(){
        users= new ArrayList<>();

    }
    public void addUser(String name, int age){
        if(age>=0 && name!=null){
            User user = new User(name,age);
            users.add(user);
        }

    }
    public void deleteUser(User u){
        //actionlistener
        users.remove(u);
    }
    public void chooseUser(){

    }
    public void changeUser(){
        //actionlistener
    }
}
