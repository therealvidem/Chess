package chess.pieces;

import chess.*;

public class Rook extends Piece {
    public Rook(Board board, PieceColor color, Position currentPosition) {
        super(board, PieceType.ROOK, color, currentPosition);
    }

    @Override
    public PositionList calculateAvailablePositions() {
        return getAllAxisPositions();
    }
}
