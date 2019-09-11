package ChessGame.View.awt;

import ChessGame.Model.Board;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Enum.EWindow;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGame.View.awt.Component.BoardComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMonitor extends JFrame implements Runnable {

    private boolean running = false;
    private Dimension d = new Dimension(EWindow.WIDTH, EWindow.HEIGHT);
    private BoardComponent boardComponent = new BoardComponent(this.d);
    private BoardIterator bIterator;

    public GameMonitor(String title, Board board) throws HeadlessException {
        super(title);
        this.bIterator = board.iterator();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
    }

    private void init() {
        this.setPreferredSize(this.d);
        this.setMaximumSize(this.d);
        this.setMinimumSize(this.d);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(this.boardComponent, BorderLayout.CENTER);
        this.setVisible(true);
        this.pack();
        this.running = true;

        this.addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    running = false;
                }
            }
        );
    }

    @Override
    public void run() {
        int fps = 60;
        long nanoPerFrame = (long) (1000000000.0 / fps);
        long lastTime = System.nanoTime();

        while (this.running) {
            long nowTime = System.nanoTime();
            if((nowTime - lastTime) < nanoPerFrame) {
                continue;
            }
            lastTime = nowTime;

            this.render();

            long elapsed = System.nanoTime() - lastTime;
            long millisleep = (nanoPerFrame - elapsed) / 1000000;
            if(millisleep > 0) {
                try {
                    Thread.sleep(millisleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.dispose();
    }

    private void render() {
        while(this.bIterator.hasNext()) {
            BoardElement boardElement = this.bIterator.next();

            if (boardElement instanceof Piece) {
                Piece element = (Piece) boardElement;
                System.out.println(element.getElement());
                // TODO: print pieces

            } else {
                System.out.println(boardElement.getElement());
            }
        }
    }

}