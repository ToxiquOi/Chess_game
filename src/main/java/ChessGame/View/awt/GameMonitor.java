package ChessGame.View.awt;

import ChessGame.Model.Board;
import ChessGame.Share.Enum.EWindow;
import ChessGame.View.awt.Canvas.BoardComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMonitor extends JFrame implements Runnable {

    private boolean running = false;
    private Board board = new Board();
    private BoardComponent boardComponent = new BoardComponent(this.board);
    private Dimension d = new Dimension(EWindow.WIDTH, EWindow.HEIGHT);

    public GameMonitor(String title) throws HeadlessException {
        super(title);
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

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void run() {
        while (this.running) {
            this.render();
        }
        this.dispose();
    }

    private void render() {

    }

}
