package org.dhbw.advancewars;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Globals.SCENE_SIZE, Globals.SCENE_SIZE);
        IController controller = (IController) fxmlLoader.getController();
        scene.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles/start-view.css")).toExternalForm());
        controller.init(stage, scene);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}