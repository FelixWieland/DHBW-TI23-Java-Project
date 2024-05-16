package org.dhbw.advancewars.entity;

import org.dhbw.advancewars.util.Position;
import org.dhbw.advancewars.level.Level;

public abstract class Entity {
    public enum LookingDirection {
        LEFT,
        RIGHT
    }
    public String texture;


    public Entity(String texture) {
    }

}
