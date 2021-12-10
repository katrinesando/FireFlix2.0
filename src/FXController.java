import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXController implements Initializable {
    @FXML
    private GridPane pane;
    @FXML
    private javafx.scene.control.ScrollPane scrollPane;
    @FXML
    private GridPane paneSerie;
    @FXML
    private TabPane tab;
    @FXML
    private AnchorPane root;
    public Medie m;
    private static ArrayList<Medie> arr;
    private static String str;

    public void loadFileMovie() {
        //inistatitere felter
        try (BufferedReader br = new BufferedReader(new FileReader("src/film.txt"))) //åbner fil og begynder at læse igennem
        {
            String[] line = null;
            //kører hele fil igennem indtil der ikke er mere og tilføjer dem til array
            while ((str = br.readLine()) != null) {
                line = str.trim().split(";");
                m = new Movie(line[0], line[1], line[2], line[3]);
                arr.add(m);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadFileSerie(){
        try (BufferedReader br = new BufferedReader(new FileReader("src/serier.txt"))) //åbner fil og begynder at læse igennem
        {
            String[] line = null;
            //kører hele fil igennem indtil der ikke er mere og tilføjer dem til array
            while ((str = br.readLine()) != null) {
                line = str.trim().split(";");
                m = new Serie(line[0], line[1], line[2], line[3],line[4]);
                arr.add(m);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() throws FileNotFoundException {
        int x = 1;//1 virker
        int y = 3;//3 virker

        pane.addRow(10);
        ColumnConstraints cc = new ColumnConstraints(100, 100, Double.MAX_VALUE,
                Priority.ALWAYS, HPos.CENTER, true);
        pane.getColumnConstraints().addAll(cc, cc);
        pane.setHgrow(scrollPane, Priority.ALWAYS);

        for(Medie m : arr){
            if(m instanceof Movie){
                FileInputStream fl = new FileInputStream("src\\filmplakater\\"+m.getTitle()+".jpg");
                Image image = new Image(fl);

                ImageView imgTest = new ImageView(image);
                imgTest.setImage(image);

                pane.add(imgTest,x,y);
                x++;
                //y++;
                if(x==10){
                    y++;
                    x=0;
                }
            }
        }
    }

    @FXML
    private void initializeSerie() throws FileNotFoundException {
        int x = 1;
        int y = 3;

        root.setLeftAnchor(paneSerie, 0.0);
        root.setRightAnchor(paneSerie, 0.0);

        for(Medie m : arr){
            if(m instanceof Serie){
                FileInputStream fl = new FileInputStream("src\\serieforsider\\"+m.getTitle()+".jpg");
                javafx.scene.image.Image image = new Image(fl);

                javafx.scene.image.ImageView imgTest = new javafx.scene.image.ImageView(image);
                imgTest.setImage(image);
                paneSerie.add(imgTest,x,y);
                x++;
                //y++;
                if(x==5){
                    y++;
                    x=0;
                }
                if(x==5&&y==7){
                    continue;
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        arr = new ArrayList<Medie>();

        loadFileMovie();
        loadFileSerie();
        System.out.println(m.getTitle());
        try {
            initialize();
            initializeSerie();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //clipChildren(pane);
    }
}
