package org.chessgame.share.constant;

public class CBoard {
    private CBoard() {

    }


    public static final int BOARD_WIDTH = 700;
    public static final int BOARD_HEIGHT = 700;
    public static final int TILE_WIDTH_TAB = 8;
    public static final int TILE_HEIGHT_TAB = 8;
    public static final int TILE_WIDTH_PX = BOARD_WIDTH / TILE_WIDTH_TAB;
    public static final int TILE_HEIGHT_PX = BOARD_HEIGHT / TILE_HEIGHT_TAB;
}
