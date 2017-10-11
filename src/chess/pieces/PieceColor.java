package chess.pieces;

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

    public String toString() {
        return name;
    }
}
