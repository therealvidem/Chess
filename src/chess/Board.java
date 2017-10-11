package chess;

import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Chess game;
    private Piece[][] board;

    public Board(Chess game) {
        this.game = game;
        this.board = new Piece[8][8];
        populateBoard();
    }

    private void populateBoard() {
        for (PieceColor color : PieceColor.values()) {
            int leftSquareRow = Position.getLeftSquareRow(color);
            int dir = color.getDir();

            //Populate the pawns
            for (int i = 0; i < 8; i++)
                board[leftSquareRow][i] = new Pawn(game, game.getPlayer(color), this, color, new Position(leftSquareRow, i));

            //Populate the rooks
            for (int i = 0; i < 8; i += 7)
                board[leftSquareRow - dir][i] = new Rook(game, game.getPlayer(color), this, color, new Position(leftSquareRow - dir, i));

            //Populate the knights
            for (int i = 1; i < 8; i += 5)
                board[leftSquareRow - dir][i] = new Knight(game, game.getPlayer(color), this, color, new Position(leftSquareRow - dir, i));

            //Populate the bishops
            for (int i = 2; i < 8; i += 3)
                board[leftSquareRow - dir][i] = new Bishop(game, game.getPlayer(color), this, color, new Position(leftSquareRow - dir, i));

            //Populate the king and queen
            board[leftSquareRow - dir][3] = new Queen(game, game.getPlayer(color), this, color, new Position(leftSquareRow - dir, 3));
            board[leftSquareRow - dir][4] = new King(game, game.getPlayer(color), this, color, new Position(leftSquareRow - dir, 4));
        }
    }

    public List<Piece> getPiecesByColor(PieceColor color) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (color.equals(board[i][j].getColor()))
                    pieces.add(board[i][j]);
        return pieces;
    }

    public Piece getPiece(Position position) {
        if (inBounds(position))
            return board[position.getRow()][position.getColumn()];
        return null;
    }

    public Piece getPiece(int r, int c) {
        if (inBounds(r, c))
            return board[r][c];
        return null;
    }

    public Piece getPiece(String piecePositionString) {
        Position piecePosition = Position.convertFromChessCoordinates(piecePositionString);
        if (piecePosition == null)
            return null;
        else
            return getPiece(piecePosition);
    }

    public static boolean inBounds(Position position) {
        return inBounds(position.getRow(), position.getColumn());
    }

    public static boolean inBounds(int r, int c) {
        return (r < 8 && r >= 0) && (c < 8 && c >= 0);
    }

    public static boolean inBounds(int number) {
        return number < 8 && number >= 0;
    }

    public void movePiece(Piece piece, Position position) {
        if (inBounds(position)) {
            Position currentPosition = piece.getCurrentPosition();
            board[currentPosition.getRow()][currentPosition.getColumn()] = null;
            board[position.getRow()][position.getColumn()] = piece;
        }
    }

    private String getBoardString() {
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

    public String toString() {
        return getBoardString();
    }
}
