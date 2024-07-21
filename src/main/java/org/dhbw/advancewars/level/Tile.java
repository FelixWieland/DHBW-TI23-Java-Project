package org.dhbw.advancewars.level;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.entity.Entity;
import org.dhbw.advancewars.util.Position;

import java.util.*;

public class Tile {
    public enum MapParts {
        ROAD,
        WATER,
        GRASS,
        TREE,
        BRIDGE,
        LARGE_ROCK,
        NORMAL_ROCK,
        TINY_WATER_ROCK,
    }

    final Level level;

    final List<MapParts> mapPartsToRender;

    public final Position position;

    public Tile(int x, int y, Level level) {
        this(new Position(x, y), level);
    }

    public Tile(Position pos, Level level) {
        this.mapPartsToRender = new ArrayList<>();
        this.position = pos;
        this.level = level;
    }

    public void addMapPart(MapParts part) {
        this.mapPartsToRender.add(part);
    }

    public boolean hasPart(MapParts part) {
        return this.mapPartsToRender.contains(part);
    }

    public int isPossibleToMoveTo(Map<MapParts, Integer> possibleMapParts) {
        int cost = -1;
        if (this.level.getEntityAt(this.position).isPresent() || possibleMapParts.isEmpty()) {
            return cost;
        }

        for (MapParts part : mapPartsToRender) {
            if (possibleMapParts.containsKey(part)) {
                cost = possibleMapParts.get(part);
                continue;
            }
            return -1;
        }


        return cost;
    }

