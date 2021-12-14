import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManagement {
    //felter
    private static ArrayList<Medie> arr;
    private static ArrayList<BufferedImage> poster;
    private static String str;
    private static Medie medie;

    //loader filer for Movie og Serie
    public ArrayList<Medie> loadFile() {
        arr = new ArrayList<>();
        try{
            //læser txt for film med BufferedReader og gemmer i Array
            BufferedReader br1 = new BufferedReader(new FileReader("src\\film.txt"));
            while ((str = br1.readLine())!=null){
                String[] line = str.trim().split(";");
                medie = new Movie(line[0],line[1],line[2],line[3]);
                arr.add(medie);
            }

            //læser txt for serier med BufferedReader og gemmer i Array
            BufferedReader br2 = new BufferedReader(new FileReader("src\\serier.txt"));
            while ((str = br2.readLine()) != null) {
                String[] line = str.trim().split(";");
                medie = new Serie(line[0], line[1], line[2], line[3], line[4]);
                arr.add(medie);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return arr;
    }

    //kobler alle film til poster og gemmer i array
    private static void poster(){
        poster = new ArrayList<>();
        //loadFile();
        for(Medie m : arr){
            poster.add(m.getImage(m.getTitle()));
        }
        System.out.println(poster.size());
    }


}
