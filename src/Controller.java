import java.util.*;

public class Controller {
    ArrayList<User> users;

    public Controller(){
        users= new ArrayList<>();

    }
    public void addUser(String name, String age){
        if(age!=null && name!=null){
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
