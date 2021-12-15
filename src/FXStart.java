import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FXStart.class.getResource("FXView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FireFlix");
        stage.setScene(scene);
        stage.show();
    }
}
