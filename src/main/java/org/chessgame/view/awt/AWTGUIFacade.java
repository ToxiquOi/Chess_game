package org.chessgame.view.awt;

import org.chessgame.share.constant.CWindow;
import org.chessgame.view.viewinterface.IGUIFacade;
import org.chessgame.view.viewinterface.ILayer;

import java.awt.*;

public class AWTGUIFacade implements IGUIFacade {

    private Graphics g;
    private GameMonitor monitor;
    private ILayer layer;


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
        if(this.g == null) {
            return;
        }

        if (this.layer == null) {
            throw new IllegalArgumentException("pas de layer");
        }
        if(!(this.layer instanceof AWTLayer)) {
            throw new IllegalArgumentException("Type de layer Invalide");
        }
        AWTLayer awtLayer = (AWTLayer) this.layer;
        awtLayer.draw(this.g);
    }

    @Override
    public ILayer createLayer() {
        return new AWTLayer();
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
