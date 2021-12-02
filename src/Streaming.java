import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Streaming {
    //felter
    private static ArrayList<Medie> arr;
    private static ArrayList<BufferedImage> poster;
    private static String str;
    private static Medie medie;

    //loader poster
    private static BufferedImage getImage(String title) {
        BufferedImage img;
        //new File();
        // This time, you can use an InputStream to load
        try
        {
            // læser fra fil og gemmer i img
            img = ImageIO.read(new File("src\\filmplakater\\" + title+".jpg"));
            // Then read it.
            //java.awt.Desktop.getDesktop().open(new File("src\\filmplakater\\" + title+".jpg"));
            return img;
        } catch (IOException e) {
            System.out.println("The image was not loaded.");
            //System.exit(1);
        }
        return null;
    }
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
            poster.add(getImage(medie.getTitle()));
        }
        System.out.println(poster.size());
    }
    public static void main(String[] args) {
        poster();
    }
}
