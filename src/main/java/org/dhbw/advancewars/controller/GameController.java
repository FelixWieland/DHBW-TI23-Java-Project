package org.dhbw.advancewars.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.MainApplication;
import org.dhbw.advancewars.entity.Entity;
import org.dhbw.advancewars.level.Level;
import org.dhbw.advancewars.util.Position;

import java.net.URL;
import java.util.Objects;


public class GameController implements IController {
    private Stage stage;
    @FXML
    private Canvas canvas;
    @FXML
    private VBox pane;
    private GraphicsContext graphicsContext;

    private int level = 0;

    public void init(Stage stage, Scene scene) {
        this.stage = stage;
        this.canvas = (Canvas) scene.lookup("#canvas");
        this.pane = (VBox) scene.lookup("#pane");
        this.graphicsContext = this.canvas.getGraphicsContext2D();

        initCurrentLevel();
    }

    private void initCurrentLevel() {
        Level level = getCurrentLevel();
        // FIT PANE TO SAME SIZE AS LEVEL TO DRAW BACKGROUND
        this.pane.setMaxHeight(level.height);
        this.pane.setMinHeight(level.height);
        this.pane.setMaxWidth(level.width);
        this.pane.setMinWidth(level.width);

        this.canvas.setHeight(level.height);
        this.canvas.setWidth(level.width);

        URL url = MainApplication.class.getResource(String.format("assets/levels/%s", level.background));
        Image img = new Image(Objects.requireNonNull(url).toString(), level.width, level.height, false, true);
        BackgroundImage bg = new BackgroundImage(
            img,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT
        );
        this.pane.setBackground(new Background(bg));

        // Render first time
        this.render();
    }

    private Level getCurrentLevel() {
        return Globals.LEVELS[level];
    }

    private void render() {
        Level lvl = getCurrentLevel();

        int heightOfTile = lvl.getHeightOfTile();
        int lengthOfTile = lvl.getLengthOfTile();

        for (Entity entity : lvl.entities) {
            System.out.println(entity.positionOnCanvas());
            URL url = MainApplication.class.getResource(String.format("assets/entities/%s", entity.texture));
            Position pos = entity.positionOnCanvas();
            {
                Image img = new Image(Objects.requireNonNull(url).toString());

                if (entity.getShouldMirrorTexture()) {
                    this.graphicsContext.drawImage(
                            img,
                            pos.x() + lengthOfTile,
                            pos.y(),
                            -lengthOfTile,
                            heightOfTile
                    );
                } else {
                    this.graphicsContext.drawImage(
                            img,
                            pos.x(),
                            pos.y(),
                            lengthOfTile,
                            heightOfTile
                    );
                }
            }
        }

    }
}
