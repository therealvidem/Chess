package chess.pieces;

import chess.*;

public class King extends Piece {
    public King(Chess game, Player player, Board board, PieceColor color, Position currentPosition) {
        super(game, player, board, PieceType.KING, color, currentPosition);
    }

    public PositionList calculateAvailablePositions() {
        PositionList positions = new PositionList();
        positions.addAll(getDiagonalPositions(1));
        positions.addAll(getAxisPositions(1));
        return positions;
    }
}
