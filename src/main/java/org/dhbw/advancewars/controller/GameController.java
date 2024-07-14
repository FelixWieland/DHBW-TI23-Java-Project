package org.dhbw.advancewars.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.level.Level;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;


public class GameController implements IController {
    private Stage stage;
    @FXML
    private Canvas canvas;
    @FXML
    private VBox pane;
    private GraphicsContext graphicsContext;


    private Level level;

    public void init(Stage stage, Scene scene) {
        this.stage = stage;
        this.canvas = (Canvas) scene.lookup("#canvas");
        this.pane = (VBox) scene.lookup("#pane");
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.canvas.setOnMouseClicked(this::onMouseClickedOnCanvas);
        this.canvas.setOnMouseMoved(this::onMouseMovedOnCanvas);
        this.canvas.setCursor(Cursor.CROSSHAIR);
        this.canvas.setOnContextMenuRequested(this::onContextMenuRequested);

        try {
            this.level = Arrays.stream(Globals.loadLevels()).filter(l -> Objects.equals(l.getName(), this.stage.getTitle())).findAny().get();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        this.initCurrentLevel();
    }

    private void onContextMenuRequested(ContextMenuEvent event) {
        this.getCurrentLevel().onContextMenuRequested(event).show(canvas, event.getScreenX(), event.getScreenY());
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
        return this.level;
    }

    private void render() {
        Level lvl = getCurrentLevel();
        lvl.render(this.graphicsContext);
    }
}
