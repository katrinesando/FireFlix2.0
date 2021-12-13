import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXController implements Initializable {
    @FXML
    private GridPane pane;
    @FXML
    private GridPane paneMyList;
    @FXML
    private GridPane paneUsers;
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
    @FXML
    private TextField username;
    @FXML
    private TextField age;
    @FXML
    private TextField searchBar;
    @FXML
    private Button changeButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button searchButton;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Button changeUserButton;

    public Medie m;
    private static ArrayList<Medie> arr;
    private static String str;
    private static ArrayList<Medie> myList;
    private static ArrayList<Medie> searchList;
    private static ArrayList<ImageView> movieImages;
    private static ArrayList<ImageView> SerieImages;
    //private static ArrayList<User> users;

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

        ImageView img = new ImageView();
        movieImages = new ArrayList<>();
        for (Medie m : arr) {
            if (m instanceof Movie) {
                FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                img = new ImageView(image);
                img.setImage(image);
                movieImages.add(img);
                pane.add(img, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
            getInfo(img,m);
        }
    }

    @FXML
    private void initializeSerie() throws FileNotFoundException {
        int x = 0;
        int y = 3;

        ImageView img = new ImageView();
        SerieImages = new ArrayList<>();
        for (Medie m : arr) {
            if (m instanceof Serie) {
                FileInputStream fl = new FileInputStream("src/serieforsider/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                img = new ImageView(image);
                img.setImage(image);
                SerieImages.add(img);
                paneSerie.add(img, x, y);
                x++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
            getInfo(img,m);
        }
    }

    @FXML
    private void userBtn() throws FileNotFoundException {
        userPane.setVisible(false);
        tab.setVisible(true);
        toolBar.setVisible(true);
        //users.add(new User((username.getText()),(age.getText())));
    }

    @FXML
    private void changeUser() throws FileNotFoundException {
        tab.setVisible(false);
        userPane.setVisible(true);
        toolBar.setVisible(false);
    }


    private void initializeMyList() throws FileNotFoundException {

        int x = 0;//1 virker
        int y = 0;//3 virker
        //tilføjer en masse tilfældeige medier til MyList
        myList.add(arr.get(1));
        myList.add(arr.get(14));
        myList.add(arr.get(27));
        myList.add(arr.get(40));
        myList.add(arr.get(102));
        myList.add(arr.get(150));
        myList.add(arr.get(170));

        for (Medie m : myList) {
            if (m instanceof Movie) {
                FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                ImageView imgTest = new ImageView(image);
                imgTest.setImage(image);

                paneMyList.add(imgTest, x, y);
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
                paneMyList.add(imgTest, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        arr = new ArrayList<Medie>();
        myList = new ArrayList<Medie>();
        loadFileMovie();
        loadFileSerie();
        //System.out.println(m.getTitle());
        try {
            toolBar.setVisible(false);
            initializeMovie();
            initializeSerie();
            initializeMyList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //clipChildren(pane);
    }

    public void searchInput() throws FileNotFoundException {
        searchList = new ArrayList<>();

        int x=0;
        int y=3;

        for (Medie m : arr) {
            if (m.getTitle().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                searchList.add(m);
            }

        }
        for (Medie m : searchList) {
            if (m instanceof Movie) {
                FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                ImageView imgTest = new ImageView(image);
                imgTest.setImage(image);
                pane.getChildren().removeAll(movieImages); //fjerner alle images fra gridpane
                pane.add(imgTest, x, y);
                x++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
            if (m instanceof Serie) {
                FileInputStream fl = new FileInputStream("src/serieforsider/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                ImageView imgTest = new ImageView(image);
                imgTest.setImage(image);
                paneSerie.getChildren().removeAll(SerieImages); //fjerner alle images fra gridpane
                paneSerie.add(imgTest, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
        }
    }

    //pop-up window
    private void getInfo(ImageView imgTest,Medie m){
        Font myFont = new Font("Serie", 16);

        imgTest.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Tile pressed "+m.getTitle() + " --- "+m.getRating());
            // create a popup
            Popup popup = new Popup();
            Label lbl = new Label("Title: " +m.getTitle()
                    +'\n'+"Year: "+m.getYear()
                    +'\n'+"Genre: "+m.getGenre()
                    +'\n'+"Rating: "+m.getRating());
            lbl.setStyle(" -fx-background-color: white;");
            // set size of label
            lbl.setFont(myFont);
            lbl.setMinWidth(70);
            lbl.setMinHeight(100);

            popup.getContent().add(lbl);
            //popup.getContent().add(popUpPane);
            //popUpPane.setVisible(true);
            // set auto hide
            popup.setAutoHide(true);
            if (!popup.isShowing()){
                System.out.println(scrollPane.getHeight());
                System.out.println(scrollPane.getWidth());
                popup.show(pane,(scrollPane.getHeight()),(scrollPane.getWidth()/3));
            }
            event.consume();
        });
    }
    public void refreshSearch() throws FileNotFoundException {
        initializeMovie();
        initializeSerie();
    }

    public void searchGenre(String input) throws FileNotFoundException {
        searchList = new ArrayList<>();

        int x = 0;
        int y = 3;

        for (Medie m : arr) {
            if (m.getGenre().toLowerCase().contains(input.toLowerCase())) {
                searchList.add(m);
            }
        }
        for (Medie m : searchList) {
            if (m instanceof Movie) {
                FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                ImageView imgTest = new ImageView(image);
                imgTest.setImage(image);
                pane.getChildren().removeAll(movieImages); //fjerner alle images fra gridpane
                pane.add(imgTest, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
            if (m instanceof Serie) {
                FileInputStream fl = new FileInputStream("src/serieforsider/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                ImageView imgTest = new ImageView(image);
                imgTest.setImage(image);
                paneSerie.getChildren().removeAll(SerieImages); //fjerner alle images fra gridpane
                paneSerie.add(imgTest, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
        }
    }

    public void searchAdventure() throws FileNotFoundException {searchGenre( "Adventure");}
    @FXML private MenuItem adventureItem;
    public void searchBiography() throws FileNotFoundException {searchGenre( "Biography");}
    @FXML private MenuItem biographyItem;
    public void searchCrime() throws FileNotFoundException {searchGenre( "Crime");}
    @FXML private MenuItem crimeItem;
    public void searchComedy() throws FileNotFoundException {searchGenre( "Comedy");}
    @FXML private MenuItem comedyItem;
    public void searchDrama() throws FileNotFoundException {searchGenre( "Drama");}
    @FXML private MenuItem dramaItem;
    public void searchFamily() throws FileNotFoundException {searchGenre( "Family");}
    @FXML private MenuItem familyItem;
    public void searchFantasy() throws FileNotFoundException {searchGenre( "Fantasy");}
    @FXML private MenuItem fantasyItem;
    public void searchFilmNoir() throws FileNotFoundException {searchGenre( "Film-Noir");}
    @FXML private MenuItem filmNoirItem;
    public void searchHistory() throws FileNotFoundException {searchGenre( "History");}
    @FXML private MenuItem historyItem;
    public void searchHorror() throws FileNotFoundException {searchGenre( "Horror");}
    @FXML private MenuItem horrorItem;
    public void searchMusical() throws FileNotFoundException {searchGenre( "Musical");}
    @FXML private MenuItem musicalItem;
    public void searchMusic() throws FileNotFoundException {searchGenre( "Music");}
    @FXML private MenuItem musicItem;
    public void searchMystery() throws FileNotFoundException {searchGenre( "Mystery");}
    @FXML private MenuItem mysteryItem;
    public void searchRomance() throws FileNotFoundException {searchGenre( "Romance");}
    @FXML private MenuItem romanceItem;
    public void searchSciFi() throws FileNotFoundException {searchGenre( "Sci-fi");}
    @FXML private MenuItem sciFiItem;
    public void searchSport() throws FileNotFoundException {searchGenre( "Sport");}
    @FXML private MenuItem sportItem;
    public void searchThriller() throws FileNotFoundException {searchGenre( "Thriller");}
    @FXML private MenuItem thrillerItem;
    public void searchWar() throws FileNotFoundException {searchGenre( "War");}
    @FXML private MenuItem warItem;
    public void searchWestern() throws FileNotFoundException {searchGenre( "Western");}
    @FXML private MenuItem westernItem;


}



