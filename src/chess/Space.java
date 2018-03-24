package chess;

import chess.pieces.Piece;
import chess.pieces.PieceColor;

import java.util.HashSet;

public class Space {
    private Board board;
    private Piece piece;
    private Position position;
    private HashSet<Piece> threats;

    public Space(Board board, Piece piece, Position position) {
        this.board = board;
        this.piece = piece;
        this.position = position;
        this.threats = new HashSet<>();
    }

    public Space(Board board, Position position) {
        this(board, null, position);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void addThreat(Piece piece) {
        threats.add(piece);
    }

    public void removeThreat(Piece piece) {
        threats.remove(piece);
    }

    public boolean isThreatened(PieceColor color) {
        PieceColor oppositeColor;
        if (color.equals(PieceColor.WHITE))
            oppositeColor = PieceColor.BLACK;
        else
            oppositeColor = PieceColor.WHITE;

        for (Piece p : threats) {
            System.out.println(p);
            if (p.getColor().equals(oppositeColor))
                return true;
        }
        return false;
    }

    public Position getPosition() {
        return position;
    }
}
