package ChessGame.View.awt.Component;

import ChessGame.Share.Constant.CBoard;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGame.View.awt.Graphics.CaseBoard;

import java.awt.*;


public class BoardComponent extends Canvas {

    private Dimension d;

    public BoardComponent(Dimension d, BoardIterator bi) {
        this.d = d;
        this.init();
    }

    private void init() {
        this.setPreferredSize(this.d);
        this.setMaximumSize(this.d);
        this.setMinimumSize(this.d);
        this.setBackground(Color.GRAY);
    }

    public void draw(Graphics g) {
        for(int x = 0; x < (this.d.getWidth() / CBoard.TILE_WIDTH_PX); x++) {
            for(int y = 0; y < (this.d.getHeight() / CBoard.TILE_HEIGHT_PX); y++) {
//                System.out.println("" + x + " " + y);
                if(((x + y) % 2) == 1) {
                    g.setColor(Color.WHITE);
                }
                else {
                    g.setColor(Color.DARK_GRAY);
                }
                CaseBoard.draw(x, y, g);
            }
        }
    }

}
