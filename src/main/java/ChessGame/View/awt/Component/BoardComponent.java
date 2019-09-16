package ChessGame.View.awt.Component;

import ChessGame.Model.Board;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Enum.ColorChess;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGame.View.awt.Graphics.CaseBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;


public class BoardComponent extends Canvas {

    private BoardIterator bIterator;
    private Dimension d;

    public BoardComponent(Dimension d, BoardIterator bi) {
        this.bIterator = bi;
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

//    public void paint(Graphics g) {
//        for(int y = 0; y < (this.d.getHeight() / 100); y++) {
//            for(int x = 0; x < (this.d.getWidth() / 100); x++) {
////                System.out.println("" + x + " " + y);
//                if(((x + y) % 2) == 1) {
//                    g.setColor(Color.WHITE);
//                }
//                else {
//                    g.setColor(Color.BLACK);
//                }
//                new CaseBoard(x, y, g);
//            }
//        }
//
//        while (this.bIterator.hasNext()) {
//            BoardElement boardElement = this.bIterator.next();
//
//            if (boardElement instanceof Piece) {
//                Piece element = (Piece) boardElement;
//
//                final BufferedImage image = this.loadTexture(element);
//                g.drawImage(image,  element.getPosX() * 100, element.getPosY() * 100,null);
//            } else {
////                System.out.println(boardElement.getElement());
//            }
//        }
//    }

