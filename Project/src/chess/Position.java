package chess;

import chess.pieces.PieceColor;

public class Position {
    private static final int WHITE_LEFT_SQUARE_ROW = 6;
    private static final int BLACK_LEFT_SQUARE_ROW = 1;
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

    public static int getLeftSquareRow(PieceColor color) {
        if (color.equals(PieceColor.WHITE))
            return WHITE_LEFT_SQUARE_ROW;
        else
            return BLACK_LEFT_SQUARE_ROW;
    }

    public static Position convertFromChessCoordinates(String coordinates) {
        if (coordinates.length() < 2 || coordinates.length() > 2)
            return null;

        char letter = coordinates.charAt(0);
        char numberChar = coordinates.charAt(1);
        int number;

        try {
            number = Integer.parseInt(Character.toString(numberChar));
        } catch (NumberFormatException e) {
            return null;
        }

        if (!Board.inBounds(number))
            return null;

        if (letter < 'a' || letter > 'h')
            return null;

        return new Position(8 - number, letter % 'a');
    }

    public static String convertToChessCoordinates(Position position) {
        char letter = (char)(position.getColumn() + 'a');
        int number = 8 - position.getRow();
        return letter + "" + number;
    }

    public String toString() {
        return convertToChessCoordinates(this);
    }

    public boolean equals(Position p2) {
        return (r == p2.getRow()) && (c == p2.getColumn());
    }
}
