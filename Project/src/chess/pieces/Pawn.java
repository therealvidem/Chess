package chess.pieces;

import chess.*;

public class Pawn extends Piece {
    private boolean moved;

    public Pawn(Chess game, Player player, Board board, PieceColor color, Position currentPosition) {
        super(game, player, board, PieceType.PAWN, color, currentPosition);
    }

    public void setMoved() {
        this.moved = true;
    }

    public PositionList getAvailablePositions() {
        Board board = getBoard();
        Position position = getCurrentPosition();
        PositionList positions = new PositionList();
        int direction = getColor().getDir();

        for (int cDir = -1; cDir < 2; cDir += 2) {
            Position captureDir = position.getRelativePosition(direction, cDir);
            // We require that there's a piece there; pawns can't move diagonally
            // unless they're capturing a piece.
            if (board.getPiece(captureDir) != null)
                addToPositionList(positions, captureDir);
        }

        // Pawns can only move 2 spaces if it hasn't already moved.
        // We also have to make sure it doesn't jump over a piece by detecting
        // if there's one in front of it.
        if (!moved && board.getPiece(direction * 2, 0) == null)
            addToPositionList(positions, position.getRelativePosition(direction * 2, 0));

        addToPositionList(positions, position.getRelativePosition(direction, 0));

        return positions;
    }
}
