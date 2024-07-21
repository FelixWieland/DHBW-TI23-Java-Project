package org.dhbw.advancewars.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.level.Tile;
import org.dhbw.advancewars.util.ImageFilters;
import org.dhbw.advancewars.util.Position;
import org.dhbw.advancewars.level.Level;

import java.util.*;

public abstract class Entity {
    public enum LookingDirection {
        LEFT,
        RIGHT
    }

    final Level level;
    final String team;

    private boolean alreadyMoved = false;
    private int availableAttacksLeft = 0;
    private int health;


    public Entity(String team, Level level) {
        this.level = level;
        this.team = team;
        this.health = this.getMaxHealth();
        this.availableAttacksLeft = this.getAvailableAttacks();
    }

    public int getMovementRange() {
        return 0;
    }

    public int getMaxHealth() { return 0; }

    public int getStrength() { return 0; }

    public int getAttackRange() { return 0; }

    public int getAvailableAttacks() { return 1; }

    private void decreaseHealth(int amount) {
        this.health = this.health - amount;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public boolean canAttack() {
        return this.availableAttacksLeft > 0;
    }

    public void attack(Entity entityToAttack) {
        if (!canAttack()) {
            return;
        }
        entityToAttack.decreaseHealth(this.getStrength());
        this.availableAttacksLeft--;
    }

    public boolean isDead() {
        return this.health == 0;
    }

    public Map<Tile.MapParts, Integer> getPossibleFields() {
        return new HashMap<>();
    }

    public List<Position> calculatePossibleMoves(Position position) {
        return this.calculatePossibleMoves(position, this.getMovementRange());
    }

    public List<Position> calculatePossibleMoves(Position position, int steps) {
        if (steps == 0) return new ArrayList<>();
        var positions = position.all();
        var possiblePositions = new ArrayList<Position>();

       for (Position pos : positions) {
           int cost = this.isPositionPossible(pos);
           if (cost == -1 || cost > steps) {
               continue;
           }
           possiblePositions.add(pos);
           possiblePositions.addAll(this.calculatePossibleMoves(pos, steps - cost));
       }

       return possiblePositions.stream().distinct().toList();
    }

    private List<Position> reduceImpossiblePositions(List<Position> positions) {
        return positions.stream().filter(e -> this.isPositionPossible(e) != -1).toList();
    }

    private int isPositionPossible(Position position) {
        var tile =  this.level.getTileAt(position);
        return tile.map(value -> value.isPossibleToMoveTo(this.getPossibleFields())).orElse(-1);
    }

    public void setAlreadyMoved(boolean moved) {
        this.alreadyMoved = moved;
    }

    public boolean getAlreadyMoved() {
        return this.alreadyMoved;
    }

    public void onEndTurn() {
        this.availableAttacksLeft = this.getAvailableAttacks();
        this.alreadyMoved = false;
    }

    public boolean canMove() {
        return !this.alreadyMoved && (Objects.equals(this.team, this.level.teams.getCurrentTeam()));
    }

    public void render(GraphicsContext ctx, Position pos) {
        System.out.println("this should never run");
    }

    public boolean enemyIsNearby() {
        var pos = this.level.searchPositionOfEntity(this);
        if (pos == null) {
            return false;
        }
        var entities = this.level.getListOfEntitiesInDistance(pos, this.getMovementRange());
        return entities.stream().anyMatch(e -> !Objects.equals(e.team, this.team));
    }

    public boolean isSameTeam(String team) {
        return Objects.equals(team, this.team);
    }

    public String getTeam() {
        return this.team;
    }

    public String getName() {
        return  this.team + " " + this.getClass().getSimpleName() + "("  + this.health + "/" + this.getMaxHealth() +"HP)";
    }

    public void renderHelper(GraphicsContext ctx, Position position, Image img, boolean flipped) {
        if (enemyIsNearby()) {
            flipped = !flipped;
        }

        double x = flipped  ? position.x() * Globals.TILE_SIZE + Globals.TILE_SIZE : position.x() * Globals.TILE_SIZE;
        double y = this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE;

        if (this.alreadyMoved) {
            img = ImageFilters.applyDarkenFilter(img);
        }

        ctx.drawImage(
                img,
                x,
                y,
                flipped  ? -Globals.TILE_SIZE : Globals.TILE_SIZE,
                Globals.TILE_SIZE
        );

        ctx.setFill(Color.WHITE);
        ctx.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        ctx.fillText(String.valueOf(this.health), position.x() * Globals.TILE_SIZE + Globals.TILE_SIZE - 30, this.level.getHeight() - position.y() * Globals.TILE_SIZE - 3);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "level=" + level +
                ", team='" + team + '\'' +
                ", alreadyMoved=" + alreadyMoved +
                ", availableAttacksLeft=" + availableAttacksLeft +
                ", health=" + health +
                '}';
    }
}
