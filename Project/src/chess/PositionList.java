package chess;

import java.util.HashSet;

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
