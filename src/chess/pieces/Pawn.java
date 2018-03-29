package chess.pieces;

import chess.*;

public class Pawn extends Piece {
    public Pawn(Board board, PieceColor color, Position currentPosition) {
        super(board, PieceType.PAWN, color, currentPosition);
    }

    @Override
    public PositionList calculateAvailablePositions() {
        Board board = getBoard();
        Position position = getCurrentPosition();
        PositionList positions = new PositionList();
        PieceColor color = getColor();
        int direction = color.getDir();

        for (int cDir = -1; cDir < 2; cDir += 2) {
            Position captureDir = position.getRelativePosition(direction, cDir);
            Space space = board.getSpace(captureDir);
            // We require that there's a piece there; pawns can't move diagonally
            // unless they're capturing a piece.
            if (space != null)
                space.addThreat(this);
            if (board.getPiece(captureDir) != null)
                addToPositionList(positions, captureDir, true);
        }

        // Pawns can only move 2 spaces if it hasn't already moved.
        // We also have to make sure it doesn't jump over a piece by detecting
        // if there's one in front of it.
        // Pawns never move backwards, so we can just tell if it hasn't moved by
        // testing if it's in its original row.
        if (getCurrentPosition().getRow() == color.getLeftSquareRow() + color.getDir() && board.getPiece(direction * 2, 0) == null)
            addToPositionList(positions, position.getRelativePosition(direction * 2, 0), false);

        addToPositionList(positions, position.getRelativePosition(direction, 0), false);

        return positions;
    }
}
