package org.dhbw.advancewars.entity;

import org.dhbw.advancewars.util.Position;
import org.dhbw.advancewars.level.Level;

public abstract class Entity {
    public enum LookingDirection {
        LEFT,
        RIGHT
    }
    public String texture;
    private Level level;

    private Position position;
    private boolean mirrorTexture;
    private final LookingDirection initialLookingDirection;


    public Entity(int x, int y, String texture, LookingDirection initialLookingDirection) {
        this.position = new Position(x, y);
        this.texture = texture;
        this.initialLookingDirection = initialLookingDirection;
    }

    public Entity setLookingDirection(LookingDirection lookingDirection) {
        this.mirrorTexture = this.initialLookingDirection != lookingDirection;
        return this;
    }

    public Entity setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Position getPosition() {
        return this.position;
    }

    public Level getLevel() {
        return this.level;
    }

    public Position positionOnCanvas() {
        return this.getLevel().locateCanvasPositionOfTile(this.position);
    }

    public boolean getShouldMirrorTexture() {
        return this.mirrorTexture;
    }
}
