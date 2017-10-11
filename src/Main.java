import chess.Board;

public class Main {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        ChessGame game = new ChessGame(board, "player1", "player2");
        board.setGame(game);
        board.populateBoard();
        game.startGame();
    }
}
