package org.dhbw.advancewars;

import javafx.scene.image.Image;
import org.dhbw.advancewars.level.Level;
import org.dhbw.advancewars.level.XMLLevel;
import org.dhbw.advancewars.util.DirectoryReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
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
    public static final Image EMPTY_IMAGE = loadImage("assets/tiles/empty.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image UNIT_SELECT_IMAGE = loadImage("assets/objects/unit_select.gif", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_BLUE_ARTILLERY = loadImage("assets/entities/blue/artillery.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_GREEN_ARTILLERY = loadImage("assets/entities/green/artillery.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_RED_ARTILLERY = loadImage("assets/entities/red/artillery.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_YELLOW_ARTILLERY = loadImage("assets/entities/yellow/artillery.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_BLUE_INFANTRY = loadImage("assets/entities/blue/infantry.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_GREEN_INFANTRY = loadImage("assets/entities/green/infantry.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_RED_INFANTRY = loadImage("assets/entities/red/infantry.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_YELLOW_INFANTRY = loadImage("assets/entities/yellow/infantry.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_BLUE_MECH_INFANTRY = loadImage("assets/entities/blue/mech-infantry.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_GREEN_MECH_INFANTRY = loadImage("assets/entities/green/mech-infantry.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_RED_MECH_INFANTRY = loadImage("assets/entities/red/mech-infantry.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_YELLOW_MECH_INFANTRY = loadImage("assets/entities/yellow/mech-infantry.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_BLUE_TANK = loadImage("assets/entities/blue/tank.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_GREEN_TANK = loadImage("assets/entities/green/tank.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_RED_TANK = loadImage("assets/entities/red/tank.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_YELLOW_TANK = loadImage("assets/entities/yellow/tank.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_BLUE_ANTI_AIR = loadImage("assets/entities/blue/anti-air.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_GREEN_ANTI_AIR = loadImage("assets/entities/green/anti-air.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_RED_ANTI_AIR = loadImage("assets/entities/red/anti-air.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_YELLOW_ANTI_AIR = loadImage("assets/entities/yellow/anti-air.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_BLUE_BATTLE_COPTER = loadImage("assets/entities/blue/battle-copter.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_GREEN_BATTLE_COPTER = loadImage("assets/entities/green/battle-copter.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_RED_BATTLE_COPTER= loadImage("assets/entities/red/battle-copter.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_YELLOW_BATTLE_COPTER = loadImage("assets/entities/yellow/battle-copter.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_BLUE_BOMBER = loadImage("assets/entities/blue/bomber.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_GREEN_BOMBER = loadImage("assets/entities/green/bomber.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_RED_BOMBER = loadImage("assets/entities/red/bomber.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_YELLOW_BOMBER = loadImage("assets/entities/yellow/bomber.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_BLUE_FIGHTER = loadImage("assets/entities/blue/fighter.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_GREEN_FIGHTER = loadImage("assets/entities/green/fighter.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_RED_FIGHTER = loadImage("assets/entities/red/fighter.png", OBJECT_SIZE, OBJECT_SIZE);
    public static final Image ENTITY_YELLOW_FIGHTER = loadImage("assets/entities/yellow/fighter.png", OBJECT_SIZE, OBJECT_SIZE);


    public static final Image HIGHLIGHT = loadImage("assets/objects/highlight.png", OBJECT_SIZE, OBJECT_SIZE);



    public static Image loadImage(String path, int width, int height) {
        URL url = MainApplication.class.getResource(path);
        return new Image(Objects.requireNonNull(url).toString(), width, height, false, false);
    }

    public static Level[] loadLevels() throws ParserConfigurationException, IOException, SAXException {
        List<XMLLevel> levelsList = null;
        try {
            levelsList = DirectoryReader.getFolderItemNames("assets/levels").stream().filter(f -> f.endsWith(".xml")).map(name -> {
                try {
                    return new XMLLevel(Objects.requireNonNull(MainApplication.class.getResource(String.format("assets/levels/%s", name))).toString());
                } catch (ParserConfigurationException | SAXException | IOException e) {
                    throw new RuntimeException(e);
                }
            }).toList();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Level[] levels = new Level[levelsList.size()];
        return levelsList.toArray(levels);
    }
}
