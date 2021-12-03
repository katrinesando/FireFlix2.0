import java.util.*;

public class Controller {
    ArrayList<User> users;
//burde arrayListen af users blive initieret i main class når man starter programmet?
    public Controller(){
        users= new ArrayList<>();

    }
    public void addUser(String name, int age){
        User user = new User(name,age);
        users.add(user);
    }
    public void deleteUser(){
        //actionlistener
        //users.remove(user);
    }
    public void chooseUser(){

    }
    public void changeUser(){
        //actionlistener
    }
}
