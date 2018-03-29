package chess;

import chess.pieces.PieceColor;

public class Position {
    private int r;
    private int c;

    public Position() {
        this(-1, -1);
    }

    public Position(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public int getRow() {
        return r;
    }

    public int getColumn() {
        return c;
    }

    public Position getRelativePosition(int dr, int dc) {
        return new Position(r + dr, c + dc);
    }

    public static Position convertFromChessCoordinates(String coordinates) {
        if (coordinates.length() < 2 || coordinates.length() > 2)
            return new Position();

        char letter = coordinates.charAt(0);
        char numberChar = coordinates.charAt(1);
        int number;

        try {
            number = 8 - Integer.parseInt(Character.toString(numberChar));
        } catch (NumberFormatException e) {
            return new Position();
        }

        if (!Board.inBounds(number))
            return new Position();

        if (letter < 'a' || letter > 'h')
            return new Position();

        return new Position(number, letter % 'a');
    }

    public static String convertToChessCoordinates(Position position) {
        if (position.isNull())
            return "null";
        char letter = (char)(position.getColumn() + 'a');
        int number = 8 - position.getRow();
        return letter + "" + number;
    }

    public boolean isNull() {
        return r == -1 && c == -1;
    }

    public String toString() {
        return convertToChessCoordinates(this);
    }

    public boolean equals(Position p2) {
        return (r == p2.getRow()) && (c == p2.getColumn());
    }
}
