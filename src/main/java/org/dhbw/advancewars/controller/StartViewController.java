package org.dhbw.advancewars.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.dhbw.advancewars.util.SceneSwapper;

import java.io.IOException;

public class StartViewController implements IController {
    private Stage stage;

    public void init(Stage stage, Scene scene) {
        this.stage = stage;
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            try {
                this.onSpacePress();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void onSpacePress() throws IOException {
        SceneSwapper.swapToScene(this.stage, "game-view");
    }
}