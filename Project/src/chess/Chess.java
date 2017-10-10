package chess;

import chess.pieces.Piece;
import chess.pieces.PieceColor;

import java.util.Scanner;

public class Chess {
    private Player white;
    private Player black;
    private Player currentPlayer;
    private Board board;
    private boolean running;

    public Chess(String white, String black) {
        this.white = new Player(white, PieceColor.WHITE);
        this.black = new Player(black, PieceColor.BLACK);
        this.board = new Board(this);
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

    public void switchTurns() {
        if (white == currentPlayer)
            currentPlayer = black;
        else
            currentPlayer = white;
    }

    public Piece getPiece(Scanner scanner) {
        String piecePositionString;
        Piece piece;

        do {
            System.out.println("(" + currentPlayer + ") Enter a valid piece coordinate to move: ");
            piecePositionString = scanner.nextLine();
            piece = board.getPiece(piecePositionString);

            if (piece != null && piece.getColor() != currentPlayer.getColor()) {
                System.out.println(Status.WRONG_COLOR);
            } else if (piece == null) {
                System.out.println(Status.EMPTY_SPACE);
            }

        } while (piece == null || piece.getColor() != currentPlayer.getColor());

        piece.updateAvailablePositions();

        return piece;
    }

    public Position getNewPosition(Scanner scanner, Piece piece) {
        String newPositionString;
        Position newPosition;

        do {
            System.out.println("(" + currentPlayer + ") Enter a valid coordinate to move the piece to: ");
            newPositionString = scanner.nextLine();
            newPosition = Position.convertFromChessCoordinates(newPositionString);
            if (newPosition == null) {
                System.out.println(Status.INVALID_COORDINATE);
                newPosition = new Position();
            } else if (!piece.isValidPosition(newPosition)) {
                System.out.println(Status.INVALID_MOVE);
            }

        } while (!piece.isValidPosition(newPosition));

        return newPosition;
    }
}
