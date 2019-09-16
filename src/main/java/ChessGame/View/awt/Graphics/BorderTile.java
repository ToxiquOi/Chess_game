package ChessGame.View.awt.Graphics;

import ChessGame.Share.Constant.CBoard;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BorderTile extends Panel {
    public BorderTile(Graphics g, int posX, int posY) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(new Color(0x128E00));
        g2.drawRect(posX * CBoard.TILE_WIDTH_PX, posY * CBoard.TILE_HEIGHT_PX, CBoard.TILE_WIDTH_PX, CBoard.TILE_HEIGHT_PX);
    }
}
