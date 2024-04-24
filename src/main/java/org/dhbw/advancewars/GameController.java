package org.dhbw.advancewars;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.dhbw.advancewars.level.Level;

import java.net.URL;
import java.util.Objects;


public class GameController implements IController {
    private Stage stage;
    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane pane;

    private int level = 0;

    public void init(Stage stage, Scene scene) {
        this.stage = stage;
        this.canvas = (Canvas) scene.lookup("#canvas");
        this.pane = (AnchorPane) scene.lookup("#pane");

        initCurrentLevel();
    }

    private void initCurrentLevel() {
        Level level = getCurrentLevel();
        // FIT PANE TO SAME SIZE AS LEVEL TO DRAW BACKGROUND
        this.pane.setMaxHeight(level.height);
        this.pane.setMinHeight(level.height);
        this.pane.setMaxWidth(level.width);
        this.pane.setMinWidth(level.width);

        URL url = MainApplication.class.getResource(String.format("assets/levels/%s", level.background));
        Image img = new Image(Objects.requireNonNull(url).toString(), level.height, level.width, false, true);
        BackgroundImage bg = new BackgroundImage(
            img,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT
        );
        this.pane.setBackground(new Background(bg));

    }

    private Level getCurrentLevel() {
        return Globals.LEVELS[level];
    }
}
