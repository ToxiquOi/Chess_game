package org.chessgame.view.awt.graphics;

import org.chessgame.share.constant.CBoard;

import java.awt.*;

public class BorderTile {
    private BorderTile() {}

    public static void draw(Graphics g, int posX, int posY, Color color) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(color);
        g2.drawRect(posX * CBoard.TILE_WIDTH_PX, posY * CBoard.TILE_HEIGHT_PX, CBoard.TILE_WIDTH_PX, CBoard.TILE_HEIGHT_PX);
    }
}
