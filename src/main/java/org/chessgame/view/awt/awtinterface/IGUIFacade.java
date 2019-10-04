package org.chessgame.view.awt.awtinterface;

import org.chessgame.view.awt.AWTLayer;

public interface IGUIFacade {
    void createWindow(String title);
    boolean beginPaint();
    void endPaint();
    void clearBackground();
    boolean isClosingRequested();
    void drawLayer(ILayer layer);
    ILayer createLayer();
}
