package chess.pieces;

public enum PieceType {
    KING    ("K", "K", "k", 0),
    QUEEN   ("Q", "Q", "q", 9),
    ROOK    ("R", "R", "r", 5),
    BISHOP  ("B", "B", "b", 3),
    KNIGHT  ("N", "N", "n", 3),
    PAWN    ("", "P", "p", 1);

    private String notation;
    private String whiteUnicode;
    private String blackUnicode;
    private int points;
    PieceType(String notation, String whiteUnicode, String blackUnicode, int points) {
        this.notation = notation;
        this.whiteUnicode = whiteUnicode;
        this.blackUnicode = blackUnicode;
        this.points = points;
    }

    public String getUnicode(PieceColor color) {
        if (color.equals(PieceColor.WHITE))
            return whiteUnicode;
        else
            return blackUnicode;
    }

    public String toString() {
        return notation;
    }
}
