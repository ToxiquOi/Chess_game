package ChessGame.View.awt.Canvas;

import ChessGame.Model.Board;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Enum.EWindow;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGame.View.awt.Graphics.CaseBoard;

import javax.swing.*;
import java.awt.*;

public class BoardComponent extends JPanel {

    private Dimension d = new Dimension(EWindow.WIDTH, EWindow.HEIGHT);
    private BoardIterator bIterator;

    public BoardComponent(Board board) {
        this.bIterator = board.iterator();
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

                BoardElement boardElement;
                if (this.bIterator.hasNext()) {
                    boardElement = this.bIterator.next();
                    if (boardElement instanceof Piece) {
                        Piece element = (Piece) boardElement;
                        System.out.println(element.getElement());

                    } else {
                        System.out.println(boardElement.getElement());
                    }
                }
            }
        }

    }

}
