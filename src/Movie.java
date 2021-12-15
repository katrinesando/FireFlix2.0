import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Movie extends Medie{

    public Movie(String title,String year,String genre, String rating) {
        super(title,year,genre,rating);

    }

    @Override
    public BufferedImage getImage(String title) {
        try
        {
            // Grab the InputStream for the image and save in img
            File file = new File("src/filmplakater/" + title+".jpg");
            //java.awt.Desktop.getDesktop().open(file);
            return ImageIO.read(file);

        } catch (IOException e) {
            System.out.println("The serie image was not loaded for: "+title);
            //System.exit(1);
        }
        return null;
    }

    public String getGenre(){
        return genre;
    }
    public String getTitle(){
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