    public void render(GraphicsContext ctx) {

        for (MapParts part : this.mapPartsToRender) {

            Image img = switch (part) {
                case MapParts.GRASS -> Globals.GRASS_TILE_IMAGE;
                case MapParts.ROAD -> Globals.EMPTY_IMAGE; // Gets handled separately
                case MapParts.BRIDGE -> Globals.BRIDGE_HORI_TILE_IMAGE;
                case MapParts.WATER -> Globals.WATER_TILE_IMAGE;
                case MapParts.TREE -> Globals.TREE_OBJECT_IMAGE;
                case MapParts.NORMAL_ROCK -> Globals.ROCK_SMALL_OBJECT_IMAGE;
                case MapParts.LARGE_ROCK -> Globals.ROCK_LARGE_OBJECT_IMAGE;
                case MapParts.TINY_WATER_ROCK -> Globals.ROCK_WATER_TINY_OBJECT_IMAGE;
            };

            ctx.drawImage(
                    img,
                     position.x() * Globals.TILE_SIZE,
                     this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                    Globals.TILE_SIZE,
                    Globals.TILE_SIZE
            );

            if (part == MapParts.WATER) {
                // dynamically detect whenever water has grass over/under/left/right from it then add the coast

                boolean grassOver = false;
                boolean grassLeft = false;
                boolean grassUnder = false;
                boolean grassRight = false;


                if (this.level.getTileAt(this.position.over()).orElse(null) instanceof Tile tile) {
                    grassOver = tile.hasPart(MapParts.GRASS);
                }
                if (this.level.getTileAt(this.position.left()).orElse(null) instanceof Tile tile) {
                    grassLeft = tile.hasPart(MapParts.GRASS);
                }
                if (this.level.getTileAt(this.position.under()).orElse(null) instanceof Tile tile) {
                    grassUnder = tile.hasPart(MapParts.GRASS);
                }
                if (this.level.getTileAt(this.position.right()).orElse(null) instanceof Tile tile) {
                    grassRight = tile.hasPart(MapParts.GRASS);
                }

                // now that we know wich parts are coast we can decide wich coast-image to draw
                if (!grassOver && !grassLeft && !grassUnder && !grassRight) {
                    continue; // now coast so skip
                }

                if (grassUnder) {
                    ctx.drawImage(
                            Globals.COAST_DOWN_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                }

                if (grassOver) {
                    ctx.drawImage(
                            Globals.COAST_TOP_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                }

                if (grassRight) {
                    double offset = (grassOver ? (Globals.TILE_SIZE / 18.2) : grassUnder ? -(Globals.TILE_SIZE / 18.2) : 0);
                    ctx.drawImage(
                            Globals.COAST_RIGHT_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE + offset,
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                }

                if (grassLeft) {
                    double offset = (grassOver ? (Globals.TILE_SIZE / 2.7) : grassUnder ? -(Globals.TILE_SIZE / 2.7) : 0);
                    ctx.drawImage(
                            Globals.COAST_LEFT_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE + offset,
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                }




                if (grassOver && grassLeft) {
                    ctx.drawImage(
                            Globals.COAST_CORNER_DOWN_RIGHT_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE - (Globals.TILE_SIZE / 4.2),
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                }

                /*
                TODO: Fix coast drawing. Add more coast images?
                if (grassUnder && grassRight) {
                    ctx.drawImage(
                            Globals.COAST_CORNER_LEFT_DOWN_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE - (Globals.TILE_SIZE / 3.5),
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                }

                */
            } else if (part == MapParts.ROAD) {
                boolean roadOver = false;
                boolean roadLeft = false;
                boolean roadUnder = false;
                boolean roadRight = false;

                if (this.level.getTileAt(this.position.over()).orElse(null) instanceof Tile tile) {
                    roadOver = tile.hasPart(MapParts.ROAD);
                }
                if (this.level.getTileAt(this.position.left()).orElse(null) instanceof Tile tile) {
                    roadLeft = tile.hasPart(MapParts.ROAD);
                }
                if (this.level.getTileAt(this.position.under()).orElse(null) instanceof Tile tile) {
                    roadUnder = tile.hasPart(MapParts.ROAD);
                }
                if (this.level.getTileAt(this.position.right()).orElse(null) instanceof Tile tile) {
                    roadRight = tile.hasPart(MapParts.ROAD);
                }

                boolean roadLeftToUp = roadLeft && roadOver;
                boolean roadRightToUp = roadRight && roadOver;
                boolean roadLeftToDown = roadLeft && roadUnder;
                boolean roadRightToDown = roadRight && roadUnder;
                boolean roadLeftToRight = roadLeft || roadRight;
                boolean roadDownToTop = roadOver || roadUnder;
                boolean roadDownToTopForce = roadOver && roadUnder;
                boolean roadLeftToRightForce = roadLeft && roadRight;

                if (roadDownToTopForce) {
                    ctx.drawImage(
                            Globals.ROAD_VERT_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                } else if(roadLeftToRightForce) {
                    ctx.drawImage(
                            Globals.ROAD_HORI_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                } else if (roadLeftToUp) {
                    ctx.drawImage(
                            Globals.ROAD_CORNER_LEFT_UP_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                } else if (roadRightToUp) {
                    ctx.drawImage(
                            Globals.ROAD_CORNER_LEFT_UP_IMAGE,
                            position.x() * Globals.TILE_SIZE + Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                            -Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                } else if (roadLeftToDown) {
                    ctx.drawImage(
                            Globals.ROAD_CORNER_DOWN_RIGHT_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE + Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                            -Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                } else if (roadRightToDown) {
                    ctx.drawImage(
                            Globals.ROAD_CORNER_DOWN_RIGHT_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                } else if (roadLeftToRight) {
                    ctx.drawImage(
                            Globals.ROAD_HORI_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                } else if (roadDownToTop) {
                    ctx.drawImage(
                            Globals.ROAD_VERT_TILE_IMAGE,
                            position.x() * Globals.TILE_SIZE,
                            this.level.getHeight() - Globals.TILE_SIZE - position.y() * Globals.TILE_SIZE,
                            Globals.TILE_SIZE,
                            Globals.TILE_SIZE
                    );
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Tile{" +
                "level=" + level +
                ", mapPartsToRender=" + mapPartsToRender +
                ", position=" + position +
                '}';
    }
}
