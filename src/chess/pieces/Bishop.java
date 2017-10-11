package chess.pieces;

import chess.*;

public class Bishop extends Piece {
    public Bishop(Chess game, Player player, Board board, PieceColor color, Position currentPosition) {
        super(game, player, board, PieceType.BISHOP, color, currentPosition);
    }

    public PositionList getAvailablePositions() {
        return getAllDiagonalPositions();
    }
}
