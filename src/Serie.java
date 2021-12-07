import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Serie extends Medie {
    String startEnd;

    public Serie(String title, String year, String genre, String rating,String startEnd) {
        super(title,year, genre, rating);
        this.startEnd =  startEnd;
    }
    @Override
    public BufferedImage getImage(String title) {
        try
        {
            // Grab the InputStream for the image and save in img
            File file = new File("src\\serieforsider\\" + title+".jpg");
            BufferedImage img = ImageIO.read(file);
            //java.awt.Desktop.getDesktop().open(file);
            return img;

        } catch (IOException e) {
            System.out.println("The serie image was not loaded for: "+title);
            //System.exit(1);
        }
        return null;
    }

    //getters unikt for Serie
    public String getStartEnd(){
        return startEnd;
    }
    //getters fra Medie
    @Override
    public String getGenre() {
        return genre;
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public String getYear() {
        return year;
    }
    @Override
    public String getRating() {
        return rating;
    }
}
