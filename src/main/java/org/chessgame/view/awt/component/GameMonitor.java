package org.chessgame.view.awt.component;

import org.chessgame.share.constant.CWindow;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class GameMonitor extends Frame {

    private transient BufferStrategy bs;
    private Dimension d = new Dimension(CWindow.WIDTH, CWindow.HEIGHT);
    private BoardComponent boardComponent;
    private boolean closingRequested = false;


    public GameMonitor(String title) {
        super(title);
    }

    public boolean isClosingRequested() {
        return closingRequested;
    }

    public void init() {
        closingRequested = false;
        this.setSize(this.d);
        this.setResizable(false);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closingRequested = true;
                dispose();
            }
        });
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        this.setLayout(layoutManager);
    }

    public void createBoardComponent() {
        this.boardComponent = new BoardComponent(d);
        this.add(BorderLayout.CENTER, boardComponent);
        this.pack();
    }

    public Graphics createGraphics() {
        this.bs = this.boardComponent.getBufferStrategy();
        if (this.bs == null) {
            this.boardComponent.createBufferStrategy(2);
            this.bs = this.boardComponent.getBufferStrategy();
        }

        return this.bs.getDrawGraphics();
    }

    public void switchBuffer() {
        if(this.bs != null) {
            this.bs.show();
        }
    }
}
