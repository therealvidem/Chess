import chess.Board;
import chess.Space;
import chess.pieces.Piece;

public class ChessBoard extends Board {
    @Override
    public String getBoardString() {
        StringBuilder boardString = new StringBuilder();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (c == 0)
                    boardString.append(8 - r).append(" ");
                Piece piece = getPiece(r, c);
                if (piece != null)
                    boardString.append(piece.getUnicode()).append(" ");
                else
                    boardString.append("  ");
            }
            boardString.append('\n');
        }

        boardString.append("  ");
        for (char i = 'a'; i <= 'h'; i++)
            boardString.append(i).append(" ");

        return boardString.toString();
    }
}
