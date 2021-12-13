import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXController implements Initializable {
    @FXML private GridPane pane,paneMyList,paneUsers,paneSerie,paneNewUser;
    @FXML private ScrollPane scrollPane;
    @FXML private Pane userPane;
    @FXML private TabPane tab;
    @FXML private AnchorPane root;
    @FXML private TextField username,age,searchBar;
    @FXML private Text noGenreError,noSearchMovieError,noSearchSerieError;
    @FXML private Button changeButton,refreshButton,searchButton,changeUserButton,btn;
    @FXML private ToolBar toolBar;
    @FXML private MenuItem adventureItem,biographyItem,crimeItem,comedyItem,dramaItem,familyItem,fantasyItem,filmNoirItem,historyItem,
            horrorItem,musicalItem,musicItem,mysteryItem,romanceItem,sciFiItem,sportItem,thrillerItem,warItem,westernItem;

    public Medie m;
    private static ArrayList<Medie> arr;
    private static String str;
    private static ArrayList<Medie> myList,searchListMovie,searchListSerie;
    private static ArrayList<Medie> searchList;
    private static ArrayList<ImageView> movieImages,serieImages;
    private int userAmount = 0,x=0;
    private static ArrayList<Button> newUsersBtn;
    public Alert alert;
    private User user;
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
        newUsersBtn = new ArrayList<>();
        alert = new Alert(Alert.AlertType.NONE);
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
        serieImages = new ArrayList<>();
        for (Medie m : arr) {
            if (m instanceof Serie) {
                FileInputStream fl = new FileInputStream("src/serieforsider/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                img = new ImageView(image);
                img.setImage(image);
                serieImages.add(img);
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
        //tilføjer user
        if(userAmount<4){
            user = new User(username.getText(),age.getText());
            Button newUser = new Button();
            paneNewUser.add(new Button(user.getName()),x,0);
            newUsersBtn.add(newUser);
            newUser.setVisible(false);
            x++;userAmount++;
        }else{//hvis der er flere end 4 user
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();
        }
        if(!alert.isShowing()){ //går kun videre, hvis alert ikke er vidst
            userPane.setVisible(false);
            tab.setVisible(true);
            toolBar.setVisible(true);
        }
    }

    @FXML
    private void changeUser() throws FileNotFoundException {
        for(Button b : newUsersBtn){
            b.setVisible(true);
        }
        tab.setVisible(false);
        userPane.setVisible(true);
        toolBar.setVisible(false);
    }


    private void initializeMyList() throws FileNotFoundException {

        int x = 0;//1 virker
        int y = 0;//3 virker
        //tilføjer en masse tilfældeige medier til MyList
//        myList.add(arr.get(1));
//        myList.add(arr.get(14));
//        myList.add(arr.get(27));
//        myList.add(arr.get(40));
//        myList.add(arr.get(102));
//        myList.add(arr.get(150));
//        myList.add(arr.get(170));

        for (Medie m : myList) {
            if (m instanceof Movie) {
                FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                ImageView img = new ImageView(image);
                img.setImage(image);
                movieImages.add(img);
                paneMyList.add(img, x, y);
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

                javafx.scene.image.ImageView img = new javafx.scene.image.ImageView(image);
                img.setImage(image);
                serieImages.add(img);
                paneMyList.add(img, x, y);
                x++;
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
            noGenreError.setVisible(false);
            noSearchSerieError.setVisible(false);
            noSearchMovieError.setVisible(false);
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
        noGenreError.setVisible(false);
        noSearchSerieError.setVisible(false);
        noSearchMovieError.setVisible(false);

        searchListMovie = new ArrayList<>();
        searchListSerie = new ArrayList<>();
        pane.getChildren().removeAll(movieImages); //fjerner alle images fra gridpane
        paneSerie.getChildren().removeAll(serieImages); //fjerner alle images fra gridpane

        for (Medie m : arr) {
            if (m instanceof Movie) {
                if (m.getTitle().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                    searchListMovie.add(m);
                }
            }
            if (m instanceof Serie) {
                if (m.getTitle().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                    searchListSerie.add(m);
                }
            }
        }
        int x = 0;
        int y = 3;
        if(searchListMovie.size()<20){
            y=0;
        }

        if(searchListMovie.isEmpty()){
            noSearchMovieError.setVisible(true);
        }

        for (Medie m : searchListMovie) {
            FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
            Image image = new Image(fl);
            ImageView img = new ImageView(image);
            img.setImage(image);
            movieImages.add(img);
            pane.add(img, x, y);
            x++;
            //y++;
            if (x == 10) {
                y++;
                x = 0;
            }
            getInfo(img, m);
        }
        x = 0;
        y = 3;
        if(searchListSerie.size()<20){
            y=0;
        }
        if(searchListSerie.isEmpty()){
            noSearchSerieError.setVisible(true);
        }

        for (Medie m : searchListSerie) {
            FileInputStream fl = new FileInputStream("src/serieforsider/" + m.getTitle() + ".jpg");
            Image image = new Image(fl);
            ImageView img = new ImageView(image);
            img.setImage(image);
            serieImages.add(img);
            paneSerie.add(img, x, y);
            x++;
            //y++;
            if (x == 10) {
                y++;
                x = 0;
            }
            getInfo(img,m);
        }
    }


    //pop-up window
    private void getInfo(ImageView imgTest,Medie m){
        Font myFont = new Font("Serie", 16);

        imgTest.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Tile pressed "+m.getTitle() + " --- "+m.getRating());
            // create a popup
            Popup popup = new Popup();
            Button add = new Button("Add to List");
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
            popup.getContent().add(add);
            add.relocate(0,100);
            //tilføjer event handler til button
            add.addEventHandler(MouseEvent.MOUSE_CLICKED, btnPressed -> {
                System.out.println("Button pressed");
                myList.add(m);
                btnPressed.consume();
            });

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
        noGenreError.setVisible(false);
        noSearchSerieError.setVisible(false);
        noSearchMovieError.setVisible(false);
        pane.getChildren().removeAll(movieImages); //fjerner alle images fra gridpane
        paneSerie.getChildren().removeAll(serieImages); //fjerner alle images fra gridpane
        paneMyList.getChildren().removeAll(movieImages); //fjerner alle images fra gridpane
        paneMyList.getChildren().removeAll(serieImages); //fjerner alle images fra gridpane
        initializeMovie();
        initializeSerie();
        initializeMyList();
    }

    public void searchGenre(String input) throws FileNotFoundException {
        noGenreError.setVisible(false);
        noSearchSerieError.setVisible(false);
        noSearchMovieError.setVisible(false);
        searchListMovie = new ArrayList<>();
        searchListSerie = new ArrayList<>();
        pane.getChildren().removeAll(movieImages); //fjerner alle images fra gridpane
        paneSerie.getChildren().removeAll(serieImages); //fjerner alle images fra gridpane

        for (Medie m : arr) {
            if (m instanceof Movie) {
                if (m.getGenre().toLowerCase().contains(input.toLowerCase())) {
                    searchListMovie.add(m);
                }
            }
            if (m instanceof Serie) {
                if (m.getGenre().toLowerCase().contains(input.toLowerCase())) {
                    searchListSerie.add(m);
                }
            }
        }
        int x = 0;
        int y = 3;
        if(searchListMovie.size()<20){
            y=0;
        }
        for (Medie m : searchListMovie) {
                FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);
                ImageView img = new ImageView(image);
                img.setImage(image);
                movieImages.add(img);
                pane.add(img, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
                getInfo(img, m);
            }

        x = 0; y = 3;
        if(searchListSerie.size()<20){
            y=0;}

        if(searchListSerie.isEmpty()){
            noGenreError.setVisible(true);
        }

        for (Medie m : searchListSerie) {
                FileInputStream fl = new FileInputStream("src/serieforsider/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);
                ImageView img = new ImageView(image);
                img.setImage(image);
                serieImages.add(img);
                paneSerie.add(img, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
                getInfo(img,m);
            }
        }

    public void addMedie(Medie m){
        if(!myList.contains(m)){
            myList.add(m);}
        else {
            myList.remove(m);
        }
    }

    public void searchAdventure() throws FileNotFoundException {searchGenre( "Adventure");}
    public void searchBiography() throws FileNotFoundException {searchGenre( "Biography");}
    public void searchCrime() throws FileNotFoundException {searchGenre( "Crime");}
    public void searchComedy() throws FileNotFoundException {searchGenre( "Comedy");}
    public void searchDrama() throws FileNotFoundException {searchGenre( "Drama");}
    public void searchFamily() throws FileNotFoundException {searchGenre( "Family");}
    public void searchFantasy() throws FileNotFoundException {searchGenre( "Fantasy");}
    public void searchFilmNoir() throws FileNotFoundException {searchGenre( "Film-Noir");}
    public void searchHistory() throws FileNotFoundException {searchGenre( "History");}
    public void searchHorror() throws FileNotFoundException {searchGenre( "Horror");}
    public void searchMusical() throws FileNotFoundException {searchGenre( "Musical");}
    public void searchMusic() throws FileNotFoundException {searchGenre( "Music");}
    public void searchMystery() throws FileNotFoundException {searchGenre( "Mystery");}
    public void searchRomance() throws FileNotFoundException {searchGenre( "Romance");}
    public void searchSciFi() throws FileNotFoundException {searchGenre( "Sci-fi");}
    public void searchSport() throws FileNotFoundException {searchGenre( "Sport");}
    public void searchThriller() throws FileNotFoundException {searchGenre( "Thriller");}
    public void searchWar() throws FileNotFoundException {searchGenre( "War");}
    public void searchWestern() throws FileNotFoundException {searchGenre( "Western");}

}



