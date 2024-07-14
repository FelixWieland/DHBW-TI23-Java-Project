package org.dhbw.advancewars.level;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.entity.Entity;
import org.dhbw.advancewars.util.Position;
import org.dhbw.advancewars.util.Teams;

import java.util.*;
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

    public Teams teams;

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

    public void initTeams(String[] teams) {
        this.teams = new Teams(teams);
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

    public String getName() {
        return this.name;
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

            if (from == to || !this.possibleFieldsToMoveTo.contains(to) || !this.getEntityAt(from).map(Entity::canMove).orElse(true)) {
                this.possibleFieldsToMoveTo = new ArrayList<>();
                this.selectedTile = Optional.empty();
                return;
            }

            this.possibleFieldsToMoveTo = new ArrayList<>();
            this.selectedTile = Optional.empty();

            this.getEntityAt(from).get().setAlreadyMoved(true);
            this.moveEntity(from, to);

        } else {
            this.selectedTile = this.hoveredTile;
            if (this.selectedTile.isPresent()) {
                var entityPos = this.selectedTile.get().position;
                if (this.getEntityAt(entityPos).orElse(null) instanceof Entity entity) {
                    /*
                    if (entity.getAlreadyMoved()) {
                        this.possibleFieldsToMoveTo = new ArrayList<>();
                        return;
                    }
                    */
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

    public List<Entity> getListOfEntitiesInDistance(Position pos, int distance) {
        List<Entity> entitiesList = new ArrayList<>();
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                if (this.entities[col][row] != null) {
                    if (pos.distance(new Position(col, row)) <= distance) {
                        entitiesList.add(this.entities[col][row]);
                    }
                }
            }
        }
        return entitiesList;
    }

    public Position searchPositionOfEntity(Entity entity) {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                if (this.entities[col][row] != null && this.entities[col][row].equals(entity)) {
                    return new Position(col, row);
                }
            }
        }
        return null;
    }

    public void endTurn() {
        this.teams.endTurn();
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                if (this.entities[col][row] != null) {
                    this.entities[col][row].onEndTurn();
                }
            }
        }
    }

    private void removeDeadEntities() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Entity entity = this.entities[col][row];
                if (entity != null && entity.isDead()) {
                    this.entities[col][row] = null;
                }
            }
        }
    }

    private boolean currentTeamWon() {
        List<String> teams = new ArrayList<>();
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Entity entity = this.entities[col][row];
                if (entity != null) {
                    if (!Objects.equals(this.teams.getCurrentTeam(), entity.getTeam())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public ContextMenu onContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        ContextMenu menu = new ContextMenu();

        List<MenuItem> items = new ArrayList<>();

        MenuItem endTurnItem = new MenuItem("End Turn");
        endTurnItem.setOnAction(event -> this.endTurn());
        items.add(endTurnItem);

        MenuItem cancelItem = new MenuItem("Cancel");
        cancelItem.setOnAction(event -> System.out.println("Cancel clicked"));
        items.add(cancelItem);

        if (hoveredTile.isPresent() && getEntityAt(hoveredTile.get().position).orElse(null) instanceof Entity entity) {
            if (entity.isSameTeam(this.teams.getCurrentTeam()) && entity.canAttack()) {
                Position pos = hoveredTile.get().position;
                List<Entity> possibleEnemiesToAttack = getListOfEntitiesInDistance(pos, entity.getAttackRange()).stream().filter(e -> !e.isSameTeam(this.teams.getCurrentTeam())).toList();
                if (!possibleEnemiesToAttack.isEmpty()) {
                    possibleEnemiesToAttack.forEach(enemy -> {
                        MenuItem fireItem = new MenuItem("Fire at: " + enemy.getName());
                        fireItem.setOnAction(event -> {
                            entity.attack(enemy);
                            removeDeadEntities();
                            if (currentTeamWon()) {
                                this.teams.markWinner();
                            }
                        });
                        items.add(fireItem);
                    });

                }
            }
        }

        menu.getItems().addAll(items);
        return menu;
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

        this.teams.render(ctx);
    }
}
