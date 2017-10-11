package chess.pieces;

import chess.*;

public class Queen extends Piece {
    public Queen(Chess game, Player player, Board board, PieceColor color, Position currentPosition) {
        super(game, player, board, PieceType.QUEEN, color, currentPosition);
    }

    public PositionList calculateAvailablePositions() {
        PositionList positions = new PositionList();
        positions.addAll(getAllDiagonalPositions());
        positions.addAll(getAllAxisPositions());
        return positions;
    }
}
