package ChessGame.Share.Enum;

public enum Element {
    EMPTY("EMPTY"),
    PAWN("PAWN"),
    KING("KING"),
    QUEEN("QUEEN"),
    BISHOP("BISHOP"),
    KNIGHT("KNIGHT"),
    ROOK("ROOK");

    String value;

    Element(String value) {

    }

    public String getValue() {
        return value;
    }
}
