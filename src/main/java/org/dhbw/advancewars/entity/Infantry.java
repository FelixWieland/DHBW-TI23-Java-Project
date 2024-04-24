package org.dhbw.advancewars.entity;

import org.dhbw.advancewars.level.Level;

public class Infantry extends Entity {
    public Infantry(int x, int y) {
        super(x, y, "red/infantry.png", LookingDirection.LEFT);
    }
}
