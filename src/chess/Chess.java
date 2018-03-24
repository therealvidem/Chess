package chess;

import chess.pieces.Piece;
import chess.pieces.PieceColor;

public abstract class Chess {
    private Player white;
    private Player black;
    private Player currentPlayer;
    private Board board;
    private boolean running;

    public Chess(Board board, String white, String black) {
        this.white = new Player(white, PieceColor.WHITE);
        this.black = new Player(black, PieceColor.BLACK);
        this.board = board;
        this.white.setPieces(board.getPiecesByColor(PieceColor.WHITE));
        this.black.setPieces(board.getPiecesByColor(PieceColor.BLACK));
        this.currentPlayer = this.white;
        this.running = true;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpposingPlayer() {
        if (currentPlayer.getColor() == PieceColor.WHITE)
            return black;
        else
            return white;
    }

    public Player getPlayer(PieceColor color) {
        if (color.equals(PieceColor.WHITE))
            return white;
        else
            return black;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void switchTurns() {
        if (white == currentPlayer)
            currentPlayer = black;
        else
            currentPlayer = white;
    }

    public abstract Piece getPiece();

    public abstract Position getNewPosition(Piece piece);

    public abstract void startGame();

    public abstract void stopGame(String reason);
}
