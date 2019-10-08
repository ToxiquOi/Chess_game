package org.chessgame.view.awt.component;

import org.chessgame.share.constant.CWindow;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class GameMonitor extends Frame {

    private BufferStrategy bs;
    private Dimension d = new Dimension(CWindow.WIDTH, CWindow.HEIGHT);
    private BoardComponent boardComponent;
    private boolean closingRequested = false;


    public GameMonitor(String title) {
        super(title);
    }

    public boolean isClosingRequested() {
        return this.closingRequested;
    }

    public void init() {
        this.setSize(this.d);
        this.setResizable(false);
//        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closingRequested = true;
                dispose();
            }
        });
        this.closingRequested = false;
    }

    public void createBoardComponent() {
        this.boardComponent = new BoardComponent(d);
        this.add(boardComponent);
        this.pack();
    }

    public Graphics createGraphics() {
        this.bs = this.boardComponent.getBufferStrategy();
        if (this.bs == null) {
            this.boardComponent.createBufferStrategy(2);
            return null;
        }

        return this.bs.getDrawGraphics();
    }

    public void switchBuffer() {
        if(this.bs != null) {
            this.bs.show();
        }
    }
}
