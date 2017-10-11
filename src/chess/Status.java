package chess;

public enum Status {
    OK                  (""),
    INVALID_COORDINATE  ("The coordinates have to be a valid algebraic notation (a1, a2, a3, etc...) (a-h)(1-8)"),
    INVALID_MOVE        ("That move is invalid."),
    NO_MOVES            ("There are no moves available for that piece."),
    EMPTY_SPACE         ("That space doesn't have a piece."),
    WRONG_COLOR         ("That piece isn't yours.");

    private String message;
    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return getMessage();
    }
}
