package org.dhbw.advancewars;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dhbw.advancewars.controller.IController;
import org.dhbw.advancewars.util.SceneSwapper;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        this.initStage(stage);
        this.initStartScene(stage);
        stage.show();
    }

    void initStage(Stage stage) {
        stage.setTitle(Globals.START_SCREEN_TITLE);
        stage.setMaxWidth(Globals.WINDOW_SIZE);
        stage.setMinWidth(Globals.WINDOW_SIZE);
        stage.setMaxHeight(Globals.WINDOW_SIZE);
        stage.setMinHeight(Globals.WINDOW_SIZE);
    }

    void initStartScene(Stage stage) throws IOException {
        SceneSwapper.swapToScene(stage, "start-view");
    }

    public static void main(String[] args) {
        launch();
    }
}