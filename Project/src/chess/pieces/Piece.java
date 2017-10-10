package chess.pieces;

import chess.*;

public abstract class Piece {
    private Chess game;
    private Player player;
    private Board board;
    private PieceType type;
    private PieceColor color;
    private Position currentPosition;
    private PositionList availablePositions;

    public Piece(Chess game, Player player, Board board, PieceType type, PieceColor color, Position currentPosition) {
        this.game = game;
        this.player = player;
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
            Player opposingPlayer = game.getPlayer(piece.getColor());
            opposingPlayer.removePiece(piece);
            player.addCapturedPiece(piece);
        }
        board.movePiece(this, newPosition);
        currentPosition = newPosition;
        return piece;
    }

    public PositionList getAxisPositions(int amount) {
        PositionList positions = new PositionList();
        int r = currentPosition.getRow();
        int c = currentPosition.getColumn();

        for (int i = 0; i < 4; i++) {
            int rDir = (int)Math.sin(i * (Math.PI / 2));
            int cDir = (int)Math.cos(i * (Math.PI / 2));
            for (int j = 0; j < amount; j++)
                if (!addToPositionList(positions, r + rDir * j, c + cDir * j))
                    break;
        }

        return positions;
    }

    public PositionList getAllAxisPositions() {
        return getAxisPositions(8);
    }

    public PositionList getDiagonalPositions(int amount) {
        PositionList positions = new PositionList();
        int r = currentPosition.getRow();
        int c = currentPosition.getColumn();

        for (int rDir = -1; rDir < 2; rDir += 2)
            for (int cDir = -1; cDir < 2; cDir += 2)
                for (int i = 1; i < amount; i++)
                    if (!addToPositionList(positions, r + i, c + i))
                        break;

        return positions;
    }

    public PositionList getAllDiagonalPositions() {
        return getDiagonalPositions(8);
    }

    public boolean addToPositionList(PositionList positions, Position position) {
        return addToPositionList(positions, position.getRow(), position.getColumn());
    }

    public boolean addToPositionList(PositionList positions, int r, int c) {
        Piece occupyingPiece = board.getPiece(r, c);
        if (occupyingPiece != null && occupyingPiece.getColor() == getColor())
            return false;
        return positions.add(r, c);
    }

    public abstract PositionList getAvailablePositions();

    public void updateAvailablePositions() {
        availablePositions = getAvailablePositions();
        System.out.println(availablePositions);
    }

    public String toString() {
        return type + "" + currentPosition;
    }
}
