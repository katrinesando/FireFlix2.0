import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Streaming {
    //felter
    ArrayList<String[]> arr;
    String str;
    String[] line;
    Medie medie;
    public Streaming() {
        //inistatitere felter
        arr = new ArrayList<>();
        line = null;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\film.txt"))) //åbner fil og begynder at læse igennem
        {
            //kører hele fil igennem indtil der ikke er mere og tilføjer dem til array
            while ((str = br.readLine()) != null) {
                line = str.trim().split(";");
                medie = new Movie(line[0],line[1],line[2],line[3]);
                //arr.add((str.trim().split(";")));
            }
            System.out.println(Arrays.toString(arr.get(0))); //eksempel med at tage første element ud
            System.out.println("Genre: "+medie.getYear());
            System.out.println("Genre: "+medie.getGenre());
            //tager og iterator igennem hele array'et
           /* for (String[] s : arr) {
                String lineStr = Arrays.toString(s);
                System.out.println(lineStr);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Streaming();

    }
}
