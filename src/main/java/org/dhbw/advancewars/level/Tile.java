package org.dhbw.advancewars.level;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.entity.Entity;
import org.dhbw.advancewars.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tile {
    public enum MapParts {
        ROAD,
        WATER,
        GRASS,
        TREE,
        BRIDGE,
        LARGE_ROCK,
        NORMAL_ROCK,
        TINY_WATER_ROCK
    }

    final Level level;

    final List<MapParts> mapPartsToRender;

    public final Position position;

    Entity entity;

    public Tile(int x, int y, Level level) {
        this(new Position(x, y), level);
    }

    public Tile(Position pos, Level level) {
        this.mapPartsToRender = new ArrayList<>();
        this.position = pos;
        this.entity = null;
        this.level = level;
    }

    public void addMapPart(MapParts part) {
        this.mapPartsToRender.add(part);
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void render(GraphicsContext ctx) {

        for (MapParts part : this.mapPartsToRender) {

            Image img = switch (part) {
                case MapParts.GRASS -> Globals.GRASS_TILE_IMAGE;
                case MapParts.ROAD -> Globals.ROAD_HORI_TILE_IMAGE;
                case MapParts.BRIDGE -> Globals.BRIDGE_HORI_TILE_IMAGE;
                case MapParts.WATER -> Globals.WATER_TILE_IMAGE;
                case MapParts.TREE -> Globals.TREE_OBJECT_IMAGE;
                case MapParts.NORMAL_ROCK -> Globals.ROCK_SMALL_OBJECT_IMAGE;
                case MapParts.LARGE_ROCK -> Globals.ROCK_LARGE_OBJECT_IMAGE;
                case MapParts.TINY_WATER_ROCK -> Globals.ROCK_WATER_TINY_OBJECT_IMAGE;
            };

            System.out.printf("Col: %s Row: %s Part: %s\n", position.x(), position.y(), part);

            ctx.drawImage(
                    img,
                     position.x() * Globals.TILE_SIZE,
                     this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                    Globals.TILE_SIZE,
                    Globals.TILE_SIZE
            );

        }


        // based on the neighbors specific tiles get rendered differently

    }
}
