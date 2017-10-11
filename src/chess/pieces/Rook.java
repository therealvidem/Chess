package chess.pieces;

import chess.*;

public class Rook extends Piece {
    public Rook(Chess game, Player player, Board board, PieceColor color, Position currentPosition) {
        super(game, player, board, PieceType.ROOK, color, currentPosition);
    }

    public PositionList calculateAvailablePositions() {
        return getAllAxisPositions();
    }
}
