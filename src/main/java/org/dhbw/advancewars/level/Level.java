package org.dhbw.advancewars.level;

import org.dhbw.advancewars.entity.Entity;
import org.dhbw.advancewars.util.Position;

import java.util.LinkedList;
import java.util.List;

public abstract class Level {

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

    public Level(Tile[][] map, int width, int height, String background) {
        this.map = map;
        this.height = height;
        this.width = width;
        this.entities = new LinkedList<>();
        this.background = background;
    }

    Level(Tile[][] map, double width, double height, String background) {
        this(map, (int)Math.round(width), (int)Math.round(height), background);
    }

    void addEntity(Entity entity) {
        this.entities.add(entity.setLevel(this));
    };

    public Position locateCanvasPositionOfTile(int x, int y) {
        int lengthOfTile = this.getLengthOfTile();
        int heightOfTile = this.getHeightOfTile();

        int xEndOfNthTile = ((x + 1) * lengthOfTile);
        int yEndOfNthTile = this.height - ((y + 1) * heightOfTile);

        return new Position(xEndOfNthTile - lengthOfTile , yEndOfNthTile);
    }

    public Position locateCanvasPositionOfTile(Position pos) {
        return this.locateCanvasPositionOfTile(pos.x(), pos.y());
    }

    public Position locateTileAtCanvasPosition(int x, int y) {
        // TODO
        return new Position(x, y);
    }

    public Position locateTileAtCanvasPosition(Position pos) {
        return this.locateTileAtCanvasPosition(pos.x(), pos.y());
    }

    public int getLengthOfTile() {
       return this.width / this.amountOfXTiles();
    }

    public int getHeightOfTile() {
        return this.height / this.amountOfYTiles();
    }

    private int amountOfXTiles() {
        return this.map.length;
    }

    private int amountOfYTiles() {
        return this.map[0].length;
    }
}
