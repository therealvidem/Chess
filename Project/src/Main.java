import chess.*;
import chess.pieces.Piece;

import java.util.Scanner;

public class Main {
    private static final String LINE = "-------------------------------------";

    public static void main(String[] args) {
        Chess game = new Chess("player1", "player2");
        Board board = game.getBoard();
        Scanner scanner = new Scanner(System.in);
        while (game.isRunning()) {

            System.out.println(LINE);

                System.out.println(board);

            System.out.println(LINE);

                Player currentPlayer = game.getCurrentPlayer();
                Player opposingPlayer = game.getOpposingPlayer();
                Piece piece = game.getPiece(scanner);
                System.out.println("(" + currentPlayer + ") Selected " + piece);

                Position newPosition = game.getNewPosition(scanner, piece);
                Piece capturedPiece = piece.makeMove(newPosition);
                System.out.println("(" + currentPlayer + ") Moved " + piece + " to " + newPosition);

                if (capturedPiece != null)
                    System.out.println("(" + currentPlayer + ") " + piece + " captured " + capturedPiece);

            System.out.println(LINE);

                System.out.println(currentPlayer + "'s Captured Pieces: " + currentPlayer.getCapturedPieces());
                System.out.println(opposingPlayer + "'s Captured Pieces: " + opposingPlayer.getCapturedPieces());

            System.out.println(LINE);

            game.switchTurns();

        }
    }
}
