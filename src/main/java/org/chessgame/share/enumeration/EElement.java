package org.chessgame.share.enumeration;

public enum EElement {
    EMPTY(0),
    BLACK_PAWN(1),
    BLACK_KING(2),
    BLACK_QUEEN(3),
    BLACK_BISHOP(4),
    BLACK_KNIGHT(5),
    BLACK_ROOK(6),
    WHITE_PAWN(7),
    WHITE_KING(8),
    WHITE_QUEEN(9),
    WHITE_BISHOP(10),
    WHITE_KNIGHT(11),
    WHITE_ROOK(12);

    String value;

    EElement(int value) {

    }

    public String getValue() {
        return value;
    }
}
