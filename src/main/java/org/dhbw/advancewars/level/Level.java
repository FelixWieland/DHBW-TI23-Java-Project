package org.dhbw.advancewars.level;

import org.dhbw.advancewars.Entity;
import org.dhbw.advancewars.Position;

import java.util.LinkedList;
import java.util.List;

public class Level {

    public enum Tile {
        ROAD,
        WATER,
        TREE,
        HOUSE,
        BRIDGE,
        GRASS
    }

    // First Accessor is the X-Axis
    // Second Accessor is the Y-Axis;
    // y
    // ^ ... ... ...
    // | 0|1 1|1 2|1 ...
    // | 0|0 1|0 2|0 ...
    // ------------> x
    public final Tile[][] map;
    public final int height;
    public final int width;

    public final List<Entity> entities;

    public final String background;

    public Level(Tile[][] map, int height, int width, String background) {
        this.map = map;
        this.height = height;
        this.width = width;
        this.entities = new LinkedList<>();
        this.background = background;
    }

    Level(Tile[][] map, double height, double width, String background) {
        this(map, (int)Math.round(height), (int)Math.round(width), background);
    }

    void addEntity(Entity entity) {
        this.entities.add(entity);
    };

    Position locateTileAtScreenPosition(int x, int y) {
        return new Position(x, y);
    }

    Position locateTileAtScreenPosition(Position pos) {
        return this.locateTileAtScreenPosition(pos.x, pos.y);
    }


}
