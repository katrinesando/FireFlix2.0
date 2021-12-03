public class MedieAlreadyAddedException extends RuntimeException{
        String name;

    public MedieAlreadyAddedException(){
        super(" has already been added to your list");
    }
    public String getName(){
        return name;
    }
}
