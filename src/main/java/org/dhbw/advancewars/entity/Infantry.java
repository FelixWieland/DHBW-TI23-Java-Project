package org.dhbw.advancewars.entity;

import javafx.scene.canvas.GraphicsContext;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.level.Level;
import org.dhbw.advancewars.level.Tile;
import org.dhbw.advancewars.util.Position;

public class Infantry extends Entity {

    public Infantry(String team, Level level) {
        super(team ,level);
    }

    @Override
    public int getRange() {
        return 3;
    }

    @Override
    public Tile.MapParts[] getPossibleFields() {
        return new Tile.MapParts[]{
                Tile.MapParts.GRASS,
                Tile.MapParts.ROAD
        };
    }

    @Override
    public void render(GraphicsContext ctx, Position pos) {
        var img = switch (team) {
            case "blue" -> Globals.ENTITY_BLUE_INFANTRY;
            case "green" -> Globals.ENTITY_GREEN_INFANTRY;
            case "red" -> Globals.ENTITY_RED_INFANTRY;
            case "yellow" -> Globals.ENTITY_YELLOW_INFANTRY;
            default -> Globals.EMPTY_IMAGE;
        };
        this.renderImage(ctx, pos, img, true);
    }
}
