package ChessGame.View.awt.Component;

import ChessGame.Model.Board;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Enum.EWindow;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGame.View.Font.ChessFont;
import ChessGame.View.awt.Graphics.CaseBoard;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BoardComponent extends JPanel {

    private Dimension d;

    public BoardComponent(Dimension d) {
        this.d = d;
        this.init();
    }

    private void init() {
        this.setPreferredSize(this.d);
        this.setMaximumSize(this.d);
        this.setMinimumSize(this.d);
    }

    @Override
    public void paint(Graphics g) {
        for(int y = 0; y < (this.d.getHeight() / 100); y++) {
            for(int x = 0; x < (this.d.getWidth() / 100); x++) {
//                System.out.println("" + x + " " + y);
                if(((x + y) % 2) == 1) {
                    g.setColor(Color.WHITE);
                }
                else {
                    g.setColor(Color.BLACK);
                }
                new CaseBoard(x, y, g);
            }
        }

    }

}
