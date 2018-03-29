package chess.pieces;

import chess.*;

public class King extends Piece {
    public King(Board board, PieceColor color, Position currentPosition) {
        super(board, PieceType.KING, color, currentPosition);
    }

    /*
    The reason this is overridden is because the king needs to
    check through each available positions as to make sure
    it can't put itself onto a threatened space (i.e. check itself.)
     */
    @Override
    public PositionList calculateAvailablePositions() {
        PositionList positions = new PositionList();
        Position position = getCurrentPosition();
        int r = position.getRow();
        int c = position.getColumn();

        for (int rDir = -1; rDir < 1; rDir++) {
            for (int cDir = -1; cDir < 1; cDir++) {
                if (rDir == 0 && cDir == 0)
                    continue;
                int newR = r + rDir;
                int newC = c + cDir;
                Space space = getBoard().getSpace(newR, newC);
                if (space != null && space.isThreatened(getColor()))
                    continue;
                addToPositionList(positions, newR, newC, true);
            }
        }

        return positions;
    }
}
