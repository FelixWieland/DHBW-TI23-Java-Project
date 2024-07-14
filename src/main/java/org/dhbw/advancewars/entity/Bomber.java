package org.dhbw.advancewars.entity;

import javafx.scene.canvas.GraphicsContext;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.level.Level;
import org.dhbw.advancewars.level.Tile;
import org.dhbw.advancewars.util.Position;

import java.util.Map;

public class Bomber extends Entity {

    public Bomber(String team, Level level) {
        super(team ,level);
    }

    @Override
    public int getMovementRange() {
        return 7;
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
                Tile.MapParts.NORMAL_ROCK, 1,
                Tile.MapParts.LARGE_ROCK, 1,
                Tile.MapParts.ROAD, 1,
                Tile.MapParts.WATER, 1
        );
    }

    @Override
    public void render(GraphicsContext ctx, Position pos) {
        var img = switch (team) {
            case "blue" -> Globals.ENTITY_BLUE_BOMBER;
            case "green" -> Globals.ENTITY_GREEN_BOMBER;
            case "red" -> Globals.ENTITY_RED_BOMBER;
            case "yellow" -> Globals.ENTITY_YELLOW_BOMBER;
            default -> Globals.EMPTY_IMAGE;
        };
        this.renderHelper(ctx, pos, img, true);
    }
}
