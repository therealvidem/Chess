package chess;

import chess.pieces.*;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {
    private static final int WHITE_LEFT_SQUARE_ROW = 7;
    private static final int BLACK_LEFT_SQUARE_ROW = 0;
    private Chess game;
    private Space[][] board;
    private Piece whiteKing;
    private Piece blackKing;

    public Board() {
        this.board = new Space[8][8];
    }

    public void populateBoard() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = new Space(this, new Position(i, j));
        for (PieceColor color : PieceColor.values()) {
            /*
            The leftSquareRow is, simply put, the row by which a set of pieces are put.
            The board has the white pieces on the bottom and the black opposite side,
            thus white's leftSquareRow would index 7th row (while black would index the first, or 0th, row)
            of the board's array which is, in fact, on the bottom of the array if looking at it top-down.

            Here's a visual example:
            [x] -> x is the row of the array
            [0] -> black's pieces
            [1] -> black's pawns relative to the first row
            [2] -> empty
            [3] -> empty
            [4] -> empty
            [5] -> empty
            [6] -> white's pawns relative to the 7th row
            [7] -> white's pieces
             */
            int leftSquareRow = getLeftSquareRow(color);
            int dir = color.getDir();

            // Populate the pawns
            // Simply iterates over the row forward.
            for (int i = 0; i < 8; i++)
                board[leftSquareRow + dir][i].setPiece(
                        new Pawn(this,
                                color,
                                new Position(leftSquareRow + dir, i))
                );

            // Populate the rooks
            for (int i = 0; i < 8; i += 7)
                board[leftSquareRow][i].setPiece(
                        new Rook(this,
                                color,
                                new Position(leftSquareRow, i))
                );

            // Populate the knights
            for (int i = 1; i < 8; i += 5)
                board[leftSquareRow][i].setPiece(
                        new Knight(this,
                                color,
                                new Position(leftSquareRow, i))
                );

            // Populate the bishops
            for (int i = 2; i < 8; i += 3)
                board[leftSquareRow][i].setPiece(
                        new Bishop(this,
                                color,
                                new Position(leftSquareRow, i))
                );

            // Populate the king and queen
            board[leftSquareRow][3].setPiece(
                    new Queen(this,
                            color,
                            new Position(leftSquareRow, 3))
            );

            Piece king = new King(this, color, new Position(leftSquareRow, 4));
            board[leftSquareRow][4].setPiece(king);
            if (color.equals(PieceColor.WHITE))
                whiteKing = king;
            else
                blackKing = king;
        }
        updateBoard();
    }

    public List<Piece> getPiecesByColor(PieceColor color) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j].getPiece();
                if (piece != null && color.equals(piece.getColor()))
                    pieces.add(piece);
            }
        }
        return pieces;
    }

    public Piece getKing(PieceColor color) {
        if (color.equals(PieceColor.WHITE))
            return whiteKing;
        else
            return blackKing;
    }

    public Piece getPiece(Position position) {
        return getPiece(position.getRow(), position.getColumn());
    }

    public Space getSpace(Position position) {
        return getSpace(position.getRow(), position.getColumn());
    }

    public Piece getPiece(int r, int c) {
        if (inBounds(r, c))
            return board[r][c].getPiece();
        return null;
    }

    public Space getSpace(int r, int c) {
        if (inBounds(r, c))
            return board[r][c];
        return null;
    }

    public Chess getGame() {
        return game;
    }

    public void setGame(Chess game) {
        this.game = game;
    }

    public static boolean inBounds(Position position) {
        if (position == null)
            return false;
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
            board[currentPosition.getRow()][currentPosition.getColumn()].setPiece(null);
            board[position.getRow()][position.getColumn()].setPiece(piece);
        }
    }

    public void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j].getPiece();
                if (piece != null)
                    piece.updateAvailablePositions();
            }
        }
    }

    public static int getLeftSquareRow(PieceColor color) {
        if (color.equals(PieceColor.WHITE))
            return WHITE_LEFT_SQUARE_ROW;
        else
            return BLACK_LEFT_SQUARE_ROW;
    }

    public abstract String getBoardString();

    public String toString() {
        return getBoardString();
    }
}
