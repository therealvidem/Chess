package chess.pieces;

import chess.*;

public class Queen extends Piece {
    public Queen(Board board, PieceColor color, Position currentPosition) {
        super(board, PieceType.QUEEN, color, currentPosition);
    }

    @Override
    public PositionList calculateAvailablePositions() {
        PositionList positions = new PositionList();
        positions.addAll(getAllDiagonalPositions());
        positions.addAll(getAllAxisPositions());
        return positions;
    }
}
