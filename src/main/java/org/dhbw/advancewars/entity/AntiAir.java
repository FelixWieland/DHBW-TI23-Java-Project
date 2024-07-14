package org.dhbw.advancewars.entity;

import javafx.scene.canvas.GraphicsContext;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.level.Level;
import org.dhbw.advancewars.level.Tile;
import org.dhbw.advancewars.util.Position;

import java.util.Map;

public class AntiAir extends Entity {

    public AntiAir(String team, Level level) {
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
                Tile.MapParts.TREE, 2,
                Tile.MapParts.ROAD, 1
        );
    }

    @Override
    public void render(GraphicsContext ctx, Position pos) {
        var img = switch (team) {
            case "blue" -> Globals.ENTITY_BLUE_ANTI_AIR;
            case "green" -> Globals.ENTITY_GREEN_ANTI_AIR;
            case "red" -> Globals.ENTITY_RED_ANTI_AIR;
            case "yellow" -> Globals.ENTITY_YELLOW_ANTI_AIR;
            default -> Globals.EMPTY_IMAGE;
        };
        this.renderHelper(ctx, pos, img, true);
    }
}
