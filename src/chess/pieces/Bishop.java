package chess.pieces;

import chess.*;

public class Bishop extends Piece {
    public Bishop(Board board, PieceColor color, Position currentPosition) {
        super(board, PieceType.BISHOP, color, currentPosition);
    }

    @Override
    public PositionList calculateAvailablePositions() { return getAllDiagonalPositions(); }
}
