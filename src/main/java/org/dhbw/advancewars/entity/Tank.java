package org.dhbw.advancewars.entity;

import javafx.scene.canvas.GraphicsContext;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.level.Level;
import org.dhbw.advancewars.level.Tile;
import org.dhbw.advancewars.util.Position;

import java.util.Map;

public class Tank extends Entity {

    public Tank(String team, Level level) {
        super(team ,level);
    }

    @Override
    public int getMovementRange() {
        return 6;
    }

    @Override
    public int getAttackRange() { return 1; }

    @Override
    public int getMaxHealth() { return 10; }

    @Override
    public int getStrength() { return 5; }

    @Override
    public Map<Tile.MapParts, Integer> getPossibleFields() {
        return Map.of(
                Tile.MapParts.GRASS, 1,
                Tile.MapParts.TREE, 1,
                Tile.MapParts.ROAD, 1
        );
    }

    @Override
    public void render(GraphicsContext ctx, Position pos) {
        var img = switch (team) {
            case "blue" -> Globals.ENTITY_BLUE_TANK;
            case "green" -> Globals.ENTITY_GREEN_TANK;
            case "red" -> Globals.ENTITY_RED_TANK;
            case "yellow" -> Globals.ENTITY_YELLOW_TANK;
            default -> Globals.EMPTY_IMAGE;
        };
        this.renderHelper(ctx, pos, img, true);
    }
}
