package ru.sl1degod.demoexamen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.sl1degod.demoexamen.models.SceneModel;
import ru.sl1degod.demoexamen.models.StageModel;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StageModel.setMyStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("authorization.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        SceneModel.setMyScene(scene);
        stage.setTitle("Способ входа");
        stage.setScene(scene);
        stage.show();
    }

    public void openNewScene(AnchorPane root, String window, String title) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(window)));
            root.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        launch();
    }
}