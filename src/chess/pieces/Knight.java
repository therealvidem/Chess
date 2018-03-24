package chess.pieces;

import chess.*;

public class Knight extends Piece {
    public Knight(Board board, PieceColor color, Position currentPosition) {
        super(board, PieceType.KNIGHT, color, currentPosition);
    }

    public PositionList calculateAvailablePositions() {
        PositionList positions = new PositionList();
        Position currentPosition = getCurrentPosition();
        for (int i = 0; i < 4; i++) {
            int rDir = (int)Math.sin(i * (Math.PI / 2));
            int cDir = (int)Math.cos(i * (Math.PI / 2));
            if (rDir == 0) {
                addToPositionList(positions, currentPosition.getRelativePosition(-1, cDir * 2), true);
                addToPositionList(positions, currentPosition.getRelativePosition(1, cDir * 2), true);
            } else if (cDir == 0) {
                addToPositionList(positions, currentPosition.getRelativePosition(rDir * 2, -1), true);
                addToPositionList(positions, currentPosition.getRelativePosition(rDir * 2, 1), true);
            }
        }
        return positions;
    }
}
