import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Streaming {
    //felter
    static ArrayList<String[]> arr;
    static String str;
    static String[] line;
    static Medie medie;
    public static void loadFile() {
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
            //System.out.println(line[0]);
            //System.out.println(Arrays.toString(arr.get(0))); //eksempel med at tage første element ud
            System.out.println("title: "+medie.getTitle());
            System.out.println("year: "+medie.getYear());
            System.out.println("Genre: "+medie.getGenre());
            System.out.println("rating: "+medie.getRating());
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
        loadFile();
    }
}
