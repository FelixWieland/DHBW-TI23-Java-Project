package org.dhbw.advancewars.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.MainApplication;
import org.dhbw.advancewars.controller.IController;

import java.io.IOException;
import java.util.Objects;

public class SceneSwapper {
    public static void swapToScene(Stage stage, String sceneName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(String.format("%s.fxml", sceneName)));
        Scene scene = new Scene(fxmlLoader.load(), Globals.SCENE_SIZE, Globals.SCENE_SIZE);
        IController controller = fxmlLoader.getController();
        scene.getStylesheets().addAll(Objects.requireNonNull(MainApplication.class.getResource(String.format("styles/%s.css", sceneName))).toExternalForm());
        controller.init(stage, scene);
        stage.setScene(scene);
    }

    public static void swapToScene(Stage stage, Scene scene) throws IOException {
        stage.setScene(scene);
    }
}
