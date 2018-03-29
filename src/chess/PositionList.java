package chess;

import java.util.HashSet;

/*
This class exists as a convenient way to check if the position is an
actually valid board coordinate, rather than checking every time in every
method that adds a position to a list.

A HashSet is used instead of a generic ArrayList because order and duplicate positions don't matter
 */
public class PositionList extends HashSet<Position> {
    public boolean add(int r, int c) {
        if (Board.inBounds(r, c)) {
            super.add(new Position(r, c));
            return true;
        }
        return false;
    }

    public boolean add(Position position) {
        if (Board.inBounds(position)) {
            super.add(position);
            return true;
        }
        return false;
    }
}
