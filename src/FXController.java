import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXController implements Initializable {
    @FXML
    private GridPane pane;
    @FXML
    private GridPane paneSerie1;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane paneSerie;
    @FXML
    private Pane userPane;
    @FXML
    private Button btn;
    @FXML
    private TabPane tab;
    @FXML
    private AnchorPane root;
    public Medie m;
    private static ArrayList<Medie> arr;
    private static String str;
    private static ArrayList<Medie> myList = new ArrayList<>();

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

    public void loadFileSerie() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/serier.txt"))) //åbner fil og begynder at læse igennem
        {
            String[] line = null;
            //kører hele fil igennem indtil der ikke er mere og tilføjer dem til array
            while ((str = br.readLine()) != null) {
                line = str.trim().split(";");
                m = new Serie(line[0], line[1], line[2], line[3], line[4]);
                arr.add(m);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initializeMovie() throws FileNotFoundException {
        int x = 0;//1 virker
        int y = 3;//3 virker

        pane.addRow(10);
        ColumnConstraints cc = new ColumnConstraints(100, 100, Double.MAX_VALUE,
                Priority.ALWAYS, HPos.CENTER, true);
        pane.getColumnConstraints().addAll(cc, cc);
        pane.setHgrow(scrollPane, Priority.ALWAYS);

        for (Medie m : arr) {
            if (m instanceof Movie) {
                FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                ImageView imgTest = new ImageView(image);
                imgTest.setImage(image);

                pane.add(imgTest, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
        }
    }

    @FXML
    private void initializeSerie() throws FileNotFoundException {
        int x = 0;
        int y = 3;

        root.setLeftAnchor(paneSerie, 0.0);
        root.setRightAnchor(paneSerie, 0.0);

        for (Medie m : arr) {
            if (m instanceof Serie) {
                FileInputStream fl = new FileInputStream("src/serieforsider/" + m.getTitle() + ".jpg");
                javafx.scene.image.Image image = new Image(fl);

                javafx.scene.image.ImageView imgTest = new javafx.scene.image.ImageView(image);
                imgTest.setImage(image);
                paneSerie.add(imgTest, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
                //if(x==5&&y==7){
                //    continue;
                //}
            }
        }
    }

    @FXML
    private void userBtn() {
        userPane.setVisible(false);
        tab.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        arr = new ArrayList<Medie>();
        loadFileMovie();
        loadFileSerie();
        //System.out.println(m.getTitle());
        try {
            initializeMovie();
            initializeSerie();
            initializeMyList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //clipChildren(pane);
    }

    private void initializeMyList() throws FileNotFoundException {

        int x = 0;//1 virker
        int y = 0;//3 virker

        myList.add(arr.get(1));
        myList.add(arr.get(14));
        myList.add(arr.get(27));
        myList.add(arr.get(40));
        myList.add(arr.get(102));
        myList.add(arr.get(150));
        myList.add(arr.get(170));

        paneSerie1.addRow(10);
        ColumnConstraints cc = new ColumnConstraints(100, 100, Double.MAX_VALUE,
                Priority.ALWAYS, HPos.CENTER, true);
        paneSerie1.getColumnConstraints().addAll(cc, cc);
        paneSerie1.setHgrow(scrollPane, Priority.ALWAYS);

        for (Medie m : myList) {

            if (m instanceof Movie) {
                FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                ImageView imgTest = new ImageView(image);
                imgTest.setImage(image);

                paneSerie1.add(imgTest, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
            if (m instanceof Serie) {
                FileInputStream fl = new FileInputStream("src/serieforsider/" + m.getTitle() + ".jpg");
                javafx.scene.image.Image image = new Image(fl);

                javafx.scene.image.ImageView imgTest = new javafx.scene.image.ImageView(image);
                imgTest.setImage(image);
                paneSerie1.add(imgTest, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
        }


    }
}

