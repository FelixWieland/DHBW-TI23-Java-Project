package org.dhbw.advancewars;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public interface IController {
    void init(Stage stage, Scene scene);

    default void swapToScene(Stage stage, String sceneName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(String.format("%s.fxml", sceneName)));
        Scene scene = new Scene(fxmlLoader.load(), Globals.SCENE_SIZE, Globals.SCENE_SIZE);
        IController controller = (IController) fxmlLoader.getController();
        scene.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource(String.format("styles/%s.css", sceneName))).toExternalForm());
        controller.init(stage, scene);
        stage.setScene(scene);
    }

    default void swapToScene(Stage stage, Scene scene) throws IOException {
        stage.setScene(scene);
    }

}
