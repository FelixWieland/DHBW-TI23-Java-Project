package org.dhbw.advancewars;

import javafx.scene.image.Image;
import org.dhbw.advancewars.level.Level;
import org.dhbw.advancewars.level.XMLLevel;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Globals {
    public static final int WINDOW_SIZE = 800;

    public static final int SCALING = 3;
    public static final int TILE_SIZE = 18 * SCALING;
    public static final int OBJECT_SIZE = 20 * SCALING;
    public static final int SCENE_SIZE = WINDOW_SIZE;

    public static final String START_SCREEN_TITLE = "Advance Wars 2";

    public static final Image BRIDGE_HORI_TILE_IMAGE = loadImage("assets/tiles/bridge-hori.png", TILE_SIZE, TILE_SIZE);
    public static final Image BRIDGE_VERT_TILE_IMAGE = loadImage("assets/tiles/bridge-vert.png", TILE_SIZE, TILE_SIZE);
    public static final Image COAST_CORNER_DOWN_RIGHT_TILE_IMAGE = loadImage("assets/tiles/coast-corner-down-right.png", TILE_SIZE, TILE_SIZE);
    public static final Image COAST_CORNER_LEFT_DOWN_TILE_IMAGE = loadImage("assets/tiles/coast-corner-left-down.png", TILE_SIZE, TILE_SIZE);
    public static final Image COAST_DOWN_TILE_IMAGE = loadImage("assets/tiles/coast-down.png", TILE_SIZE, TILE_SIZE);
    public static final Image COAST_LEFT_TILE_IMAGE = loadImage("assets/tiles/coast-left.png", TILE_SIZE, TILE_SIZE);
    public static final Image COAST_RIGHT_TILE_IMAGE = loadImage("assets/tiles/coast-right.png", TILE_SIZE, TILE_SIZE);
    public static final Image COAST_TOP_TILE_IMAGE = loadImage("assets/tiles/coast-top.png", TILE_SIZE, TILE_SIZE);
    public static final Image GRASS_TILE_IMAGE = loadImage("assets/tiles/grass.png", TILE_SIZE, TILE_SIZE);
    public static final Image ROAD_CORNER_DOWN_RIGHT_TILE_IMAGE = loadImage("assets/tiles/road-corner-down-right.png", TILE_SIZE, TILE_SIZE);
    public static final Image ROAD_CORNER_LEFT_UP_IMAGE = loadImage("assets/tiles/road-corner-left-up.png", TILE_SIZE, TILE_SIZE);
    public static final Image ROAD_HORI_TILE_IMAGE = loadImage("assets/tiles/road-hori.png", TILE_SIZE, TILE_SIZE);
    public static final Image ROAD_VERT_TILE_IMAGE = loadImage("assets/tiles/road-vert.png", TILE_SIZE, TILE_SIZE);
    public static final Image WATER_TILE_IMAGE = loadImage("assets/tiles/water.png", TILE_SIZE, TILE_SIZE);
    public static final Image TREE_OBJECT_IMAGE = loadImage("assets/objects/tree.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ROCK_LARGE_OBJECT_IMAGE = loadImage("assets/objects/rock-large.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ROCK_SMALL_OBJECT_IMAGE = loadImage("assets/objects/rock-small.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ROCK_WATER_TINY_OBJECT_IMAGE = loadImage("assets/objects/rock-water-tiny.png", OBJECT_SIZE, OBJECT_SIZE);


    public static Image loadImage(String path, int width, int height) {
        URL url = MainApplication.class.getResource(path);
        return new Image(Objects.requireNonNull(url).toString(), width, height, false, false);
    }

    public static Level[] loadLevels() throws ParserConfigurationException, IOException, SAXException {
        Level[] levels = new Level[1];
        levels[0] = new XMLLevel(Objects.requireNonNull(MainApplication.class.getResource(String.format("assets/levels/%s", "little-island.xml"))).toString());
        return levels;
    }
}
