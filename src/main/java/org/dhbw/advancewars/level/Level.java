package org.dhbw.advancewars.level;

import javafx.scene.canvas.GraphicsContext;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.entity.Entity;
import org.dhbw.advancewars.util.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public abstract class Level {

    // First Accessor is the X-Axis
    // Second Accessor is the Y-Axis;
    // y
    // ^ ... ... ...
    // | 0|1 1|1 2|1 ...
    // | 0|0 1|0 2|0 ...
    // ------------> x
    public Tile[][] map;

    protected String name;
    protected int number;

    private int rows;
    private int cols;

    public Level() {
    }

    public void initMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.map = new Tile[cols][rows];
    }

    public void setTile(Tile tile) {
        this.map[tile.position.x()][tile.position.y()] = tile;
    }

    public int getHeight() {
        return Globals.TILE_SIZE * this.rows;
    }

    public int getWidth() {
        return Globals.TILE_SIZE * this.cols;
    }

    public void render(GraphicsContext ctx) {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Tile tile = this.map[col][row];
                if (tile == null) {
                    continue;
                }
                tile.render(ctx);
            }
        }
    }
}
