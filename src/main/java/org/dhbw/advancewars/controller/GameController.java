package org.dhbw.advancewars.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.MainApplication;
import org.dhbw.advancewars.entity.Entity;
import org.dhbw.advancewars.level.Level;
import org.dhbw.advancewars.util.Position;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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

    private Level[] levels;

    public void init(Stage stage, Scene scene) {
        this.stage = stage;
        this.canvas = (Canvas) scene.lookup("#canvas");
        this.pane = (VBox) scene.lookup("#pane");
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.canvas.setOnMouseClicked(this::onMouseClickedOnCanvas);
        this.canvas.setOnMouseMoved(this::onMouseMovedOnCanvas);
        this.canvas.setCursor(Cursor.CROSSHAIR);

        try {
            this.levels = Globals.loadLevels();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        this.initCurrentLevel();
    }

    private void initCurrentLevel() {
        Level level = getCurrentLevel();
        // FIT PANE TO SAME SIZE AS LEVEL TO DRAW BACKGROUND
        this.pane.setMaxHeight(level.getHeight());
        this.pane.setMinHeight(level.getHeight());
        this.pane.setMaxWidth(level.getWidth());
        this.pane.setMinWidth(level.getWidth());

        this.canvas.setHeight(level.getHeight());
        this.canvas.setWidth(level.getWidth());

        this.stage.setMinHeight(level.getHeight());
        this.stage.setMaxHeight(level.getHeight());
        this.stage.setMinWidth(level.getWidth());
        this.stage.setMaxWidth(level.getWidth());


        /*
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

        */

        // Render first time
        this.render();
    }

    private void onMouseClickedOnCanvas(MouseEvent mouseEvent) {
        this.calculateAndSetHoveredTile(mouseEvent);
        this.getCurrentLevel().selectHoveredTile();
        this.render();
    }

    private void onMouseMovedOnCanvas(MouseEvent mouseEvent) {
        this.calculateAndSetHoveredTile(mouseEvent);
        this.render();
    }

    private void calculateAndSetHoveredTile(MouseEvent mouseEvent) {
        double x = mouseEvent.getSceneX();
        double y = mouseEvent.getSceneY();

        int xTile = (int) (x / Globals.TILE_SIZE);
        int yTile = (int) ((this.canvas.getHeight() - y - (Globals.TILE_SIZE / 3)) / Globals.TILE_SIZE);

        this.getCurrentLevel().setHoveredTile(xTile, yTile);
    }

    private Level getCurrentLevel() {
        return this.levels[level];
    }

    private void render() {
        Level lvl = getCurrentLevel();



        lvl.render(this.graphicsContext);

        /*
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
        */

    }
}
