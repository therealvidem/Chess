package chess.pieces;

import chess.Position;

public enum PieceColor {
    WHITE ("White", -1),
    BLACK ("Black", 1);

    private String name;
    private int dir;
    PieceColor(String name, int dir) {
        this.name = name;
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }

    public int getLeftSquareRow() { return Position.getLeftSquareRow(this); }

    public String toString() {
        return name;
    }
}
