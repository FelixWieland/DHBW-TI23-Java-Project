package org.dhbw.advancewars.level;

import org.dhbw.advancewars.entity.Entity;
import org.dhbw.advancewars.entity.Infantry;

public class LittleIsland extends Level {
    public LittleIsland() {
        super(new Tile[19][10], 304 * 2.5, 160 * 2.5, "little-island.png");
        this.initMap();
        this.initEntities();
    }

    void initMap() {
        int y;
        {
            y = 0;
            this.map[0][y] = Tile.WATER;
            this.map[1][y] = Tile.WATER;
            this.map[2][y] = Tile.WATER;
            this.map[3][y] = Tile.WATER;
            this.map[4][y] = Tile.WATER;
            this.map[5][y] = Tile.WATER;
            this.map[6][y] = Tile.WATER;
            this.map[7][y] = Tile.WATER;
            this.map[8][y] = Tile.WATER;
            this.map[9][y] = Tile.WATER;
            this.map[10][y] = Tile.WATER;
            this.map[11][y] = Tile.WATER;
            this.map[12][y] = Tile.WATER;
            this.map[13][y] = Tile.WATER;
            this.map[14][y] = Tile.WATER;
            this.map[15][y] = Tile.WATER;
            this.map[16][y] = Tile.WATER;
            this.map[17][y] = Tile.WATER;
            this.map[18][y] = Tile.WATER;
        }
        {
            y = 1;
            this.map[0][y] = Tile.WATER;
            this.map[1][y] = Tile.TREE;
            this.map[2][y] = Tile.GRASS;
            this.map[3][y] = Tile.HOUSE;
            this.map[4][y] = Tile.GRASS;
            this.map[5][y] = Tile.TREE;
            this.map[6][y] = Tile.GRASS;
            this.map[7][y] = Tile.WATER;
            this.map[8][y] = Tile.WATER;
            this.map[9][y] = Tile.WATER;
            this.map[10][y] = Tile.WATER;
            this.map[11][y] = Tile.WATER;
            this.map[12][y] = Tile.WATER;
            this.map[13][y] = Tile.WATER;
            this.map[14][y] = Tile.WATER;
            this.map[15][y] = Tile.WATER;
            this.map[16][y] = Tile.WATER;
            this.map[17][y] = Tile.WATER;
            this.map[18][y] = Tile.WATER;
        }
        {
            y = 2;
            this.map[0][y] = Tile.WATER;
            this.map[1][y] = Tile.GRASS;
            this.map[2][y] = Tile.HOUSE;
            this.map[3][y] = Tile.ROAD;
            this.map[4][y] = Tile.ROAD;
            this.map[5][y] = Tile.ROAD;
            this.map[6][y] = Tile.ROAD;
            this.map[7][y] = Tile.ROAD;
            this.map[8][y] = Tile.ROAD;
            this.map[9][y] = Tile.ROAD;
            this.map[10][y] = Tile.WATER;
            this.map[11][y] = Tile.WATER;
            this.map[12][y] = Tile.WATER;
            this.map[13][y] = Tile.WATER;
            this.map[14][y] = Tile.WATER;
            this.map[15][y] = Tile.WATER;
            this.map[16][y] = Tile.WATER;
            this.map[17][y] = Tile.WATER;
            this.map[18][y] = Tile.WATER;
        }
        {
            y = 3;
            this.map[0][y] = Tile.WATER;
            this.map[1][y] = Tile.HOUSE;
            this.map[2][y] = Tile.GRASS;
            this.map[3][y] = Tile.GRASS;
            this.map[4][y] = Tile.HOUSE;
            this.map[5][y] = Tile.HOUSE;
            this.map[6][y] = Tile.TREE;
            this.map[7][y] = Tile.TREE;
            this.map[8][y] = Tile.GRASS;
            this.map[9][y] = Tile.ROAD;
            this.map[10][y] = Tile.GRASS;
            this.map[11][y] = Tile.GRASS;
            this.map[12][y] = Tile.WATER;
            this.map[13][y] = Tile.WATER;
            this.map[14][y] = Tile.WATER;
            this.map[15][y] = Tile.WATER;
            this.map[16][y] = Tile.WATER;
            this.map[17][y] = Tile.WATER;
            this.map[18][y] = Tile.WATER;
        }
        {
            y = 4;
            this.map[0][y] = Tile.WATER;
            this.map[1][y] = Tile.WATER;
            this.map[2][y] = Tile.TREE;
            this.map[3][y] = Tile.GRASS;
            this.map[4][y] = Tile.GRASS;
            this.map[5][y] = Tile.GRASS;
            this.map[6][y] = Tile.TREE;
            this.map[7][y] = Tile.GRASS;
            this.map[8][y] = Tile.HOUSE;
            this.map[9][y] = Tile.ROAD;
            this.map[10][y] = Tile.TREE;
            this.map[11][y] = Tile.TREE;
            this.map[12][y] = Tile.TREE;
            this.map[13][y] = Tile.GRASS;
            this.map[14][y] = Tile.WATER;
            this.map[15][y] = Tile.WATER;
            this.map[16][y] = Tile.WATER;
            this.map[17][y] = Tile.WATER;
            this.map[18][y] = Tile.WATER;
        }
        {
            y = 5;
            this.map[0][y] = Tile.WATER;
            this.map[1][y] = Tile.WATER;
            this.map[2][y] = Tile.WATER;
            this.map[3][y] = Tile.WATER;
            this.map[4][y] = Tile.WATER;
            this.map[5][y] = Tile.TREE;
            this.map[6][y] = Tile.GRASS;
            this.map[7][y] = Tile.TREE;
            this.map[8][y] = Tile.TREE;
            this.map[9][y] = Tile.ROAD;
            this.map[10][y] = Tile.HOUSE;
            this.map[11][y] = Tile.GRASS;
            this.map[12][y] = Tile.GRASS;
            this.map[13][y] = Tile.TREE;
            this.map[14][y] = Tile.GRASS;
            this.map[15][y] = Tile.TREE;
            this.map[16][y] = Tile.GRASS;
            this.map[17][y] = Tile.WATER;
            this.map[18][y] = Tile.WATER;
        }
        {
            y = 6;
            this.map[0][y] = Tile.WATER;
            this.map[1][y] = Tile.WATER;
            this.map[2][y] = Tile.WATER;
            this.map[3][y] = Tile.WATER;
            this.map[4][y] = Tile.WATER;
            this.map[5][y] = Tile.WATER;
            this.map[6][y] = Tile.WATER;
            this.map[7][y] = Tile.GRASS;
            this.map[8][y] = Tile.GRASS;
            this.map[9][y] = Tile.ROAD;
            this.map[10][y] = Tile.GRASS;
            this.map[11][y] = Tile.TREE;
            this.map[12][y] = Tile.GRASS;
            this.map[13][y] = Tile.HOUSE;
            this.map[14][y] = Tile.HOUSE;
            this.map[15][y] = Tile.GRASS;
            this.map[16][y] = Tile.GRASS;
            this.map[17][y] = Tile.HOUSE;
            this.map[18][y] = Tile.WATER;
        }
        {
            y = 7;
            this.map[0][y] = Tile.WATER;
            this.map[1][y] = Tile.WATER;
            this.map[2][y] = Tile.WATER;
            this.map[3][y] = Tile.WATER;
            this.map[4][y] = Tile.WATER;
            this.map[5][y] = Tile.WATER;
            this.map[6][y] = Tile.WATER;
            this.map[7][y] = Tile.WATER;
            this.map[8][y] = Tile.HOUSE;
            this.map[9][y] = Tile.ROAD;
            this.map[10][y] = Tile.ROAD;
            this.map[11][y] = Tile.ROAD;
            this.map[12][y] = Tile.ROAD;
            this.map[13][y] = Tile.ROAD;
            this.map[14][y] = Tile.ROAD;
            this.map[15][y] = Tile.ROAD;
            this.map[16][y] = Tile.HOUSE;
            this.map[17][y] = Tile.GRASS;
            this.map[18][y] = Tile.WATER;
        }
        {
            y = 8;
            this.map[0][y] = Tile.WATER;
            this.map[1][y] = Tile.WATER;
            this.map[2][y] = Tile.WATER;
            this.map[3][y] = Tile.WATER;
            this.map[4][y] = Tile.WATER;
            this.map[5][y] = Tile.WATER;
            this.map[6][y] = Tile.WATER;
            this.map[7][y] = Tile.WATER;
            this.map[8][y] = Tile.WATER;
            this.map[9][y] = Tile.WATER;
            this.map[10][y] = Tile.WATER;
            this.map[11][y] = Tile.WATER;
            this.map[12][y] = Tile.GRASS;
            this.map[13][y] = Tile.TREE;
            this.map[14][y] = Tile.GRASS;
            this.map[15][y] = Tile.HOUSE;
            this.map[16][y] = Tile.GRASS;
            this.map[17][y] = Tile.TREE;
            this.map[18][y] = Tile.WATER;
        }
        {
            y = 9;
            this.map[0][y] = Tile.WATER;
            this.map[1][y] = Tile.WATER;
            this.map[2][y] = Tile.WATER;
            this.map[3][y] = Tile.WATER;
            this.map[4][y] = Tile.WATER;
            this.map[5][y] = Tile.WATER;
            this.map[6][y] = Tile.WATER;
            this.map[7][y] = Tile.WATER;
            this.map[8][y] = Tile.WATER;
            this.map[9][y] = Tile.WATER;
            this.map[10][y] = Tile.WATER;
            this.map[11][y] = Tile.WATER;
            this.map[12][y] = Tile.WATER;
            this.map[13][y] = Tile.WATER;
            this.map[14][y] = Tile.WATER;
            this.map[15][y] = Tile.WATER;
            this.map[16][y] = Tile.WATER;
            this.map[17][y] = Tile.WATER;
            this.map[18][y] = Tile.WATER;
        }
    }

    void initEntities() {
        this.addEntity(new Infantry(3,2).setLookingDirection(Entity.LookingDirection.RIGHT));
    }
}
