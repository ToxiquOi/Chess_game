package org.chessgame.share.constant;

public class CBoard {
    private CBoard() {}

    public static final int TILE_WIDTH_TAB = 8;
    public static final int TILE_HEIGHT_TAB = 8;
    public static final int TILE_WIDTH_PX = CWindow.WIDTH / TILE_WIDTH_TAB;
    public static final int TILE_HEIGHT_PX = CWindow.HEIGHT / TILE_HEIGHT_TAB;
}
