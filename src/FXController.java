import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class FXController implements Initializable {
    @FXML private GridPane pane,paneMyList,paneUsers,paneSerie,paneNewUser;
    @FXML private ScrollPane scrollPane;
    @FXML private Pane popUpPane;
    @FXML private Pane userPane;
    @FXML private TabPane tab;
    @FXML private AnchorPane root;
    @FXML private TextField username,age,searchBar;
    @FXML private Text noGenreError,noSearchMovieError,noSearchSerieError,EmptyMyListError;
    @FXML private Button changeButton,refreshButton,searchButton,changeUserButton,btn;
    @FXML private ToolBar toolBar;
    @FXML private MenuItem adventureItem,biographyItem,crimeItem,comedyItem,dramaItem,familyItem,fantasyItem,filmNoirItem,historyItem,
            horrorItem,musicalItem,musicItem,mysteryItem,romanceItem,sciFiItem,sportItem,thrillerItem,warItem,westernItem;

    @FXML private MenuItem actionItem;

    public Medie m;
    private static ArrayList<Medie> arr;
    private static String str;
    private static ArrayList<Medie> myList,searchListMovie,searchListSerie;
    private static ArrayList<Medie> searchList;
    private static ArrayList<ImageView> images;
    private int userAmount = 0,x=0;
    private static ArrayList<Button> newUsersBtn;
    public Alert alert;
    private User user;
    private FileManagement filemanagement;
    private static ArrayList<User> users;



    @FXML
    private void initializeMovie() throws FileNotFoundException {
        int x = 0;//1 virker
        int y = 3;//3 virker

        ImageView img = new ImageView();

        for (Medie m : arr) {
            if (m instanceof Movie) {
                FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                img = new ImageView(image);
                img.setImage(image);
                images.add(img);
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

        for (Medie m : arr) {
            if (m instanceof Serie) {
                FileInputStream fl = new FileInputStream("src/serieforsider/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                img = new ImageView(image);
                img.setImage(image);
                images.add(img);
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
        users = new ArrayList<>();
        //tjekker om username er empty
        if(username.getText().isEmpty()){
            alert.setContentText("Username can't be empty");
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.show();
        }else if(age.getText().isEmpty()){
            alert.setContentText("Age field can't be empty");
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.show();
        }else{
            //tilføjer user
            if(userAmount<4){
                /*for(User u:users){
                    if(username.getText().toLowerCase().equals(u.getName())){
                        alert.setContentText("There already exists a user with that username");
                        alert.setAlertType(Alert.AlertType.WARNING);
                        alert.show();
                    }
                }*/
                user = new User(username.getText(),age.getText());
                users.add(user);
                Button newUser = new Button(user.getName());
                newUser.setOnAction((ActionEvent e) -> {
                    if(!alert.isShowing()){ //går kun videre, hvis alert ikke er vidst
                        userPane.setVisible(false);
                        tab.setVisible(true);
                        toolBar.setVisible(true);
                    }
                });
                paneNewUser.add(newUser,x,0);
                newUsersBtn.add(newUser);
                newUser.setVisible(false);
                x++;userAmount++;
            }else{//hvis der er flere end 4 user
                alert.setContentText("4 users is the max to be added");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.show();
            }
            if(!alert.isShowing()){ //går kun videre, hvis alert ikke er vidst
                userPane.setVisible(false);
                tab.setVisible(true);
                toolBar.setVisible(true);
            }
        }

    }
    @FXML
    private void changeUser() throws FileNotFoundException {
        tab.setVisible(false);
        userPane.setVisible(true);
        toolBar.setVisible(false);
        for(Button b : newUsersBtn){
            b.setVisible(true);
        }
    }

    private void initializeMyList() throws FileNotFoundException {

        int x = 0;//1 virker
        int y = 0;//3 virker
        if(myList.isEmpty()){
            EmptyMyListError.setVisible(true);
        }
        for (Medie m : myList) {
            if (m instanceof Movie) {
                FileInputStream fl = new FileInputStream("src/filmplakater/" + m.getTitle() + ".jpg");
                Image image = new Image(fl);

                ImageView img = new ImageView(image);
                img.setImage(image);
                images.add(img);
                paneMyList.add(img, x, y);
                x++;
                //y++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
                getInfo(img,m);
            }
            if (m instanceof Serie) {
                FileInputStream fl = new FileInputStream("src/serieforsider/" + m.getTitle() + ".jpg");
                javafx.scene.image.Image image = new Image(fl);

                javafx.scene.image.ImageView img = new javafx.scene.image.ImageView(image);
                img.setImage(image);
                images.add(img);
                paneMyList.add(img, x, y);
                x++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
                getInfo(img,m);
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialisere felter
        arr = new ArrayList<Medie>();
        myList = new ArrayList<Medie>();
        images = new ArrayList<>();
        newUsersBtn = new ArrayList<>();
        alert = new Alert(Alert.AlertType.NONE);
        filemanagement = new FileManagement();
        arr = filemanagement.loadFile();

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
        pane.getChildren().removeAll(images); //fjerner alle images fra gridpane
        paneSerie.getChildren().removeAll(images); //fjerner alle images fra gridpane

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
            images.add(img);
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
            images.add(img);
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
            Button remove = new Button("Delete from List");
            Label background = new Label();
            background.setMinSize(300,200);
            Label lbl = new Label("Title: " +m.getTitle()
                    +'\n'+"Year: "+m.getYear()
                    +'\n'+"Genre: "+m.getGenre()
                    +'\n'+"Rating: "+m.getRating()
                    +'\n'+"  ");
            background.setStyle(" -fx-background-color: white;");
            // set size of label
            lbl.setFont(myFont);

            popup.getContent().add(background);
            popup.getContent().add(lbl);
            popup.getContent().add(add);
            popup.getContent().add(remove);

            lbl.relocate(10,10);
            add.relocate(0,100);
            remove.relocate(80,100);
            //tilføjer event handler til button
            btnMyList(add,remove,m);
            // set auto hide
            popup.setAutoHide(true);
            if (!popup.isShowing()){
                System.out.println(scrollPane.getHeight());
                System.out.println(scrollPane.getWidth());
//                popup.show();
                popup.show(paneMyList,(scrollPane.getHeight()),(scrollPane.getWidth()/3));
            }
            event.consume();
        });
    }
    private void btnMyList(Button add, Button remove,Medie m){
        //tilføjer event handler til button
        add.addEventHandler(MouseEvent.MOUSE_CLICKED, btnPressed -> {
            try {
                addMedie(m);
                System.out.println(m.getTitle());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            btnPressed.consume();
        });
        remove.addEventHandler(MouseEvent.MOUSE_CLICKED, btnPressed -> {
            try {
                removeMedie(m);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            btnPressed.consume();
        });
    }
    public void refreshSearch() throws FileNotFoundException {
        noGenreError.setVisible(false);
        noSearchSerieError.setVisible(false);
        noSearchMovieError.setVisible(false);
        pane.getChildren().removeAll(images); //fjerner alle images fra gridpane
        paneSerie.getChildren().removeAll(images); //fjerner alle images fra gridpane
        paneMyList.getChildren().removeAll(images); //fjerner alle images fra gridpane

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
        pane.getChildren().removeAll(images); //fjerner alle images fra gridpane
        paneSerie.getChildren().removeAll(images); //fjerner alle images fra gridpane

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
                images.add(img);
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
                images.add(img);
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

    public void addMedie(Medie m) throws FileNotFoundException {
        if(!myList.contains(m)){
            myList.add(m);}
        else {
            alert.setContentText("This medie is already on your list");
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.show();
        }
        paneMyList.getChildren().removeAll(images);
        EmptyMyListError.setVisible(false);
        initializeMyList();
    }

    public void removeMedie(Medie m) throws FileNotFoundException {
        if(myList.contains(m)){
            myList.remove(m);}
        else {
            alert.setContentText("This medie isn't on your list");
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.show();
        }
        paneMyList.getChildren().removeAll(images);
        EmptyMyListError.setVisible(false);
        initializeMyList();
    }

    public void searchAction() throws FileNotFoundException {searchGenre( "Action");}
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



