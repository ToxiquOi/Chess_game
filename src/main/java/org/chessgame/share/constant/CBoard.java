package org.chessgame.share.constant;

import org.chessgame.share.config.JsonConfigRW;

import java.awt.*;

public class CBoard {

    private CBoard() {
    }

    private static Dimension d = new JsonConfigRW().getDimension("board", "current");
    public static int BOARD_WIDTH = d.width;
    public static int BOARD_HEIGHT = d.height;
    public static final int TILE_WIDTH_TAB = 8;
    public static final int TILE_HEIGHT_TAB = 8;
    public static int TILE_WIDTH_PX = BOARD_WIDTH / TILE_WIDTH_TAB;
    public static int TILE_HEIGHT_PX = BOARD_HEIGHT / TILE_HEIGHT_TAB;
}
