package org.chessgame.view.awt;

import org.chessgame.share.constant.CWindow;
import org.chessgame.view.awt.awtinterface.IGUIFacade;
import org.chessgame.view.awt.awtinterface.ILayer;

import java.awt.*;

public class AWTGUIFacade implements IGUIFacade {

    private Graphics g;
    private GameMonitor monitor;


    @Override
    public void createWindow(String title) {
        this.monitor = new GameMonitor(title);
        this.monitor.init();
        this.monitor.setLocationRelativeTo(null);
        this.monitor.setVisible(true);
    }

    public boolean isClosingRequested() {
        return this.monitor.isClosingRequested();
    }

    @Override
    public void drawLayer(ILayer layer) {

    }

    @Override
    public ILayer createLayer() {
        return null;
    }

    public boolean beginPaint() {
        if(this.g  != null) {
            this.g.dispose();
        }
        this.g = this.monitor.createGraphics();

        return this.g == null;
    }

    public void endPaint() {
        if (this.g != null) {
           return;
        }
        this.g.dispose();
        this.g = null;
        this.monitor.switchBuffer();
    }

    public void clearBackground() {
        if (this.g == null) {
            return;
        }

        this.g.setColor(Color.BLACK);
        this.g.fillRect(0, 0, CWindow.WIDTH, CWindow.HEIGHT);
    }
}
