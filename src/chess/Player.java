package chess;

import chess.pieces.Piece;
import chess.pieces.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Piece> pieces;
    private List<Piece> capturedPieces;
    private PieceColor color;
    private String name;

    public Player(String name, PieceColor color) {
        this.pieces = new ArrayList<>();
        this.capturedPieces = new ArrayList<>();
        this.color = color;
        this.name = name;
    }

    public PieceColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void addCapturedPiece(Piece piece) {
        capturedPieces.add(piece);
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getCapturedPieces() {
        return capturedPieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void removePiece(Piece piece) {
        capturedPieces.remove(piece);
    }

    public String toString() {
        return getName();
    }
}
