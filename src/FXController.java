import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXController implements Initializable {
    private ArrayList<Medie> medieArr;
    public FileManagement files;
    @FXML
    private Pane panel;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ImageView imageview;
    //private JPanel panel;

    //kan kobles op sammen med et knap
    public void createPic(){
        medieArr = files.getArr();
        for(Medie m : medieArr){
            BufferedImage image = m.getImage(m.getTitle());
            imageview = new ImageView((Element) image);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
