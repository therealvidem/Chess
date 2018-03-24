package chess;

import chess.pieces.*;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {
    private Chess game;
    private Space[][] board;
    private Piece[] kings;

    public Board() {
        this.board = new Space[8][8];
        this.kings = new Piece[2];
    }

    public void populateBoard() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = new Space(this, new Position(i, j));
        for (PieceColor color : PieceColor.values()) {
            int leftSquareRow = Position.getLeftSquareRow(color);
            int dir = color.getDir();

            //Populate the pawns
            for (int i = 0; i < 8; i++)
                board[leftSquareRow + dir][i].setPiece(
                        new Pawn(this,
                                color,
                                new Position(leftSquareRow + dir, i))
                );

            //Populate the rooks
            for (int i = 0; i < 8; i += 7)
                board[leftSquareRow][i].setPiece(
                        new Rook(this,
                                color,
                                new Position(leftSquareRow, i))
                );

            //Populate the knights
            for (int i = 1; i < 8; i += 5)
                board[leftSquareRow][i].setPiece(
                        new Knight(this,
                                color,
                                new Position(leftSquareRow, i))
                );

            //Populate the bishops
            for (int i = 2; i < 8; i += 3)
                board[leftSquareRow][i].setPiece(
                        new Bishop(this,
                                color,
                                new Position(leftSquareRow, i))
                );

            //Populate the king and queen
            board[leftSquareRow][3].setPiece(
                    new Queen(this,
                            color,
                            new Position(leftSquareRow, 3))
            );

            Piece king = new King(this, color, new Position(leftSquareRow, 4));
            board[leftSquareRow][4].setPiece(king);
            if (color.equals(PieceColor.WHITE))
                kings[0] = king;
            else
                kings[1] = king;
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
            return kings[0];
        else
            return kings[1];
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

    protected abstract String getBoardString();

    public String toString() {
        return getBoardString();
    }
}
