package org.dhbw.advancewars;

import org.dhbw.advancewars.level.LittleIsland;
import org.dhbw.advancewars.level.Level;

public class Globals {
    public static final int WINDOW_SIZE = 800;
    public static final int SCENE_SIZE = WINDOW_SIZE;

    public static final String START_SCREEN_TITLE = "Advance Wars 2";

    public static Level[] LEVELS = {
            new LittleIsland()
    };
}
