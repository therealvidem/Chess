import chess.*;
import chess.pieces.Piece;

import java.util.Scanner;

public class ChessGame extends Chess {
    private static final String LINE = "-------------------------------------";
    private Scanner scanner;

    public ChessGame(Board board, String white, String black) {
        super(board, white, black);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Piece getPiece() {
        Board board = getBoard();
        Player currentPlayer = getCurrentPlayer();
        String piecePositionString;
        Piece piece = null;

        do {

            System.out.println("(" + currentPlayer + ") Enter a valid piece coordinate to move: ");
            piecePositionString = scanner.nextLine();
            Position piecePosition = Position.convertFromChessCoordinates(piecePositionString);

            if (!piecePosition.isNull()) {
                piece = board.getPiece(piecePosition);

                if (piece == null) {
                    System.out.println(Status.EMPTY_SPACE);
                } else {
                    piece.updateAvailablePositions();
                    if (piece.getColor() != currentPlayer.getColor())
                        System.out.println(Status.WRONG_COLOR);
                    if (!piece.hasAvailablePositions())
                        System.out.println(Status.NO_MOVES);
                }
            } else {
                System.out.println(Status.INVALID_COORDINATE);
            }

        } while (piece == null || piece.getColor() != currentPlayer.getColor() || !piece.hasAvailablePositions());

        return piece;
    }

    @Override
    public Position getNewPosition(Piece piece) {
        String newPositionString;
        Position newPosition;

        do {

            System.out.println("(" + getCurrentPlayer() + ") Enter a valid coordinate for the piece to move to: ");
            newPositionString = scanner.nextLine();

            newPosition = Position.convertFromChessCoordinates(newPositionString);
            if (!Board.inBounds(newPosition)) {
                System.out.println(Status.INVALID_COORDINATE);
                newPosition.setToNull();
            } else if (!piece.isValidPosition(newPosition)) {
                System.out.println(Status.INVALID_MOVE);
                newPosition.setToNull();
            }

        } while (!Board.inBounds(newPosition));

        return newPosition;
    }

    @Override
    public void startGame() {
        Board board = getBoard();

        while (isRunning()) {

            System.out.println(LINE);

                System.out.println(board);

            System.out.println(LINE);

                Player currentPlayer = getCurrentPlayer();
                Player opposingPlayer = getOpposingPlayer();
                Piece piece = getPiece();
                System.out.println("(" + currentPlayer + ") Selected " + piece);

                Position newPosition = getNewPosition(piece);
                String oldPieceString = piece.toString();
                Piece capturedPiece = piece.makeMove(newPosition);
                System.out.println("(" + currentPlayer + ") Moved " + oldPieceString + " to " + newPosition);

                if (capturedPiece != null)
                    System.out.println("(" + currentPlayer + ") " + oldPieceString + " captured " + capturedPiece);

            System.out.println(LINE);

                System.out.println(currentPlayer + "'s Captured Pieces: " + currentPlayer.getCapturedPieces());
                System.out.println(opposingPlayer + "'s Captured Pieces: " + opposingPlayer.getCapturedPieces());

            switchTurns();

        }

        stopGame();
    }

    @Override
    public void checkmate(Piece piece, Piece capturedPiece) {
        setRunning(false);
        System.out.println("(" + getCurrentPlayer() + ") " + piece + " has checkmated " + capturedPiece);
    }

    @Override
    public void stopGame() {
        System.out.println(LINE);
        System.out.println(getBoard());
        System.out.println(LINE);
        System.out.println("The game has ended.");
    }
}
