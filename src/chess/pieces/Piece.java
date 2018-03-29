package chess.pieces;

import chess.*;

public abstract class Piece {
    private Board board;
    private PieceType type;
    private PieceColor color;
    private Position currentPosition;
    private PositionList availablePositions;

    public Piece(Board board, PieceType type, PieceColor color, Position currentPosition) {
        this.board = board;
        this.type = type;
        this.color = color;
        this.currentPosition = currentPosition;
        this.availablePositions = new PositionList();
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor getColor() {
        return color;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Space getCurrentSpace() { return board.getSpace(getCurrentPosition()); }

    public PositionList getAvailablePositions() { return availablePositions; }

    public String getUnicode() {
        return type.getUnicode(color);
    }

    public boolean isValidPosition(Position newPosition) {
        for (Position position : availablePositions) {
            if (position.equals(newPosition))
                return true;
        }
        return false;
    }

    public Piece makeMove(Position newPosition) {
        Piece piece = board.getPiece(newPosition);
        if (piece != null && piece.getColor() != getColor()) {
            Chess game = board.getGame();
            Player player = game.getPlayer(color);
            Player opposingPlayer = game.getPlayer(piece.getColor());
            opposingPlayer.removePiece(piece);
            player.addCapturedPiece(piece);
        }
        for (Position p : availablePositions)
            getBoard().getSpace(p).removeThreat(this);
        board.movePiece(this, newPosition);
        currentPosition = newPosition;
        return piece;
    }

    PositionList getAxisPositions(int amount) {
        PositionList positions = new PositionList();
        int r = currentPosition.getRow();
        int c = currentPosition.getColumn();

        for (int i = 0; i < 4; i++) {
            int rDir = (int)Math.sin(i * (Math.PI / 2));
            int cDir = (int)Math.cos(i * (Math.PI / 2));
            for (int j = 1; j <= amount; j++)
                if(!addToPositionList(positions, r + rDir * j, c + cDir * j, true))
                    break;
        }

        return positions;
    }

    PositionList getAllAxisPositions() {
        return getAxisPositions(8);
    }

    PositionList getDiagonalPositions(int amount) {
        PositionList positions = new PositionList();
        int r = currentPosition.getRow();
        int c = currentPosition.getColumn();

        for (int rDir = -1; rDir < 2; rDir += 2)
            for (int cDir = -1; cDir < 2; cDir += 2)
                for (int i = 1; i <= amount; i++)
                    if(!addToPositionList(positions, r + rDir * i, c + cDir * i, true))
                        break;

        return positions;
    }

    PositionList getAllDiagonalPositions() {
        return getDiagonalPositions(8);
    }

    boolean addToPositionList(PositionList positions, Position position, boolean threat) {
        return addToPositionList(positions, position.getRow(), position.getColumn(), threat);
    }

    boolean addToPositionList(PositionList positions, int r, int c, boolean threat) {
        Piece occupyingPiece = board.getPiece(r, c);
        if (occupyingPiece != null && occupyingPiece.getColor() == getColor())
            return false;
        if (positions.add(r, c)) {
            if (threat)
                board.getSpace(r, c).addThreat(this);
            return true;
        }
        return false;
    }

    public abstract PositionList calculateAvailablePositions();

    public PositionList updateAvailablePositions() {
        availablePositions = calculateAvailablePositions();
        return availablePositions;
    }

    public boolean hasAvailablePositions() {
        return !availablePositions.isEmpty();
    }

    public String toString() {
        return type + "" + currentPosition;
    }
}
