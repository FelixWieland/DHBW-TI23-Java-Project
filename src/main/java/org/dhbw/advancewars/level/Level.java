package org.dhbw.advancewars.level;

import javafx.scene.canvas.GraphicsContext;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.entity.Entity;
import org.dhbw.advancewars.util.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
    public Entity[][] entities;

    protected String name;
    protected int number;

    private int rows;
    private int cols;

    private Optional<Tile> hoveredTile = Optional.empty();
    private Optional<Tile> selectedTile = Optional.empty();
    private List<Position> possibleFieldsToMoveTo = new ArrayList<>();

    public Level() {
    }

    public void initMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.map = new Tile[cols][rows];
        this.entities = new Entity[cols][rows];
    }

    public void setTile(Tile tile) {
        this.map[tile.position.x()][tile.position.y()] = tile;
    }

    public void setEntity(Position position, Entity entity) {
        System.out.printf("Row: %s Col: %s\n", position.y(), position.x());
        this.entities[position.x()][position.y()] = entity;
    }

    public int getHeight() {
        return Globals.TILE_SIZE * this.rows;
    }

    public int getWidth() {
        return Globals.TILE_SIZE * this.cols;
    }

    public void setHoveredTile(int x, int y) {
        this.hoveredTile = this.getTileAt(x, y);
    }

    public boolean onSelectedTileIsEntity() {
        if (this.selectedTile.isPresent()) {
            var entityPos = this.selectedTile.get().position;
            return this.getEntityAt(entityPos).isPresent();
        }
        return false;
    }

    public void selectHoveredTile() {
        if (this.onSelectedTileIsEntity()) {
            // because there is a entity on the selected tile the current action can be a move action
            var from = this.selectedTile.get().position;
            var to = this.hoveredTile.get().position;

            if (!this.possibleFieldsToMoveTo.contains(to) || this.getEntityAt(from).map(Entity::getAlreadyMoved).orElse(false)) {
                return;
            }

            this.possibleFieldsToMoveTo = new ArrayList<>();
            this.selectedTile = Optional.empty();
            if (from == to) {
                return;
            }
            this.getEntityAt(from).get().setAlreadyMoved(true);
            this.moveEntity(from, to);

        } else {
            this.selectedTile = this.hoveredTile;
            if (this.selectedTile.isPresent()) {
                var entityPos = this.selectedTile.get().position;
                if (this.getEntityAt(entityPos).orElse(null) instanceof Entity entity) {
                    if (entity.getAlreadyMoved()) {
                        this.possibleFieldsToMoveTo = new ArrayList<>();
                        return;
                    }
                    this.possibleFieldsToMoveTo = entity.calculatePossibleMoves(entityPos);
                } else {
                    this.possibleFieldsToMoveTo = new ArrayList<>();
                }
            } else {
                this.possibleFieldsToMoveTo = new ArrayList<>();
            }
        }
    }

    public void moveEntity(Position from , Position to) {
        this.entities[to.x()][to.y()] = this.entities[from.x()][from.y()];
        this.entities[from.x()][from.y()] = null;
    }

    public Optional<Tile> getHoveredTile() {
        return this.hoveredTile;
    }

    public Optional<Tile> getSelectedTile() {
        return this.selectedTile;
    }

    public Optional<Tile> getTileAt(Position pos) {
        return this.getTileAt(pos.x(), pos.y());
    }

    public Optional<Tile> getTileAt(int x, int y) {
        // check for out of bounds
        if ((y < 0 || y >= rows) || (x < 0 || x >= cols))  {
            return Optional.empty();
        }
        return Optional.of(this.map[x][y]);
    }

    public Optional<Entity> getEntityAt(Position pos) {
        return this.getEntityAt(pos.x(), pos.y());
    }

    public Optional<Entity> getEntityAt(int x, int y) {
        // check for out of bounds
        if ((y < 0 || y >= rows) || (x < 0 || x >= cols))  {
            return Optional.empty();
        }
        Entity e = this.entities[x][y];
        if (e == null) {
            return Optional.empty();
        }
        return Optional.of(e);
    }

    public void render(GraphicsContext ctx) {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Tile tile = this.map[col][row];
                if (tile != null) {
                    tile.render(ctx);
                }
                Entity entity = this.entities[col][row];
                if (entity != null) {
                    entity.render(ctx, new Position(col, row));
                }
            }
        }

        if (!this.possibleFieldsToMoveTo.isEmpty()) {
            // highlight possible fields to move to
            this.possibleFieldsToMoveTo.forEach(position -> {
                ctx.drawImage(
                        Globals.HIGHLIGHT,
                        position.x() * Globals.TILE_SIZE,
                        this.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                        Globals.TILE_SIZE,
                        Globals.TILE_SIZE
                );
            });
        }

        if (this.hoveredTile.orElse(null) instanceof Tile ht) {
            ctx.drawImage(
                    Globals.UNIT_SELECT_IMAGE,
                    ht.position.x() * Globals.TILE_SIZE,
                    this.getHeight() - Globals.TILE_SIZE - ht.position.y() * Globals.TILE_SIZE,
                    Globals.TILE_SIZE,
                    Globals.TILE_SIZE
            );
        }
    }
}
