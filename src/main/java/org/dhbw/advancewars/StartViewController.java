package org.dhbw.advancewars;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
        this.swapToScene(this.stage, "game-view");
    }
}