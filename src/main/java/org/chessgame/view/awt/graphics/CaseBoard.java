package org.chessgame.view.awt.graphics;


import org.chessgame.share.constant.CBoard;

import java.awt.*;

public class CaseBoard {

    private static final int width = CBoard.TILE_WIDTH_PX;
    private static final int height = CBoard.TILE_WIDTH_PX;

    public static void draw(int posX, int posY, Graphics g) {
        g.fillRect(posX * 100, posY * 100, width, height);
    }
}
