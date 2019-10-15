package org.chessgame.view.awt.graphics;


import org.chessgame.share.constant.CBoard;

import java.awt.*;

public class CaseBoard {
    private CaseBoard() {}

    private static final int WIDTH = CBoard.TILE_WIDTH_PX;
    private static final int HEIGHT = CBoard.TILE_WIDTH_PX;

    public static void draw(int posX, int posY, Graphics g) {
        g.fillRect(posX * CBoard.TILE_WIDTH_PX, posY * CBoard.TILE_HEIGHT_PX, WIDTH, HEIGHT);
    }
}
