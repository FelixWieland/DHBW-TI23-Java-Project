package org.dhbw.advancewars;

import org.dhbw.advancewars.level.Level;

public class Entity {
    private final Level level;
    private int x;
    private int y;

    private Entity(Level level, int x, int y) {
        this.level = level;
        this.x = x;
        this.y = y;
    }


}
