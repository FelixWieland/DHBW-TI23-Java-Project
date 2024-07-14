package org.dhbw.advancewars.controller;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.level.Level;
import org.dhbw.advancewars.util.SceneSwapper;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;

public class StartViewController implements IController {
    private Stage stage;

    public void init(Stage stage, Scene scene) {
        this.stage = stage;

        try {
            ListView<String> listView = new ListView<>();
            listView.getItems().addAll(Arrays.stream(Globals.loadLevels()).map(Level::getName).toList());
            listView.setOnMouseClicked(event -> {
                try {
                    stage.setTitle(listView.getSelectionModel().getSelectedItem());
                    SceneSwapper.swapToScene(this.stage, "game-view");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            var root = (VBox) scene.lookup("#vbox");
            root.getChildren().add(listView);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }


    }
}