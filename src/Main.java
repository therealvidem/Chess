public class Main {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.populateBoard();
        ChessGame game = new ChessGame(board, "player1", "player2");
        board.setGame(game);
        game.startGame();
    }
}
