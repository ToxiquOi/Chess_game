package org.chessgame.share.enumeration;

public enum EElement {
    EMPTY("EMPTY"),
    PAWN("PAWN"),
    KING("KING"),
    QUEEN("QUEEN"),
    BISHOP("BISHOP"),
    KNIGHT("KNIGHT"),
    ROOK("ROOK");

    String value;

    EElement(String value) {

    }

    public String getValue() {
        return value;
    }
}
