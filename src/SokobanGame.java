import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SokobanGame extends Application {
    public static void main(String[] args) {
        Application.launch(SokobanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/GameMaster.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Paint.valueOf("#2A2A2A"));

        stage.setTitle("Sokoban");
        stage.getIcons().add(new Image("textures/crate.gif"));
        stage.setScene(scene);
        stage.show();
    }
}