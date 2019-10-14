package org.chessgame.view.awt.component;

import org.chessgame.controller.listener.Keyboard;
import org.chessgame.controller.listener.Mouse;
import org.chessgame.share.constant.CWindow;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.security.Key;

public class GameMonitor extends Frame {

    private transient BufferStrategy bs;
    private Dimension d = new Dimension(CWindow.WIDTH, CWindow.HEIGHT);
    private BoardComponent boardComponent;
    private boolean closingRequested = false;
    private Keyboard keyboard;
    private Mouse mouse;


    public GameMonitor(String title) {
        super(title);
    }

    public boolean isClosingRequested() {
        return this.closingRequested;
    }

    public void setClosingRequest(boolean b) {
        this.closingRequested = b;
    }

    public void init() {
        this.closingRequested = false;
        this.setSize(this.d);
        this.setResizable(false);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setClosingRequest(true);
                dispose();
                Thread.currentThread().interrupt();
            }
        });

        addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                if (boardComponent != null) {
                    boardComponent.requestFocusInWindow();
                }
            }
        });
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        this.setLayout(layoutManager);
    }

    public void createBoardComponent() {
        if (this.boardComponent == null) {
            this.boardComponent = new BoardComponent(d);
            this.add(BorderLayout.CENTER, this.boardComponent);
        }
        if (this.boardComponent.getWidth() != this.d.width || this.boardComponent.getHeight() != this.d.height) {
            this.boardComponent.setPreferredSize(this.d);
            this.boardComponent.setMinimumSize(this.d);
            this.boardComponent.setMaximumSize(this.d);
            this.pack();
        }
        if (this.keyboard == null) {
            this.keyboard = new Keyboard();
            this.addKeyListener(this.keyboard);
        }
        if (this.mouse == null) {
            this.mouse = new Mouse();
            this.addMouseListener(this.mouse);
        }
    }

    public Keyboard getKeyboard() {
        return this.keyboard;
    }

    public Mouse getMouse() {
        return this.mouse;
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
