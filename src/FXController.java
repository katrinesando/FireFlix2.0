import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXController implements Initializable {
    @FXML private GridPane pane,paneMyList,paneSerie,paneNewUser;
    @FXML private Pane userPane;
    @FXML private TabPane tab;
    @FXML private TextField username,age,searchBar;
    @FXML private Text noGenreError,noSearchMovieError,noSearchSerieError,EmptyMyListError;
    @FXML private ToolBar toolBar;

    public Medie m;
    private static ArrayList<Medie> arr;
    private static ArrayList<Medie> myList,searchListMovie,searchListSerie;
    private static ArrayList<ImageView> images;
    private int userAmount = 0,x=0;
    private static ArrayList<Button> newUsersBtn;
    private Alert alert;
    private Alert alertWarning;
    private Alert popup;
    private User user;
    private FileManagement filemanagement;
    private static ArrayList<User> users;

    //laver imageview af movie og serie
    private ImageView movie(Medie m){
        Image image = new Image(getClass().getResourceAsStream("/filmplakater/" + m.getTitle() + ".jpg"));

        ImageView img = new ImageView(image);
        img.setImage(image);
        images.add(img);
        return img;
    }
    private ImageView serie(Medie m){
        Image image = new Image(getClass().getResourceAsStream("/serieforsider/" + m.getTitle() + ".jpg"));

        ImageView img = new ImageView(image);
        img.setImage(image);
        images.add(img);
        return img;
    }

    //initalisere movie og gemmer i array
    @FXML
    private void initializeMovie() throws FileNotFoundException {
        int x = 0;
        int y = 3;

        ImageView img = new ImageView();

        for (Medie m : arr) {
            if (m instanceof Movie) {
                img = movie(m);
                pane.add(img, x, y);
                x++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
            }
            getInfo(img,m);
        }
    }
    //initalisere serie og gemmer i array
    @FXML
    private void initializeSerie() throws FileNotFoundException {
        int x = 0;
        int y = 3;

        ImageView img = new ImageView();

        for (Medie m : arr) {
            if (m instanceof Serie) {
                img = serie(m);
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
    private void userBtn() {
        users = new ArrayList<>();
        //tjekker om username er empty
        if(userAlert()){
            userAlert();
        }else{
            //tilføjer user
            if(userAmount<4){
            //tjekker om der allerrede eksistere en user med det brugernavn
                for(Button b : newUsersBtn){
                    if(username.getText().equalsIgnoreCase(b.getText())){
                        alertWarning.setContentText("There already exists a user with that username"
                                +'\n'+"Please pick another username");
                        alertWarning.setAlertType(Alert.AlertType.WARNING);
                        alertWarning.show();
                        return;
                    }
                }
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
                alert.setContentText("You can't have more than 4 users"
                        +'\n'+"Please pick an existing user");
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

    private Boolean userAlert(){
       if(username.getText().isEmpty()){
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.setContentText("Username can't be empty"
                   +'\n'+"Please pick a username");
           alert.show();
           return true;
           //tjekker om age er empty
       }else if(age.getText().isEmpty()){
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.setContentText("Age field can't be empty"
                   +'\n'+"Please fill out your age");
           alert.show();
           return true;
           //tjekker om age er et tal - isNumeric metoden er nederst i dokumentet
       }else if(!isNumeric((age.getText()))){
           alert.setContentText("Age has to be a number"
                   +'\n'+"Please enter a valid number");
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.show();
           return true;
           //tjekker om age er et positivt tal
       }else if(age.getText().contains("-")||age.getText().equals("0")) {
           alert.setContentText("Age has to be a positive number"
                   + '\n' + "Please enter a valid number");
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.show();
           return true;
       }
       return false;
   }
    @FXML
    private void changeUser() {
        tab.setVisible(false);
        userPane.setVisible(true);
        toolBar.setVisible(false);
        for(Button b : newUsersBtn){
            b.setVisible(true);
            b.setPadding(new Insets(15));
        }
    }

    private void initializeMyList() throws FileNotFoundException {
        int x = 0;
        int y = 0;
        ImageView img = new ImageView();

        if(myList.isEmpty()){
            EmptyMyListError.setVisible(true);
        }
        for (Medie m : myList) {
            if (m instanceof Movie) {
                img = movie(m);
                paneMyList.add(img, x, y);
                x++;
                if (x == 10) {
                    y++;
                    x = 0;
                }
                getInfo(img,m);
            }
            if (m instanceof Serie) {
                img = serie(m);
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
        arr = new ArrayList<>();
        myList = new ArrayList<>();
        images = new ArrayList<>();
        newUsersBtn = new ArrayList<>();
        searchListMovie = new ArrayList<>();
        searchListSerie = new ArrayList<>();

        alert = new Alert(Alert.AlertType.NONE);
        alertWarning = new Alert(Alert.AlertType.NONE);
        popup = new Alert(Alert.AlertType.NONE);
        filemanagement = new FileManagement();
        arr = filemanagement.loadFile();

        try {
            initializeMovie();
            initializeSerie();
            initializeMyList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void errorMes(){
        noGenreError.setVisible(false);
        noSearchSerieError.setVisible(false);
        noSearchMovieError.setVisible(false);
    }

    public void searchInput() {
        errorMes();
        searchListMovie.clear();
        searchListSerie.clear();
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
        search( x,  y,  noGenreError, searchListMovie,searchListSerie);
    }

    //pop up window med alert
    private void getInfo(ImageView img, Medie m){
        img.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Button add = new Button("Add to List");
            Button remove = new Button("Delete from List");
            Button play = new Button("Play");

            btnMyList(add,remove,play,m);

            popup.setGraphic(null);
            popup.setAlertType(Alert.AlertType.CONFIRMATION);
            popup.setHeaderText(m.getTitle());
            popup.setTitle("Information");

            GridPane gridPop = new GridPane();
            gridPop.addRow(0,new Label("   Year: " + m.getYear(), new Label("Rating: "+ m.getRating())));
            gridPop.addRow(1,new Label("Genre: " + m.getGenre()));
            if(m instanceof Serie s){
                gridPop.addRow(2,new Label("Season & episodes: " + s.getEpisode()));
            }
            gridPop.addRow(3,play,add,remove);
            popup.getDialogPane().setContent(gridPop);
            popup.show();

            event.consume();
        });
    }

    private void btnMyList(Button add, Button remove,Button play,Medie m){
        //tilføjer event handler til button
        add.addEventHandler(MouseEvent.MOUSE_CLICKED, btnPressed -> {
            try {
                addMedie(m);
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
        play.addEventHandler(MouseEvent.MOUSE_CLICKED, btnPressed -> {
            Alert popupPlay = new Alert(Alert.AlertType.CONFIRMATION);

            FileInputStream fl = null;
            try {
                fl = new FileInputStream("Materials/playingBtn.png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image(fl);
            ImageView img = new ImageView(image);
            img.setImage(image);

            img.setPreserveRatio(true);
            img.setFitHeight(150);

            //viser popup menu med play button
            popupPlay.getDialogPane().setPrefSize(200,200);
            popupPlay.getDialogPane().setHeader(img);
            popupPlay.getDialogPane().setGraphic(null);
            popupPlay.setHeaderText("");
            popupPlay.setTitle("Now Playing: " + m.getTitle());
            popup.hide();
            popupPlay.show();
            btnPressed.consume();
        });
    }

    public void refreshSearch() throws FileNotFoundException {
        errorMes();
        pane.getChildren().removeAll(images); //fjerner alle images fra gridpane
        paneSerie.getChildren().removeAll(images); //fjerner alle images fra gridpane
        paneMyList.getChildren().removeAll(images); //fjerner alle images fra gridpane

        initializeMovie();
        initializeSerie();
        initializeMyList();
    }

    public void searchGenre(String input) {
        errorMes();

        searchListMovie.clear();
        searchListSerie.clear();
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
        search( x,  y,  noGenreError, searchListMovie,searchListSerie);
    }

    private void search(int x, int y, Text noGenreError,ArrayList<Medie> searchListMovie,ArrayList<Medie> searchListSerie) {
        for (Medie m : searchListMovie) {
                ImageView img = movie(m);
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
            y=0;}

        if(searchListSerie.isEmpty()){
            noGenreError.setVisible(true);
        }

        for (Medie m : searchListSerie) {
                ImageView img = serie(m);
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
            if(m instanceof Movie) {
                alertWarning.setContentText("This movie is already on your list");
            } else {
                alertWarning.setContentText("This serie is already on your list");
            }
            alertWarning.setAlertType(Alert.AlertType.WARNING);
            alertWarning.show();
        }
        paneMyList.getChildren().removeAll(images);
        EmptyMyListError.setVisible(false);
        initializeMyList();
    }

    public void removeMedie(Medie m) throws FileNotFoundException {
        if(myList.contains(m)){
            myList.remove(m);}
        else {
            if(m instanceof Movie) {
                alertWarning.setContentText("This movie isn't on your list");
            } else {
                alertWarning.setContentText("This serie isn't on your list");
            }
            alertWarning.setAlertType(Alert.AlertType.WARNING);
            alertWarning.show();
        }
        paneMyList.getChildren().removeAll(images);
        EmptyMyListError.setVisible(false);
        initializeMyList();
    }

    // metode for at tjekke om en string er et tal: https://stackabuse.com/java-check-if-string-is-a-number/
    public static boolean isNumeric(String string) {
        int intValue;
        if(string == null || string.equals("")) {
            return false;
        }try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {}
        return false;
    }

    public void searchAction() {searchGenre( "Action");}
    public void searchAdventure() {searchGenre( "Adventure");}
    public void searchBiography() {searchGenre( "Biography");}
    public void searchCrime() {searchGenre( "Crime");}
    public void searchComedy() {searchGenre( "Comedy");}
    public void searchDrama() {searchGenre( "Drama");}
    public void searchFamily() {searchGenre( "Family");}
    public void searchFantasy() {searchGenre( "Fantasy");}
    public void searchFilmNoir() {searchGenre( "Film-Noir");}
    public void searchHistory() {searchGenre( "History");}
    public void searchHorror() {searchGenre( "Horror");}
    public void searchMusical() {searchGenre( "Musical");}
    public void searchMusic() {searchGenre( "Music");}
    public void searchMystery() {searchGenre( "Mystery");}
    public void searchRomance() {searchGenre( "Romance");}
    public void searchSciFi() {searchGenre( "Sci-fi");}
    public void searchSport() {searchGenre( "Sport");}
    public void searchThriller() {searchGenre( "Thriller");}
    public void searchWar() {searchGenre( "War");}
    public void searchWestern() {searchGenre( "Western");}

}



