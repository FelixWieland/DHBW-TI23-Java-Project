package org.dhbw.advancewars.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.level.Tile;
import org.dhbw.advancewars.util.Position;
import org.dhbw.advancewars.level.Level;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Entity {
    public enum LookingDirection {
        LEFT,
        RIGHT
    }

    final Level level;
    final String team;

    private boolean alreadyMoved = false;


    public Entity(String team, Level level) {
        this.level = level;
        this.team = team;
    }

    public int getRange() {
        return 0;
    }

    public Tile.MapParts[] getPossibleFields() {
        return new Tile.MapParts[0];
    }

    public List<Position> calculatePossibleMoves(Position position) {
        return this.calculatePossibleMoves(position, this.getRange());
    }

    public List<Position> calculatePossibleMoves(Position position, int steps) {
        if (steps == 0) return new ArrayList<>();
        var start = position.all();
        var possiblePositions = reduceImpossiblePositions(start);
        return possiblePositions.stream().flatMap(pos -> {
            var moves = new ArrayList<Position>();
            moves.add(pos);
            moves.addAll(this.calculatePossibleMoves(pos, steps-1));
            return moves.stream();
        }).distinct().toList();
    }

    private List<Position> reduceImpossiblePositions(List<Position> positions) {
        return positions.stream().filter(this::isPositionPossible).toList();
    }

    private boolean isPositionPossible(Position position) {
        var tile =  this.level.getTileAt(position);
        return tile.map(value -> value.isPossibleToMoveTo(this.getPossibleFields())).orElse(false);
    }

    public void setAlreadyMoved(boolean moved) {
        this.alreadyMoved = moved;
    }

    public boolean getAlreadyMoved() {
        return this.alreadyMoved;
    }

    public void render(GraphicsContext ctx, Position pos) {
        System.out.println("this should never run");
    }

    public void renderImage(GraphicsContext ctx, Position position, Image img, boolean flipped) {
        ctx.drawImage(
                img,
                flipped  ? position.x() * Globals.TILE_SIZE + Globals.TILE_SIZE : position.x() * Globals.TILE_SIZE,
                this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                flipped  ? -Globals.TILE_SIZE : Globals.TILE_SIZE,
                Globals.TILE_SIZE
        );
    }
}
