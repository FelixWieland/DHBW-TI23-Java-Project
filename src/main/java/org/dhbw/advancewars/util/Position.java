package org.dhbw.advancewars.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public record Position(int x, int y) {

    public Position over() {
        return this.over(1);
    }

    public Position over(int amount) {
        return new Position(x, y+amount);
    }

    public Position under() {
        return this.under(1);
    }

    public Position under(int amount) {
        return new Position(x, y-amount);
    }

    public Position left() {
        return this.left(1);
    }

    public Position left(int amount) {
        return new Position(x-amount, y);
    }

    public Position right() {
        return this.right(1);
    }

    public Position right(int amount) {
        return new Position(x+amount, y);
    }

    public List<Position> all() {
        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(this.over());
        positionList.add(this.under());
        positionList.add(this.left());
        positionList.add(this.right());
        return positionList;
    }
}
