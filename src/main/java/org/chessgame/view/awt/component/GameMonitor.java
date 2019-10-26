package org.chessgame.view.awt.component;

import org.chessgame.controller.listener.Keyboard;
import org.chessgame.controller.listener.Mouse;
import org.chessgame.share.constant.CWindow;
import org.chessgame.share.interfaces.IKeyboard;
import org.chessgame.share.interfaces.IMouse;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameMonitor extends Frame {

    private static Logger logger = Logger.getLogger(GameMonitor.class.getSimpleName());
    private Dimension d = new Dimension(CWindow.WIDTH, CWindow.HEIGHT);
    private BoardPanel boardPanel;
    private boolean closingRequested = false;
    private Keyboard keyboard;
    private Mouse mouse;


    public GameMonitor(String title) {
        super(title);
    }

    public int getBoardPanelWidth() {
        return this.boardPanel.getWidth();
    }

    public int getBoardPanelHeight() {
        return this.boardPanel.getHeight();
    }

    public boolean isClosingRequested() {
        return this.closingRequested;
    }

    public void setClosingRequest(boolean b) {
        this.closingRequested = b;
    }

    public void init() {
        this.closingRequested = false;
        this.setLayout(new BorderLayout());
        this.setSize(this.d);
        this.setResizable(false);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapterCustom(this, this.boardPanel));
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        this.setLayout(layoutManager);
    }

    public void createBoardPanel(String layout) {
        if (this.boardPanel == null) {
            this.boardPanel = new BoardPanel();
            this.boardPanel.init();
            this.add(layout, this.boardPanel);
            this.pack();
        }
        if (this.keyboard == null) {
            this.keyboard = new Keyboard();
            this.boardPanel.addKeyListener(this.keyboard);
        }
        if (this.mouse == null) {
            this.mouse = new Mouse();
            this.boardPanel.addMouseListener(this.mouse);
        }
    }


    public IKeyboard getKeyboard() {
        return this.keyboard;
    }

    public IMouse getMouse() {
        return this.mouse;
    }

    public Graphics createGraphics() {
        return this.boardPanel.createGraphics();
    }

    public void switchBuffer() {
        this.boardPanel.switchBuffer();
    }
}
