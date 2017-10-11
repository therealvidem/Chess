package chess.pieces;

import chess.*;

public class Knight extends Piece {
    public Knight(Chess game, Player player, Board board, PieceColor color, Position currentPosition) {
        super(game, player, board, PieceType.KNIGHT, color, currentPosition);
    }

    public PositionList calculateAvailablePositions() {
        PositionList positions = new PositionList();
        Position currentPosition = getCurrentPosition();
        for (int i = 0; i < 4; i++) {
            int rDir = (int)Math.sin(i * (Math.PI / 2));
            int cDir = (int)Math.cos(i * (Math.PI / 2));
            if (rDir == 0) {
                addToPositionList(positions, currentPosition.getRelativePosition(-1, cDir * 2));
                addToPositionList(positions, currentPosition.getRelativePosition(1, cDir * 2));
            } else if (cDir == 0) {
                addToPositionList(positions, currentPosition.getRelativePosition(rDir * 2, -1));
                addToPositionList(positions, currentPosition.getRelativePosition(rDir * 2, 1));
            }
        }
        return positions;
    }
}
