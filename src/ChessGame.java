import chess.*;
import chess.pieces.Piece;
import chess.pieces.PieceColor;
import chess.pieces.PieceType;

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
        PieceColor playerColor = currentPlayer.getColor();
        String piecePositionString;
        Piece piece = board.getKing(playerColor);

        // If the king isn't forced to move (or threatened,) then the player may choose any piece.
        if (!piece.getCurrentSpace().isThreatened(playerColor)) {

            // While the player has not chosen a valid piece, keep requesting them.
            // This is a do-while loop since the player hasn't initially chosen a piece.
            do {

                System.out.println("(" + currentPlayer + ") Enter a valid piece coordinate to move: ");
                piecePositionString = scanner.nextLine();
                // Show threats
                /*char command = piecePositionString.charAt(0);
                if (command == 'z') {
                    StringBuilder boardString = new StringBuilder();

                    for (int r = 0; r < 8; r++) {
                        for (int c = 0; c < 8; c++) {
                            if (c == 0)
                                boardString.append(8 - r).append(" ");
                            Space bspace = board.getSpace(r, c);
                            Piece bpiece = board.getPiece(r, c);
                            if (bpiece != null)
                                boardString.append(bpiece.getUnicode()).append(" ");
                            else if (bspace.isThreatened(playerColor))
                                boardString.append("x ");
                            else
                                boardString.append("  ");
                        }
                        boardString.append('\n');
                    }

                    boardString.append("  ");
                    for (char i = 'a'; i <= 'h'; i++)
                        boardString.append(i).append(" ");

                    System.out.println(boardString.toString());
                    continue;
                }*/
                Position piecePosition = Position.convertFromChessCoordinates(piecePositionString);

                if (!piecePosition.isNull()) {
                    piece = board.getPiece(piecePosition);
                    if (piece == null) {
                        System.out.println(Status.EMPTY_SPACE);
                    } else {
                        PieceColor color = piece.getColor();
                        if (color != playerColor) {
                            System.out.println(Status.WRONG_COLOR);
                            continue;
                        }
                        piece.updateAvailablePositions();
                        if (!piece.hasAvailablePositions()) {
                            System.out.println(Status.NO_MOVES);
                        }
                    }
                } else {
                    System.out.println(Status.INVALID_COORDINATE);
                }

            } while (piece == null || piece.getColor() != playerColor || !piece.hasAvailablePositions());

        } else {
            if (!piece.hasAvailablePositions()) {
                /*
                Checkmate, by definition, only occurs when a player's king is in check and has
                no more available moves, as shown by the logic of this if statement, which checks
                if the current space is threatened by any of the opposing player's pieces.
                Otherwise, if the current space isn't threatened, it's by definition a stalemate.
                 */
                if (piece.getCurrentSpace().isThreatened(playerColor))
                    stopGame(getOpposingPlayer().toString() + " has checkmated " + currentPlayer.toString() + ".");
                else
                    stopGame("There has been a stalemate.");
                return null;
            } else {
                System.out.println("(" + currentPlayer + ") You're forced to move your king, as it is in check.");
            }
        }

        return piece;
    }

    @Override
    public Position getNewPosition(Piece piece) {
        String newPositionString;
        Position newPosition;
        System.out.println("Available Moves: " + piece.getAvailablePositions().toString());

        // While the player has not chosen a valid coordinate to which the chosen piece is able to move, keep requesting them.
        // This is a do-while loop since the player hasn't initially chosen a coordinate.
        do {

            System.out.println("(" + getCurrentPlayer() + ") Enter a valid coordinate for the piece to move to: ");
            newPositionString = scanner.nextLine();
            if (newPositionString.equals("undo"))
                return null;

            newPosition = Position.convertFromChessCoordinates(newPositionString);
            if (!Board.inBounds(newPosition)) {
                System.out.println(Status.INVALID_COORDINATE);
                newPosition = null;
            } else if (!piece.isValidPosition(newPosition)) {
                System.out.println(Status.INVALID_MOVE);
                newPosition = null;
            }

        } while (!Board.inBounds(newPosition));

        return newPosition;
    }

    private void startTurn() {
        Board board = getBoard();

        System.out.println(LINE);

            System.out.println(board);

        System.out.println(LINE);

            Player currentPlayer = getCurrentPlayer();
            Player opposingPlayer = getOpposingPlayer();
            Piece piece = getPiece();
            if (!isRunning())
                return;
            System.out.println("(" + currentPlayer + ") Selected " + piece);

            Position newPosition = getNewPosition(piece);
            if (newPosition == null)
                return;
            String oldPieceString = piece.toString();
            Piece capturedPiece = piece.makeMove(newPosition);
            System.out.println("(" + currentPlayer + ") Moved " + oldPieceString + " to " + newPosition);

            if (capturedPiece != null)
                System.out.println("(" + currentPlayer + ") " + oldPieceString + " captured " + capturedPiece);

        System.out.println(LINE);

            System.out.println(currentPlayer + "'s Captured Pieces: " + currentPlayer.getCapturedPieces());
            System.out.println(opposingPlayer + "'s Captured Pieces: " + opposingPlayer.getCapturedPieces());

        board.updateBoard();
        switchTurns();
    }

    @Override
    public void startGame() {
        /*
        startTurn is a separate method so if a player wants to undo choosing a piece,
        then startTurn can return to this while loop.
         */
        while (isRunning())
            startTurn();
    }
    
    @Override
    public void stopGame(String reason) {
        setRunning(false);
        System.out.println(LINE);
        System.out.println(getBoard());
        System.out.println(LINE);
        System.out.println(reason);
    }
}
