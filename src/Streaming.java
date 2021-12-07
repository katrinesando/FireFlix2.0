import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Streaming {
    //felter
    private static ArrayList<Medie> arr;
    private static ArrayList<BufferedImage> poster;
    private static String str;
    private static Medie medie;

    //loader filer
    private static void loadFile() {
        //inistatitere felter
        arr = new ArrayList<>();
        String[] line;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\film.txt"))) //åbner fil og begynder at læse igennem
        {
            //kører hele fil igennem indtil der ikke er mere og tilføjer dem til array
            while ((str = br.readLine()) != null) {
                line = str.trim().split(";");
                medie = new Movie(line[0],line[1],line[2],line[3]);
                arr.add(medie);
            }

            System.out.println("title: "+medie.getTitle());
            System.out.println("year: "+medie.getYear());
            System.out.println("Genre: "+medie.getGenre());
            System.out.println("rating: "+medie.getRating());
            //tager og iterator igennem hele array'et

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //kobler alle film til poster og gemmer i array
    private static void poster(){
        poster = new ArrayList<>();
        loadFile();
        for(Medie m : arr){
            poster.add(m.getImage(m.getTitle()));
        }
        System.out.println(poster.size());
    }
    public static void main(String[] args) {
        poster();
    }
}
